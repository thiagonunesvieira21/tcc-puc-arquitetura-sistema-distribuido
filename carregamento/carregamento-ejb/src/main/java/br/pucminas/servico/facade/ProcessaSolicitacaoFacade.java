package br.pucminas.servico.facade;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import br.pucminas.dto.SolicitacaoDTO;
import br.pucminas.modelo.Concorrente;
import br.pucminas.modelo.Solicitacao;
import br.pucminas.modelo.TarifaFrete;
import br.pucminas.modelo.TipoSituacaoTransporte;
import br.pucminas.modelo.enuns.SituacaoTransporteEnum;
import br.pucminas.servico.ConcorrenteAS;
import br.pucminas.servico.EventoSolicitacaoAS;
import br.pucminas.servico.SolicitacaoAS;
import br.pucminas.servico.TarifaFreteAS;
import br.pucminas.servico.TipoSituacaoTransporteAS;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ProcessaSolicitacaoFacade {

	private static final String VALOR_FRETE_CONCORRENTE_MENOR = "Menor Preço";

	@EJB
	private SolicitacaoAS solicitacaoAS;
	
	@EJB
	private TipoSituacaoTransporteAS tipoSituacaoTransporteAS;
	
	@EJB
	private EventoSolicitacaoAS eventoSolicitacaoAS;
	
	@EJB
	private TarifaFreteAS tarifaFreteAS;
	
	@EJB
	private ConcorrenteAS concorrenteAS;
	
	public Solicitacao registarClassificar(SolicitacaoDTO dto) {
		TipoSituacaoTransporte pendente = (TipoSituacaoTransporte) tipoSituacaoTransporteAS.consultarPorId(Long.valueOf(SituacaoTransporteEnum.PENDENTE.value));
		TipoSituacaoTransporte coletado = (TipoSituacaoTransporte) tipoSituacaoTransporteAS.consultarPorId(Long.valueOf(SituacaoTransporteEnum.COLETANDO.value));

		TarifaFrete tarifaFrete = tarifaFreteAS.consular(dto.getOrigem(), dto.getDestino());
		
		Solicitacao solicitacao = new Solicitacao();
		solicitacao.setOrigem(dto.getOrigem());
		solicitacao.setDestino(dto.getDestino());
		solicitacao.setDataHoaraSolicitacao(LocalDateTime.now());
		solicitacao.setUserId(dto.getUserId());
		solicitacao.setTipoExcepcionalidade(dto.getExcepcionalidade());
		solicitacao.setValorFrete(tarifaFrete.getVrTarifaFrete());
		solicitacao.setPrazo(tarifaFrete.getTempoEntrega());
		
		if(dto.getExcepcionalidade() != null && !dto.getExcepcionalidade().isEmpty()) {
			solicitacao.setSituacao(pendente);
		} else {
			solicitacao.setSituacao(coletado);
			
			analisarExistenciaMenorPreco(pendente, tarifaFrete, solicitacao);
		}
		
		solicitacaoAS.salvar(solicitacao);
		
		this.informarRecebimentoSolicitacao(solicitacao);
		
		eventoSolicitacaoAS.registrar(solicitacao);
		
		return solicitacao;
	}

	private void analisarExistenciaMenorPreco(TipoSituacaoTransporte pendente, TarifaFrete tarifaFrete,
			Solicitacao solicitacao) {
		@SuppressWarnings("unchecked")
		List<Concorrente> concorrentes = concorrenteAS.consultarTodos();
		
		for(Concorrente conc : concorrentes) {
			BigDecimal vrFreteConcorrente = conc.getVrTarifa().multiply(BigDecimal.valueOf(tarifaFrete.getQtDistanciaConcorrente()));
			
			if(vrFreteConcorrente.compareTo(tarifaFrete.getValor()) > 0) {
				solicitacao.setTipoExcepcionalidade(VALOR_FRETE_CONCORRENTE_MENOR);
				solicitacao.setSituacao(pendente);
				break;
			}
		}
	}
	
	@Asynchronous
	private void informarRecebimentoSolicitacao(Solicitacao solicitacao) {
		Client client = ClientBuilder.newClient();
		
		String urlSistemaFrota = System.getProperty("URL_SISTEMA_FROTA", "http://localhost:9080/frotas/api/faturamento");
		String urlSistemaGestaoFaturamento = System.getProperty("URL_SISTEMA_FATURAMENTO", "http://localhost:9080/faturamento/api/faturamento");
		
		client
	      .target(urlSistemaFrota)
	      .request(MediaType.APPLICATION_JSON)
	      .post(Entity.entity(solicitacao, MediaType.APPLICATION_JSON));
		
		client
	      .target(urlSistemaGestaoFaturamento)
	      .request(MediaType.APPLICATION_JSON)
	      .post(Entity.entity(solicitacao, MediaType.APPLICATION_JSON));
		
	}

	public Solicitacao indicarColeta(Long idSolicitacao) {
		TipoSituacaoTransporte situacao = (TipoSituacaoTransporte) tipoSituacaoTransporteAS.consultarPorId(Long.valueOf(SituacaoTransporteEnum.COLETADA.value));
		
		Solicitacao solicitacao = (Solicitacao) solicitacaoAS.consultarPorId(idSolicitacao);
		solicitacao.setSituacao(situacao);
		
		return (Solicitacao) solicitacaoAS.alterar(solicitacao);
	}
	
}
