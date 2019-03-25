package br.pucminas.logistica.web;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Context
    private HttpHeaders headers;

    public Response toResponse(WebApplicationException e) {
        return Response
                .status(e.getResponse().getStatus())
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Origin", getRequestHeader("Origin"))
                .entity(e.getMessage())
                .build();
    }

    private String getRequestHeader(String name) {
        List<String> headersList = headers.getRequestHeader(name);

        if ((headersList == null) || (headersList.isEmpty())) {
            return null;
        }

        return headersList.get(0);
    }
}
