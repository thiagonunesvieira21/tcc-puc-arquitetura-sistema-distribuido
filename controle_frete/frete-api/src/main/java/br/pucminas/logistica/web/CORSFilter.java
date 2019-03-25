package br.pucminas.logistica.web;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Insere cabeçalhos CORS quando necessário
 * <p>
 * CORS = Cross Origin Resource Sharing
 */
@WebFilter(filterName = "CORSFilter", urlPatterns = {"/*"})
public class CORSFilter extends BaseFilter {
    /**
     * a constante LOG
     */
    private static final Logger LOG = Logger.getLogger(CORSFilter.class.getName());

    /**
     * Construtor
     */
    public CORSFilter() {
        super(true);

        LOG.info("Iniciando filtro CORS ...");
    }

    @Override
    public boolean onFilter(HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        // Os cabeçalhos já foram inseridos na classe ancestral
        LOG.info("Inserindo cabeçalhos CORS da requisição '" + getRequestUrl(request) + "' ...");

        // CORS handshake: responder requisições HTTP OPTIONS com código de
        // status ACCEPTED e não postergar mais a requisição na cadeia de
        // filtros
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return false;
        }

        return true;
    }
}
