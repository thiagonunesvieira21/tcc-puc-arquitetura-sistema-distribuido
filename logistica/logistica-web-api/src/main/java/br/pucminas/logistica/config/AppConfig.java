package br.pucminas.logistica.config;

import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;

/**
 * Classe AppConfig
 *
 */
@WebServlet(name = "AppConfig", loadOnStartup = 1)
public class AppConfig extends BaseConfig {
    private static final long serialVersionUID = 1L;
    
    /**
     * a constante LOG
     */
    private static final Logger LOG = Logger.getLogger(AppConfig.class.getName());

    @Override
    protected void onStartup(ServletConfig servletConfig) {
        LOG.info("Contexto de aplicação iniciado");
    }
}
