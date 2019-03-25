package br.pucminas.logistica.resource;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.pucminas.dto.AvaliacaoDTO;
import br.pucminas.logistica.web.BaseRestJson;
import br.pucminas.modelo.Avaliacao;
import br.pucminas.modelo.Solicitacao;
import br.pucminas.servico.AvaliacaoAS;
import br.pucminas.servico.SolicitacaoAS;

@Path("avaliacao")
public class AvaliacaoRS extends BaseRestJson {

	@EJB
	private SolicitacaoAS solicitacaoAS;

	@EJB
	private AvaliacaoAS service;
	
    @GET
    @Path("/solicitacao/consultar")
    public List<Solicitacao> consultarPaginado(
            @Context HttpServletRequest httpRequest) {

        return solicitacaoAS.consultaSolicitacaoEntregues();
    }
    
    @POST
    public Response transferir(
    		AvaliacaoDTO dto,
            @Context HttpServletRequest httpRequest) {
    	
    	Avaliacao avaliacao = (Avaliacao) service.salvar(new Avaliacao(dto));
    		
    	return Response.status(Status.OK).entity(avaliacao).build();
    }
    
    @GET
    public List<Avaliacao> consultar(
            @Context HttpServletRequest httpRequest) {

        return service.consultar();
    }

}
