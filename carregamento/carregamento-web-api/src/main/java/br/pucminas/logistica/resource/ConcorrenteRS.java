package br.pucminas.logistica.resource;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import br.pucminas.logistica.web.BaseRestJson;
import br.pucminas.modelo.Concorrente;
import br.pucminas.servico.ConcorrenteAS;

@Path("concorrente")
public class ConcorrenteRS extends BaseRestJson {

	@EJB
	private ConcorrenteAS service;
	
	
    @SuppressWarnings("unchecked")
	@GET
    @Path("/consultar")
    public List<Concorrente> consultarPaginado(
            @Context HttpServletRequest httpRequest) {

        return service.consultarTodos();
    }
  	
}
