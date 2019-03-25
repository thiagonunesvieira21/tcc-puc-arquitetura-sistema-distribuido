package br.pucminas.logistica.seguranca;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;

import br.pucminas.logistica.config.SecuritySettings;

public class KeycloakHelper {
    public static final String AUTH_DATA_PARAM_NAME = "org.keycloak.json.adapterConfig";

    /**
     * realm
     */
    private String realm;
    /**
     * ID do cliente
     */
    private String idCliente;
    /**
     * URL do servidor de autorização
     */
    private String urlServidorAutorizacao;

    public KeycloakHelper(ServletContext servletContext, SecuritySettings securitySettings) {
        realm = null;
        idCliente = null;
        urlServidorAutorizacao = null;

        String json = servletContext.getInitParameter(AUTH_DATA_PARAM_NAME);
        if (json == null) {
            return;
        }

        InputStream is = new ByteArrayInputStream(json.getBytes());
        KeycloakDeployment deployment = KeycloakDeploymentBuilder.build(is);
        realm = deployment.getRealm();
        idCliente = deployment.getResourceName();
        urlServidorAutorizacao = securitySettings.getUrlServAuth();
        if (urlServidorAutorizacao.length() == 0) {
            urlServidorAutorizacao = deployment.getAuthServerBaseUrl();
        }
    }

    /**
     * Obtém o realm
     *
     * @return realm
     */
    public String getRealm() {
        return realm;
    }

    /**
     * Setar o realm
     *
     * @param realm
     */
    public void setRealm(String realm) {
        this.realm = realm;
    }

    /**
     * Obtém o ID do cliente
     *
     * @return ID do cliente
     */
    public String getIdCliente() {
        return idCliente;
    }

    /**
     * Setar o ID do cliente
     *
     * @param ID do cliente
     */
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Obtém a URL do servidor de autorização
     *
     * @return URL do servidor de autorização
     */
    public String getUrlServidorAutorizacao() {
        if ((urlServidorAutorizacao.length() > 0) &&
                (urlServidorAutorizacao.charAt(urlServidorAutorizacao.length() - 1) == '/')) {
            urlServidorAutorizacao = urlServidorAutorizacao.substring(0,
                    urlServidorAutorizacao.length() - 1);
        }

        return urlServidorAutorizacao;
    }

    /**
     * Setar a URL do servidor de autorização
     *
     * @param URL do servidor de autorização
     */
    public void setUrlServidorAutorizacao(String urlServidorAutorizacao) {
        this.urlServidorAutorizacao = urlServidorAutorizacao;
    }
}
