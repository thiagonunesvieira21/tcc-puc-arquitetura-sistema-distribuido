package br.pucminas.persistencia.interfaces;

import java.util.List;

import br.pucminas.comum.persistencia.IBaseRepository;
import br.pucminas.modelo.EventoSolicitacao;
import br.pucminas.modelo.EventoSolicitacaoPk;

public interface IEventoSolicitacaoRepository extends IBaseRepository<EventoSolicitacao, EventoSolicitacaoPk> {

	List<EventoSolicitacao> consultaEventoSolicitacaoPendenteEnvio();

}
