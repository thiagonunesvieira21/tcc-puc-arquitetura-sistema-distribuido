package br.pucminas.servico.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.pucminas.modelo.Concorrente;
import br.pucminas.modelo.Solicitacao;
import br.pucminas.modelo.TipoSituacaoTransporte;
import br.pucminas.modelo.enuns.SituacaoTransporteEnum;
import br.pucminas.servico.ConcorrenteAS;
import br.pucminas.servico.EventoSolicitacaoAS;
import br.pucminas.servico.SolicitacaoAS;
import br.pucminas.servico.TipoSituacaoTransporteAS;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class TransferirSolicitacaoFacade {
	
	@EJB
	private SolicitacaoAS solicitacaoAS;
	
	@EJB
	private ConcorrenteAS concorrenteAS;
	
	@EJB
	private TipoSituacaoTransporteAS tipoSituacaoTransporteAS;
	
	@EJB
	private EventoSolicitacaoAS eventoSolicitacaoAS;
	
	public Boolean transferirSolicitacao(Long idSolicitacao, Long idConcorrente) {
		Solicitacao solicitacao = (Solicitacao) solicitacaoAS.consultarPorId(idSolicitacao);
		Concorrente concorrente = (Concorrente) concorrenteAS.consultarPorId(idConcorrente);

		//@TODO CALL CICS PROGRAM
		
		TipoSituacaoTransporte situacao = (TipoSituacaoTransporte) tipoSituacaoTransporteAS.consultarPorId(Long.valueOf(SituacaoTransporteEnum.TRANSFERIDA.value));
		
		solicitacao.setConcorrente(concorrente);
		solicitacao.setSituacao(situacao);
		
		solicitacaoAS.alterar(solicitacao);
		
		eventoSolicitacaoAS.registrar(solicitacao);
		return Boolean.TRUE;
		
	}
}
