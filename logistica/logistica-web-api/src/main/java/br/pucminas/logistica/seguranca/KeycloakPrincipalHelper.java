package br.pucminas.logistica.seguranca;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;

import br.pucminas.logistica.config.AppSettings;
import br.pucminas.logistica.exceptions.NegocioHttpExceptionDTO;

public class KeycloakPrincipalHelper {
    private KeycloakPrincipal<KeycloakSecurityContext> keycloakPrincipal;
    private AccessToken accessToken;
    private String userId;
    private String userLogin;
    private String userName;
    private String userEmail;
    private String userEmployeeId;
    private String userPersonalId;
    private Collection<String> userRoles;

    @SuppressWarnings("unchecked")
    public KeycloakPrincipalHelper(final Principal userPrincipal) {
        if (!(userPrincipal instanceof KeycloakPrincipal)) {
            throw new NegocioHttpExceptionDTO(BAD_REQUEST, "UserPrincipal não esperado");
        }

        keycloakPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) userPrincipal;
        accessToken = keycloakPrincipal.getKeycloakSecurityContext().getToken();
        userId = accessToken.getSubject();
        userLogin = accessToken.getPreferredUsername();
        userName = accessToken.getName();
        userEmail = accessToken.getEmail();
        userEmployeeId = accessToken.getPreferredUsername();
        userRoles = new ArrayList<>();
        if (accessToken.getRealmAccess() != null) {
            userRoles.addAll(accessToken.getRealmAccess().getRoles());
        }
        if (accessToken.getResourceAccess()
                .get(AppSettings.NOME_APLICACAO) != null) {
            userRoles.addAll(accessToken.getResourceAccess()
                    .get(AppSettings.NOME_APLICACAO).getRoles());
        }
    }

    public KeycloakPrincipal<KeycloakSecurityContext> getKeycloakPrincipal() {
        return keycloakPrincipal;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserEmployeeId() {
        return userEmployeeId;
    }

    public String getUserPersonalId() {
        return userPersonalId;
    }
    
    public Collection<String> getUserRoles() {
        return userRoles;
    }
}
