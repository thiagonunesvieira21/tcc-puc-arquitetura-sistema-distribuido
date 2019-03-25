package br.pucminas.servico.task;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import br.pucminas.dto.EmailDTO;
import br.pucminas.modelo.EventoSolicitacao;
import br.pucminas.modelo.Solicitacao;
import br.pucminas.modelo.enuns.SituacaoTransporteEnum;
import br.pucminas.servico.EmailAS;
import br.pucminas.servico.EventoSolicitacaoAS;
import br.pucminas.servico.SolicitacaoAS;

@Singleton
public class EnviarEventoSolicitacaoTask implements Serializable {
	private static final long serialVersionUID = 4303293858759580569L;

	@Resource(mappedName = "java:jboss/mail/Default")
	private Session mailSession;

	@EJB
	private EventoSolicitacaoAS eventoSolicitacaoAS;

	@EJB
	private SolicitacaoAS solicitacaoAs;
	
	@EJB
	private EmailAS emailAs;

	@Schedule(dayOfWeek = "*", hour = "*", minute = "*/1")
	private void obterEvento() throws AddressException {
		List<EventoSolicitacao> eventos = eventoSolicitacaoAS.consultaEventoSolicitacaoPendenteEnvio();

		enviarEventos(eventos);
	}

	@Asynchronous
	private void enviarEventos(List<EventoSolicitacao> eventos) throws AddressException {

		for (EventoSolicitacao evento : eventos) {
			StringBuilder msg = new StringBuilder();
			
			Solicitacao solicitacao = (Solicitacao) solicitacaoAs.consultarPorId(evento.getId().getNuSolicitacao());

			EmailDTO email = new EmailDTO();
		
			msg.append("   A solicitação nº ")
			.append(solicitacao.getId())
			.append(" mundou o status para ")
			.append( SituacaoTransporteEnum.getDes(evento.getId().getNuSituacao().intValue())).append(".")
			.append("\n\nAtt,  ")
			.append("Sistema de Logística.");
			
			email.setContent(msg.toString());
			email.setTo(new InternetAddress[] { 
							new InternetAddress(solicitacao.getEmailDestinatario()),
							new InternetAddress(solicitacao.getEmailSolicitante()),
							new InternetAddress(solicitacao.getEmailRemetente())
						});
			email.setSubject("Ateração de Status da Solicitação Nº "+solicitacao.getId());
			
			emailAs.enviar(email);
			
			evento.setDhEnvioEvento(LocalDateTime.now());
			eventoSolicitacaoAS.alterar(evento);
		}
	}

}
