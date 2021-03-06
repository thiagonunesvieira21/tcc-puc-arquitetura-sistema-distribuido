package br.com.frota;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("frota")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class FrotaRS {

	@POST
	public Response post(Object solicitacao) {
		
		return Response.status(Status.OK).entity(solicitacao).build();
	}
	
}
