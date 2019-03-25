package br.pucminas.persistencia.interfaces;

import br.pucminas.comum.persistencia.IBaseRepository;
import br.pucminas.modelo.TarifaFrete;

public interface ITarifaFreteRepository extends IBaseRepository<TarifaFrete, Long> {

	TarifaFrete consultar(String cepOrigem, String cepDestino);

}
