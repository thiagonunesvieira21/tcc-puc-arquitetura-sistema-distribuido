package br.pucminas.persistencia.implementacao;

import java.util.List;

import javax.annotation.Priority;
import javax.interceptor.Interceptor;

import br.pucminas.comum.persistencia.BaseRepository;
import br.pucminas.modelo.EventoSolicitacao;
import br.pucminas.modelo.EventoSolicitacaoPk;
import br.pucminas.persistencia.interfaces.IEventoSolicitacaoRepository;

@Priority(Interceptor.Priority.APPLICATION + 10)
public class EventoSolicitacaoRepository extends BaseRepository<EventoSolicitacao, EventoSolicitacaoPk> implements IEventoSolicitacaoRepository {

	@Override
	public List<EventoSolicitacao> consultaEventoSolicitacaoPendenteEnvio() {
		return (List<EventoSolicitacao>) consultar(EventoSolicitacao.PENDENTES_ENVIO);
	}

}
