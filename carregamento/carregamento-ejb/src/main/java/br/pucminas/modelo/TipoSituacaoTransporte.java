package br.pucminas.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.pucminas.comum.modelo.IEntidade;

/**
 * Entity implementation class for Entity: TipoSituacaoTransporte
 *
 */
@Entity
@Table(name="tb02_tipo_situacao_transporte")
public class TipoSituacaoTransporte implements IEntidade, Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="nu_tipo_situacao_transporte")
	private Long id;
	
	@Column(name="no_tipo_situacao_transporte")
	private String nome;

	public TipoSituacaoTransporte() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		TipoSituacaoTransporte other = (TipoSituacaoTransporte) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
   
}
