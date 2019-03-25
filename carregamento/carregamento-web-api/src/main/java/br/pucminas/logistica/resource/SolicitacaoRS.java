package br.pucminas.logistica.resource;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.pucminas.dto.TransferenciaDTO;
import br.pucminas.logistica.web.BaseRestJson;
import br.pucminas.modelo.Solicitacao;
import br.pucminas.servico.SolicitacaoAS;
import br.pucminas.servico.facade.ProcessaSolicitacaoFacade;
import br.pucminas.servico.facade.TransferirSolicitacaoFacade;

@Path("solicitacao")
public class SolicitacaoRS extends BaseRestJson {

	@EJB
	private SolicitacaoAS service;
	
	@EJB
	private TransferirSolicitacaoFacade transferenciaFacade;
	
	@EJB
	private ProcessaSolicitacaoFacade processaSolicitacaoFacade;
	
    @GET
    @Path("/consultar")
    public List<Solicitacao> consultarPaginado(
            @Context HttpServletRequest httpRequest) {

        return service.consultaSolicitacaoPendente();
    }
    
    @POST
    @Path("/transferir")
    public Response transferir(
    		TransferenciaDTO transferencia,
            @Context HttpServletRequest httpRequest) {
    	
    	Boolean isSuccess = transferenciaFacade.transferirSolicitacao(transferencia.getIdSolicitacao(), transferencia.getIdConcorrente());
    		
    	return Response.status(Status.OK).entity(isSuccess).build();
    }
    
    @PUT
    @Path("/indicarColeta")
    public Response iniciar(
            @QueryParam("idSolicitacao") Long idSolicitacao,
            @Context HttpServletRequest httpRequest) {
    	
    	Solicitacao solicitacao = processaSolicitacaoFacade.indicarColeta(idSolicitacao);
    	
    	return Response.status(Status.OK).entity(solicitacao).build();
    }
	
}
