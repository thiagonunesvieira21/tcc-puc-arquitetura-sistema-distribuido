package br.pucminas.logistica.resource;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import br.pucminas.logistica.web.BaseRestJson;
import br.pucminas.modelo.TarifaFrete;
import br.pucminas.servico.TarifaFreteAS;

@Path("tarifa")
public class TarifaFreteRS extends BaseRestJson {

	@EJB
	private TarifaFreteAS service;

	@GET
	@Path("/{id}")
	public TarifaFrete consultar(@PathParam("id") final Long id, @Context HttpServletRequest httpRequest) {
		return (TarifaFrete) service.consultarPorId(id);
	}

	@SuppressWarnings("unchecked")
	@GET
	public List<TarifaFrete> consultar(@Context HttpServletRequest httpRequest) {
		return service.consultarTodos();
	}

	@GET
	@Path("/simular/{cepOrigem}/{cepDestino}/{qtDistancia}")
	public List<TarifaFrete> simular(@PathParam("cepOrigem") final String cepOrigem,
			@PathParam("cepDestino") final String cepDestino, @PathParam("qtDistancia") final Long qtDistancia,  
			@Context HttpServletRequest httpRequest) {
		return service.simular(cepOrigem, cepDestino, qtDistancia);
	}

	@POST
	public TarifaFrete salvar(TarifaFrete tarifa, @Context HttpServletRequest httpRequest) {
		return (TarifaFrete) service.salvar(tarifa);
	}

	@PUT
	@Path("/{id}")
	public TarifaFrete alterar(TarifaFrete tarifa, @PathParam("id") final Long id,
			@Context HttpServletRequest httpRequest) {
		return (TarifaFrete) service.alterar(tarifa);
	}

	@PUT
	@Path("/reajustar/{valor}")
	public int alterar(@PathParam("valor") final BigDecimal valor, @Context HttpServletRequest httpRequest) {
		BigDecimal taxa = BigDecimal.ONE.add(valor.divide(BigDecimal.valueOf(100)));
		return service.reajustar(taxa);
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") final Long id, @Context HttpServletRequest httpRequest) {
		service.excluirPorId(id);
	}
}
