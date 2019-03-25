package br.pucminas.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EventoSolicitacaoPk implements Serializable {
	private static final long serialVersionUID = 6930275500399132932L;

	@Column(name="nu_solicitacao_evento")
	private Long nuSolicitacao;
	
	@Column(name="nu_tipo_situacao_transporte")
	private Long nuSituacao;
	
	@Column(name="dh_registro")
	private LocalDateTime dhRegistro;

	public Long getNuSolicitacao() {
		return nuSolicitacao;
	}

	public void setNuSolicitacao(Long nuSolicitacao) {
		this.nuSolicitacao = nuSolicitacao;
	}

	public Long getNuSituacao() {
		return nuSituacao;
	}

	public void setNuSituacao(Long nuSituacao) {
		this.nuSituacao = nuSituacao;
	}

	public LocalDateTime getDhRegistro() {
		return dhRegistro;
	}

	public void setDhRegistro(LocalDateTime dhRegistro) {
		this.dhRegistro = dhRegistro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dhRegistro == null) ? 0 : dhRegistro.hashCode());
		result = prime * result + ((nuSituacao == null) ? 0 : nuSituacao.hashCode());
		result = prime * result + ((nuSolicitacao == null) ? 0 : nuSolicitacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventoSolicitacaoPk other = (EventoSolicitacaoPk) obj;
		if (dhRegistro == null) {
			if (other.dhRegistro != null)
				return false;
		} else if (!dhRegistro.equals(other.dhRegistro))
			return false;
		if (nuSituacao == null) {
			if (other.nuSituacao != null)
				return false;
		} else if (!nuSituacao.equals(other.nuSituacao))
			return false;
		if (nuSolicitacao == null) {
			if (other.nuSolicitacao != null)
				return false;
		} else if (!nuSolicitacao.equals(other.nuSolicitacao))
			return false;
		return true;
	}
	
}
