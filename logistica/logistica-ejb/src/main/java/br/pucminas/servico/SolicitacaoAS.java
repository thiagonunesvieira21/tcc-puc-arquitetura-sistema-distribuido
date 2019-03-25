package br.pucminas.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.pucminas.comum.persistencia.IBaseRepository;
import br.pucminas.comum.servico.BaseApplicationService;
import br.pucminas.modelo.Solicitacao;
import br.pucminas.persistencia.interfaces.ISolicitacaoRepository;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class SolicitacaoAS extends BaseApplicationService {

	@Inject
	private ISolicitacaoRepository repository;
	
	@Override
	public IBaseRepository<Solicitacao, Long> getRepository() {
		return repository;
	}
	
	public List<Solicitacao> consultaSolicitacaoEntregues() {
        return (List<Solicitacao>) repository.consultarEntregues();
	}

}
