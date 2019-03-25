package br.pucminas.persistencia.implementacao;

import java.math.BigDecimal;

import javax.annotation.Priority;
import javax.interceptor.Interceptor;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.pucminas.comum.persistencia.BaseRepository;
import br.pucminas.modelo.TarifaFrete;
import br.pucminas.persistencia.interfaces.ITarifaFreteRepository;

@Priority(Interceptor.Priority.APPLICATION + 10)
public class TarifaFreteRepository extends BaseRepository<TarifaFrete, Long> implements ITarifaFreteRepository {

	@PersistenceContext(unitName = "sislogDB")
	protected EntityManager entityManager;
	
	@Override
	public BigDecimal mediaTarifas() {
		Query q = entityManager.createNamedQuery(TarifaFrete.MEDIA);
		
		BigDecimal media = null;
		
		try {
			media = new BigDecimal((Double) q.getSingleResult());
		}catch (NoResultException e) {
			 media = BigDecimal.ZERO;
		}
		return media;
	}

	@Override
	public int reajustar(BigDecimal valor) {
		String quey = "UPDATE controle_frete.tb01_tarifa_frete SET vr_tarifa_frete = (vr_tarifa_frete * "+ valor.doubleValue()  +")";
		Query q = entityManager.createNativeQuery(quey);
		return q.executeUpdate();
	}

}
