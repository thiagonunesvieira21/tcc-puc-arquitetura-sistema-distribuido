package br.pucminas.dto;

import br.pucminas.modelo.Solicitacao;

public class SolicitacaoResponse {
	private String procotolo;
	
	public SolicitacaoResponse(Solicitacao solicitacao) {
		super();
		this.procotolo = solicitacao.getId().toString();
	}

	public String getProcotolo() {
		return procotolo;
	}

	public void setProcotolo(String procotolo) {
		this.procotolo = procotolo;
	}
}
