package br.pucminas.logistica.seguranca;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;

public class UsuarioAutenticadoHelper {
    private KeycloakPrincipalHelper keycloakPrincipalHelper;
    private Collection<String> recursosUsuario;

    public UsuarioAutenticadoHelper(final Principal userPrincipal) {
        keycloakPrincipalHelper = new KeycloakPrincipalHelper(userPrincipal);
        recursosUsuario = null;
    }

    /**
     * Mapeia os recursos relacionados com os perfis recebidos no token
     */
    public void carregarRecursosUsuario() {
        recursosUsuario = new ArrayList<>();
        for (String perfil : getPerfisUsuario()) {
                recursosUsuario.add(perfil);
        }
    }

    public AccessToken getTokenAcesso() {
        return keycloakPrincipalHelper.getAccessToken();
    }

    public String getLoginUsuario() {
        return keycloakPrincipalHelper.getUserLogin();
    }

    public String getNomeUsuario() {
        return keycloakPrincipalHelper.getUserName();
    }

    public String getEmailUsuario() {
        return keycloakPrincipalHelper.getUserEmail();
    }

    public String getMatriculaUsuario() {
        return keycloakPrincipalHelper.getUserEmployeeId();
    }

    public String getCpfUsuario() {
        return keycloakPrincipalHelper.getUserPersonalId();
    }

    public Collection<String> getPerfisUsuario() {
        return keycloakPrincipalHelper.getUserRoles();
    }

    public Collection<String> getRecursosUsuario() {
        return recursosUsuario;
    }

    public String getIdUsuarioKeycloak() {
        return keycloakPrincipalHelper.getUserId();
    }

    public KeycloakPrincipal<KeycloakSecurityContext> getKeycloakPrincipal() {
        return keycloakPrincipalHelper.getKeycloakPrincipal();
    }
}
