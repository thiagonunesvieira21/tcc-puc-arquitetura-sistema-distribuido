package br.pucminas.logistica.config;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletConfig;

/**
 * Configura o contexto de segurança web
 */
public class SecurityWebConfig extends BaseConfig {
    private static final long serialVersionUID = 1L;
    /**
     * a constante LOG
     */
    private static final Logger LOG = Logger.getLogger(SecurityWebConfig.class.getName());

    @Inject
    private SecuritySettings securitySettings;

    @Override
    protected void onStartup(ServletConfig servletConfig) {
        LOG.info("Timeout da sessão WEB: " + securitySettings.getTempoVidaToken());
    }
}
