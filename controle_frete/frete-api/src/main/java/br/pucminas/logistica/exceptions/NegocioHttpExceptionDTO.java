package br.pucminas.logistica.exceptions;

import javax.ws.rs.core.Response.Status;

public class NegocioHttpExceptionDTO extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final Status responseStatus;

    public NegocioHttpExceptionDTO(Status responseStatus, String mensagem) {
        super(mensagem);

        this.responseStatus = responseStatus;
    }

    public int getStatusCode() {
        return responseStatus.getStatusCode();
    }
}
