package br.pucminas.servico;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.pucminas.comum.persistencia.IBaseRepository;
import br.pucminas.comum.servico.BaseApplicationService;
import br.pucminas.modelo.EventoSolicitacao;
import br.pucminas.modelo.EventoSolicitacaoPk;
import br.pucminas.modelo.Solicitacao;
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

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public EventoSolicitacao registrar(Solicitacao solicitacao) {
		EventoSolicitacao evento = new EventoSolicitacao();
		evento.setId(new EventoSolicitacaoPk());
		evento.getId().setDhRegistro(LocalDateTime.now());
		evento.getId().setNuSituacao(solicitacao.getSituacao().getId());
		evento.getId().setNuSolicitacao(solicitacao.getId());
		
		return (EventoSolicitacao) super.salvar(evento);
	}
	
	public List<EventoSolicitacao> consultaEventoSolicitacaoPendenteEnvio() {
        return (List<EventoSolicitacao>) repository.consultaEventoSolicitacaoPendenteEnvio();
	}

}
