package br.pucminas.dto;

public class AvaliacaoDTO {
	private Long solicitacao, nota;
	
	private String deAvaliacao, emailContatoAvaliador;

	public Long getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Long solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Long getNota() {
		return nota;
	}

	public void setNota(Long nota) {
		this.nota = nota;
	}

	public String getDeAvaliacao() {
		return deAvaliacao;
	}

	public void setDeAvaliacao(String deAvaliacao) {
		this.deAvaliacao = deAvaliacao;
	}

	public String getEmailContatoAvaliador() {
		return emailContatoAvaliador;
	}

	public void setEmailContatoAvaliador(String emailContatoAvaliador) {
		this.emailContatoAvaliador = emailContatoAvaliador;
	}

}
