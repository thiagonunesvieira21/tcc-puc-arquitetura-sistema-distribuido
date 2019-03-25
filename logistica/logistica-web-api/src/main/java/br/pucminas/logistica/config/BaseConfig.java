package br.pucminas.logistica.config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class BaseConfig extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);

        onStartup(servletConfig);
    }

    @Override
    public void destroy() {
        onDestroy();
    }

    protected void onStartup(ServletConfig servletConfig) {
    }

    protected void onDestroy() {
    }
}
