package br.pucminas.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.pucminas.comum.modelo.IEntidade;

/**
 * Entity implementation class for Entity: Concorrente
 *
 */
@Entity
@Table(name="tb04_empresa_concorrente")
public class Concorrente implements IEntidade, Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="nu_empresa_concorrente")
	private Long id;
	
	@Column(name="no_empresa_concorrente")
	private String nome;
	
	@Column(name="co_cnpj_empresa")
	private String cnpj;
	
	@Column(name="co_endereco_sistema")
	private String enderecoSistema;
	
	@Column(name="co_usuario_sistema")
	private String usuarioSistema;
	
	@Column(name="co_senha_sistema")
	private String senhaSistema;
	
	@Column(name="vr_tarifa_frete")
	private BigDecimal vrTarifa;

	public Concorrente() {
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
	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}   
	public String getEnderecoSistema() {
		return this.enderecoSistema;
	}

	public void setEnderecoSistema(String enderecoSistema) {
		this.enderecoSistema = enderecoSistema;
	}   
	public String getUsuarioSistema() {
		return this.usuarioSistema;
	}

	public void setUsuarioSistema(String usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
	}  
	
	public String getSenhaSistema() {
		return this.senhaSistema;
	}

	public void setSenhaSistema(String senhaSistema) {
		this.senhaSistema = senhaSistema;
	}
	
	public BigDecimal getVrTarifa() {
		return vrTarifa;
	}
	public void setVrTarifa(BigDecimal vrTarifa) {
		this.vrTarifa = vrTarifa;
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
		Concorrente other = (Concorrente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
   
}
