package br.pucminas.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.pucminas.comum.modelo.IEntidade;
import br.pucminas.dto.AvaliacaoDTO;

@Entity
@Table(name = "tb02_avaliacao_solicitacao")
@NamedQueries({
	@NamedQuery(name=Avaliacao.ALL_AVALIACAO, query="SELECT o FROM Avaliacao o ORDER BY o.dhAvaliacao DESC")
})
public class Avaliacao implements IEntidade, Serializable {
	private static final long serialVersionUID = -6739587319386285213L;
	public static final String ALL_AVALIACAO = "ALL_AVALIACAO";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="nu_avaliacao_entrega")
	private Long id;
	
	@Column(name="nu_solicitacao_transporte")
	private Long solicitacao;
	
	@Column(name="dh_avaliacao")
	private LocalDateTime dhAvaliacao;
	
	@Column(name="vr_nota_avaliacao")
	private Long nota;
	
	@Column(name="de_avaliacao")
	private String deAvaliacao;
	
	@Column(name="co_email_avaliador")
	private String emailContatoAvaliador;
	
	public Avaliacao() {
		super();
	}

	public Avaliacao(AvaliacaoDTO dto) {
		super();
		
		this.deAvaliacao = dto.getDeAvaliacao();
		this.nota = dto.getNota();
		this.emailContatoAvaliador = dto.getEmailContatoAvaliador();
		this.solicitacao = dto.getSolicitacao();
		this.dhAvaliacao = LocalDateTime.now();
	}

	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Long solicitacao) {
		this.solicitacao = solicitacao;
	}

	public LocalDateTime getDhAvaliacao() {
		return dhAvaliacao;
	}

	public void setDhAvaliacao(LocalDateTime dhAvaliacao) {
		this.dhAvaliacao = dhAvaliacao;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Avaliacao other = (Avaliacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
