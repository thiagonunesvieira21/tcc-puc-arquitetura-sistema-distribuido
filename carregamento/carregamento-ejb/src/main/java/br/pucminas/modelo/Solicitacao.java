package br.pucminas.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.pucminas.comum.modelo.IEntidade;

/**
 * Entity implementation class for Entity: Solicitacao
 *
 */
@Entity
@Table(name = "tb01_solicitacao_transporte")
@NamedQueries({
		@NamedQuery(name = Solicitacao.COUNT_SOLICITACAO_POR_SITUACAO, query = "SELECT COUNT(o) FROM Solicitacao o WHERE o.situacao.id = :situacao"),
		@NamedQuery(name = Solicitacao.SOLICITACAO_POR_SITUACAO, query = "SELECT o FROM Solicitacao o WHERE o.situacao.id = :situacao") })
public class Solicitacao implements IEntidade, Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SOLICITACAO_POR_SITUACAO = "Solicitacao.findBySitucao";
	public static final String COUNT_SOLICITACAO_POR_SITUACAO = "Solicitacao.CountFindBySitucao";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nu_solicitacao_transporte")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "nu_tipo_situacao_transporte")
	private TipoSituacaoTransporte situacao;

	@ManyToOne
	@JoinColumn(name = "nu_empresa_concorrente")
	private Concorrente concorrente;

	@Column(name = "dh_entrada_solicitacao")
	private LocalDateTime dataHoaraSolicitacao;

	@Column(name = "co_localizacao_origem")
	private String origem;

	@Column(name = "co_localizacao_destino")
	private String destino;

	@Column(name = "co_keycloak_id")
	private String userId;

	@Column(name = "de_tipo_excepcionalidade")
	private String tipoExcepcionalidade;

	@Column(name = "co_email_remetente")
	private String emailRemetente;

	@Column(name = "co_email_destinatario")
	private String emailDestinatario;

	@Column(name = "co_email_solicitante")
	private String emailSolicitante;

	@Column(name = "vr_frete")
	private BigDecimal valorFrete;
	
	@Column(name = "qt_prazo_entrega")
	private Long prazo;

	public Solicitacao() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoSituacaoTransporte getSituacao() {
		return this.situacao;
	}

	public void setSituacao(TipoSituacaoTransporte situacao) {
		this.situacao = situacao;
	}

	public Concorrente getConcorrente() {
		return this.concorrente;
	}

	public void setConcorrente(Concorrente concorrente) {
		this.concorrente = concorrente;
	}

	public LocalDateTime getDataHoaraSolicitacao() {
		return this.dataHoaraSolicitacao;
	}

	public void setDataHoaraSolicitacao(LocalDateTime dataHoaraSolicitacao) {
		this.dataHoaraSolicitacao = dataHoaraSolicitacao;
	}

	public String getOrigem() {
		return this.origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return this.destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getTipoExcepcionalidade() {
		return tipoExcepcionalidade;
	}

	public void setTipoExcepcionalidade(String tipoExcepcionalidade) {
		this.tipoExcepcionalidade = tipoExcepcionalidade;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmailRemetente() {
		return emailRemetente;
	}

	public void setEmailRemetente(String emailRemetente) {
		this.emailRemetente = emailRemetente;
	}

	public String getEmailDestinatario() {
		return emailDestinatario;
	}

	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}

	public String getEmailSolicitante() {
		return emailSolicitante;
	}

	public void setEmailSolicitante(String emailSolicitante) {
		this.emailSolicitante = emailSolicitante;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public Long getPrazo() {
		return prazo;
	}

	public void setPrazo(Long prazo) {
		this.prazo = prazo;
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
		Solicitacao other = (Solicitacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
