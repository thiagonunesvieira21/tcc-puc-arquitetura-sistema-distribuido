package br.pucminas.ws;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import br.pucminas.dto.SolicitacaoCadastroResponse;
import br.pucminas.dto.SolicitacaoComCadastroDTO;
import br.pucminas.dto.SolicitacaoDTO;
import br.pucminas.dto.SolicitacaoResponse;
import br.pucminas.modelo.Solicitacao;
import br.pucminas.servico.facade.ProcessaSolicitacaoFacade;

@Stateless
@WebService
public class SolicitanteWS implements Serializable {
	private static final long serialVersionUID = -2557419345659544378L;

	@EJB
	private ProcessaSolicitacaoFacade solicitacaoFacade;
	
	@WebMethod(operationName="registar")
	public SolicitacaoResponse create(SolicitacaoDTO solicitacao) {
		Solicitacao result  = solicitacaoFacade.registarClassificar(solicitacao);
		
		return new SolicitacaoResponse(result);
	}
	
	
	@WebMethod(operationName="cadastrarRegistar")
	public SolicitacaoCadastroResponse create(SolicitacaoComCadastroDTO solicitacao) {
		try {
			solicitacao.setUserId(this.createUserKeycloak(solicitacao));

			Solicitacao result = solicitacaoFacade.registarClassificar(solicitacao);
			
			return new SolicitacaoCadastroResponse(result);
		} catch (Exception e) {
			throw new RuntimeException("O username já existe em nossa base de dados");
		}
	}

	@WebMethod(exclude=true)
	public String createUserKeycloak(SolicitacaoComCadastroDTO dto) throws Exception {
		
		Keycloak keycloak = KeycloakBuilder.builder()
				.serverUrl("http://10.0.75.2:8080/auth")
				.realm("master")
				.username("admin")
				.password("Admin#1234")
				.clientId("admin-cli")
				.resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).register(new CustomJacksonProvider ()).build())
				.build();
		
		// Define password credential
		CredentialRepresentation passwordCred = new CredentialRepresentation();
		passwordCred.setTemporary(false);
		passwordCred.setType(CredentialRepresentation.PASSWORD);
		passwordCred.setValue(dto.getPassword());
			
		// Define user
		UserRepresentation user = new UserRepresentation();
		user.setEnabled(true);
		user.setUsername(dto.getUsername());
		user.setFirstName(dto.getFirstname());
		user.setLastName(dto.getLastname());
		user.setEmail(dto.getEmail());

		// Get realm
		RealmResource realmResource = keycloak.realm("master");
		UsersResource userRessource = realmResource.users();

		// Create user (requires manage-users role)
		Response response = userRessource.create(user);
		
		if(response.getStatus()==Status.CONFLICT.getStatusCode()) {
			throw new Exception(Status.CONFLICT.name());
		}
		
		String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

		return userId;
	}
}

class CustomJacksonProvider extends ResteasyJackson2Provider { } 
