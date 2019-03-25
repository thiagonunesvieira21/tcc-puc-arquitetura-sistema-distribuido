package br.pucminas.servico;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.pucminas.comum.persistencia.IBaseRepository;
import br.pucminas.comum.servico.BaseApplicationService;
import br.pucminas.modelo.TipoSituacaoTransporte;
import br.pucminas.persistencia.interfaces.ITipoSituacaoTransporteRepository;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class TipoSituacaoTransporteAS extends BaseApplicationService {

	@Inject
	private ITipoSituacaoTransporteRepository repository;
	
	@Override
	public IBaseRepository<TipoSituacaoTransporte, Long> getRepository() {
		return repository;
	}

}
