package br.pucminas.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.pucminas.comum.modelo.IEntidade;

@Entity
@Table(name = "tb01_tarifa_frete")
@NamedQuery(name=TarifaFrete.MEDIA, query="SELECT AVG(o.vrTarifaFrete) FROM TarifaFrete o")
public class TarifaFrete implements IEntidade, Serializable {
	private static final long serialVersionUID = -6739587319386285213L;
	public static final String MEDIA = "MEDIA";
	public static final Long KM = 60L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nu_tarifa_frete")
	private Long id;

	@Column(name = "vr_tarifa_frete")
	private BigDecimal vrTarifaFrete;

	@Column(name = "qt_distancia_concorrente")
	private Long qtDistanciaConcorrente;

	@Column(name = "qt_distancia")
	private Long qtDistancia;
	
	@Column(name = "co_cep_origem")
	private String cepOrigem;
	
	@Column(name = "co_cep_destino")
	private String cepDestino;
	
	@Transient
	private Long tempoEntrega;
	
	@Transient
	private BigDecimal valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getVrTarifaFrete() {
		return vrTarifaFrete;
	}

	public void setVrTarifaFrete(BigDecimal vrTarifaFrete) {
		this.vrTarifaFrete = vrTarifaFrete;
	}

	public Long getQtDistanciaConcorrente() {
		return qtDistanciaConcorrente;
	}

	public void setQtDistanciaConcorrente(Long qtDistanciaConcorrente) {
		this.qtDistanciaConcorrente = qtDistanciaConcorrente;
	}

	public Long getQtDistancia() {
		return qtDistancia;
	}

	public void setQtDistancia(Long qtDistancia) {
		this.qtDistancia = qtDistancia;
	}

	public String getCepOrigem() {
		return cepOrigem;
	}

	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}

	public String getCepDestino() {
		return cepDestino;
	}

	public void setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
	}
	
	public Long getTempoEntrega() {
		if(qtDistancia!=null) {
			tempoEntrega = (Long) (((qtDistancia * 2) / KM) / 24);
		}
		return tempoEntrega;
	}

	public BigDecimal getValor() {
		if(qtDistancia!=null) {
			valor = new BigDecimal(qtDistancia).multiply(vrTarifaFrete); 
		}
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setTempoEntrega(Long tempoEntrega) {
		this.tempoEntrega = tempoEntrega;
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
		TarifaFrete other = (TarifaFrete) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
