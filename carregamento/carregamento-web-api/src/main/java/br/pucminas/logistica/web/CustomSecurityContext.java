package br.pucminas.logistica.web;

import java.security.Principal;
import java.util.Collection;
import java.util.logging.Logger;

import javax.ws.rs.core.SecurityContext;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;

public class CustomSecurityContext implements SecurityContext {
    /**
     * a constante LOG
     */
    private static final Logger LOG = Logger.getLogger(CustomSecurityContext.class.getName());

    private final KeycloakPrincipal<KeycloakSecurityContext> keycloakPrincipal;
    private final Collection<String> resources;
    private final boolean isSecure;
    private final String authenticationScheme;

    public CustomSecurityContext(
            KeycloakPrincipal<KeycloakSecurityContext> keycloakPrincipal,
            Collection<String> resources, boolean isSecure,
            String authenticationScheme) {
        this.keycloakPrincipal = keycloakPrincipal;
        this.resources = resources;
        this.isSecure = isSecure;
        this.authenticationScheme = authenticationScheme;
    }

    @Override
    public Principal getUserPrincipal() {
        return keycloakPrincipal;
    }

    @Override
    public boolean isUserInRole(String role) {
        boolean achou = resources.contains(role);
        LOG.info("Verificação de acesso ao recurso '" + role + "' pelo usuário " +
                keycloakPrincipal.getName() + ": " + (achou ? "OK" : "Não OK"));

        return achou;
    }

    @Override
    public boolean isSecure() {
        return isSecure;
    }

    @Override
    public String getAuthenticationScheme() {
        return authenticationScheme;
    }
}
