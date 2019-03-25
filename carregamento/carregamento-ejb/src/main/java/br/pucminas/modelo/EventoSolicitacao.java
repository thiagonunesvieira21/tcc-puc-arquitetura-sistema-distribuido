package br.pucminas.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.pucminas.comum.modelo.IEntidade;

@Entity
@Table(name = "tb01_evento_solicitacao", schema="controle_logistica")
@NamedQueries({
	@NamedQuery(name=EventoSolicitacao.PENDENTES_ENVIO, query="SELECT o FROM EventoSolicitacao o WHERE o.dhEnvioEvento IS NULL")
})
public class EventoSolicitacao implements IEntidade, Serializable {
	
	private static final long serialVersionUID = -9084482418847540526L;
	public static final String PENDENTES_ENVIO = "EventoSolicitacao.findPendenteEnvio";
	
	@EmbeddedId
	private EventoSolicitacaoPk id;
	
	@Column(name="dh_envio_evento")
	private LocalDateTime dhEnvioEvento;

	public EventoSolicitacaoPk getId() {
		return id;
	}

	public void setId(EventoSolicitacaoPk id) {
		this.id = id;
	}

	public LocalDateTime getDhEnvioEvento() {
		return dhEnvioEvento;
	}

	public void setDhEnvioEvento(LocalDateTime dhEnvioEvento) {
		this.dhEnvioEvento = dhEnvioEvento;
	}

}
