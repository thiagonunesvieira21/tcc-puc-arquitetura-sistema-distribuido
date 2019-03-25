package br.pucminas.logistica.web;

import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.keycloak.KeycloakPrincipal;

import br.pucminas.logistica.seguranca.UsuarioAutenticadoHelper;

/**
 * Filtro de autorização responsável por traduzir Perfis que vem do
 * Keycloak (provavelmente recuperados de um LDAP) em Recursos, ou
 * seja, nomes específicos utilizados para autorizar execução dos
 * Web Services da API deste back-end.
 * <p>
 * Para garantir performance, o mapeamento de Perfis em Recursos
 * passa por um mecanismo de cache.
 *
 */
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {
    /**
     * a constante LOG
     */
    private static final Logger LOG = Logger.getLogger(AuthorizationFilter.class.getName());


    public AuthorizationFilter() {
        super();

        LOG.info("Iniciando filtro de Keycloak Authorization ...");
    }

    @Override
    public void filter(ContainerRequestContext requestContext) {
        // Não requer este esforço nas requisições de OPTIONS
        if (requestContext.getMethod().equals("OPTIONS")) {
            return;
        }

        final SecurityContext securityContext = requestContext.getSecurityContext();
        // UserPrincipal não esperado
        if (!(securityContext.getUserPrincipal() instanceof KeycloakPrincipal)) {
            return;
        }

        // Recupera todos os dados envolvidos
        UsuarioAutenticadoHelper usuarioAutenticadoHelper =
                new UsuarioAutenticadoHelper(securityContext.getUserPrincipal());

        // Mapeia os recursos relacionados com os perfis recebidos no token
        usuarioAutenticadoHelper.carregarRecursosUsuario();

        // Redefine o contexto de segurança
        requestContext.setSecurityContext(new CustomSecurityContext(
                usuarioAutenticadoHelper.getKeycloakPrincipal(),
                usuarioAutenticadoHelper.getRecursosUsuario(),
                securityContext.isSecure(),
                securityContext.getAuthenticationScheme()));
        LOG.info("Mapeamento de perfis para recursos realizado com sucesso para o " +
                "usuário '" + usuarioAutenticadoHelper.getLoginUsuario() + "'");
    }
}
