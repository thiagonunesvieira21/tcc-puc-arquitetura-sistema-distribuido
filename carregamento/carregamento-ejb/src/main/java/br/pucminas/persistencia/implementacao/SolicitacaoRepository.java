package br.pucminas.persistencia.implementacao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Priority;
import javax.interceptor.Interceptor;

import br.pucminas.comum.persistencia.BaseRepository;
import br.pucminas.modelo.Solicitacao;
import br.pucminas.modelo.enuns.SituacaoTransporteEnum;
import br.pucminas.persistencia.interfaces.ISolicitacaoRepository;

@Priority(Interceptor.Priority.APPLICATION + 10)
public class SolicitacaoRepository extends BaseRepository<Solicitacao, Long> implements ISolicitacaoRepository {

	@Override
	public Long countTotalRegistroPendente() {
		Map<String, Object> params = new HashMap<>();
		params.put("situacao", new Long(SituacaoTransporteEnum.PENDENTE.value));
		
		return (Long) consultarSingleResult(Solicitacao.COUNT_SOLICITACAO_POR_SITUACAO, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitacao> consultarPaginadoPendente() {
		
		Map<String, Object> params = new HashMap<>();
		params.put("situacao", new Long(SituacaoTransporteEnum.PENDENTE.value));
		
		return (List<Solicitacao>) consultar(Solicitacao.SOLICITACAO_POR_SITUACAO,params);
	}

}
