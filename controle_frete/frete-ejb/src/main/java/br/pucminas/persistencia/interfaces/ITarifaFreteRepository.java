package br.pucminas.persistencia.interfaces;

import java.math.BigDecimal;

import br.pucminas.comum.persistencia.IBaseRepository;
import br.pucminas.modelo.TarifaFrete;

public interface ITarifaFreteRepository extends IBaseRepository<TarifaFrete, Long> {

	int reajustar(BigDecimal valor);

	BigDecimal mediaTarifas();

}
