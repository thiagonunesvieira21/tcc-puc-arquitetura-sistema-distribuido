package br.pucminas.persistencia.implementacao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Priority;
import javax.interceptor.Interceptor;

import br.pucminas.comum.persistencia.BaseRepository;
import br.pucminas.modelo.TarifaFrete;
import br.pucminas.persistencia.interfaces.ITarifaFreteRepository;

@Priority(Interceptor.Priority.APPLICATION + 10)
public class TarifaFreteRepository extends BaseRepository<TarifaFrete, Long> implements ITarifaFreteRepository {


	@Override
	public TarifaFrete consultar(String cepOrigem, String cepDestino ) {
		Map<String, Object> params = new HashMap<>();
		params.put("cepOrigem", cepOrigem);
		params.put("cepDestino", cepDestino);
		
		@SuppressWarnings("unchecked")
		List<TarifaFrete> list = (List<TarifaFrete>) consultar(TarifaFrete.ROTA,params);
		
		return list != null ? list.get(0) : null;
	}

}
