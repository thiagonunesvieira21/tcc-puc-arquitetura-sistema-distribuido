package br.pucminas.servico;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.pucminas.comum.persistencia.IBaseRepository;
import br.pucminas.comum.servico.BaseApplicationService;
import br.pucminas.modelo.Concorrente;
import br.pucminas.persistencia.interfaces.IConcorrenteRepository;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ConcorrenteAS extends BaseApplicationService {

	@Inject
	private IConcorrenteRepository repository;
	
	@Override
	public IBaseRepository<Concorrente, Long> getRepository() {
		return repository;
	}

}
