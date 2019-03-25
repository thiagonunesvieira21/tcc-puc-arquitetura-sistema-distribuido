package br.pucminas.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.pucminas.comum.persistencia.IBaseRepository;
import br.pucminas.comum.servico.BaseApplicationService;
import br.pucminas.modelo.EventoSolicitacao;
import br.pucminas.modelo.EventoSolicitacaoPk;
import br.pucminas.persistencia.interfaces.IEventoSolicitacaoRepository;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EventoSolicitacaoAS extends BaseApplicationService {

	@Inject
	private IEventoSolicitacaoRepository repository;
	
	@Override
	public IBaseRepository<EventoSolicitacao, EventoSolicitacaoPk> getRepository() {
		return repository;
	}
	
	public List<EventoSolicitacao> consultaEventoSolicitacaoPendenteEnvio() {
        return (List<EventoSolicitacao>) repository.consultaEventoSolicitacaoPendenteEnvio();
	}

}
