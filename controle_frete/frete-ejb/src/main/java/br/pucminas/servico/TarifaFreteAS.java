package br.pucminas.servico;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.pucminas.comum.persistencia.IBaseRepository;
import br.pucminas.comum.servico.BaseApplicationService;
import br.pucminas.modelo.TarifaFrete;
import br.pucminas.persistencia.interfaces.ITarifaFreteRepository;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class TarifaFreteAS extends BaseApplicationService {

	@Inject
	private ITarifaFreteRepository repository;
	
	
	@Override
	public IBaseRepository<TarifaFrete, Long> getRepository() {
		return repository;
	}


	public List<TarifaFrete> simular(String cepOrigem, String cepDestino, Long qtDistancia) {
		List<TarifaFrete> simulacao = new ArrayList<>();
		TarifaFrete tarifaFrete = new TarifaFrete();
		tarifaFrete.setCepDestino(cepDestino);
		tarifaFrete.setCepOrigem(cepOrigem);
		tarifaFrete.setQtDistancia(qtDistancia);
		tarifaFrete.setVrTarifaFrete(repository.mediaTarifas());
		tarifaFrete.getTempoEntrega();
		tarifaFrete.getValor();
		
		simulacao.add(tarifaFrete);
		
		return simulacao;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public int reajustar(BigDecimal valor) {
		return repository.reajustar(valor);
	}

}
