package br.pucminas.dto;

import br.pucminas.modelo.Solicitacao;

public class SolicitacaoCadastroResponse extends SolicitacaoResponse {
	
	public SolicitacaoCadastroResponse(Solicitacao solicitacao) {
		super(solicitacao);
		this.userId = solicitacao.getUserId();
	}

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
