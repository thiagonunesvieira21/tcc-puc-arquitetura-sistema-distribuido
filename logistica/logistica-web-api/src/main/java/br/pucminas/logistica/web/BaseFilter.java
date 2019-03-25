package br.pucminas.logistica.web;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Classe BaseFilter
 */
public abstract class BaseFilter implements Filter {
    private boolean isAddCorsHeaders;

    public BaseFilter(boolean isAddCorsHeaders) {
        this.isAddCorsHeaders = isAddCorsHeaders;
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (isAddCorsHeaders) {
            // Adicionar os cabeçalhos CORS, caso não tenham sido adicionados
            addCorsHeaders(request, response);
        }

        if (onFilter(request, response)) {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }

    public void addCorsHeaders(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        if (request.getMethod().equals("OPTIONS")) {
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers",
                    "Origin, Authorization, X-Requested-With, Accept, Content-Type, " +
                            "Access-Control-Allow-Credentials, " +
                            "Access-Control-Request-Headers, " +
                            "Access-Control-Request-Method");
        }
    }

    protected abstract boolean onFilter(HttpServletRequest request,
                                        HttpServletResponse response) throws IOException;

    protected String getRequestUrl(HttpServletRequest request) {
        return request.getRequestURI().substring(
                request.getServletContext().getContextPath().length());
    }
}
