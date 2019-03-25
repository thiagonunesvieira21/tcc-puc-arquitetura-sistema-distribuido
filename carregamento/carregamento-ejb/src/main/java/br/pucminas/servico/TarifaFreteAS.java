package br.pucminas.servico;

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
	
	public TarifaFrete consular(String cepOrigem, String cepDestino) {
		return repository.consultar(cepOrigem, cepDestino);
	}

}
