package br.pucminas.servico;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.pucminas.dto.EmailDTO;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EmailAS implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource(mappedName = "java:jboss/mail/Default")
	private Session mailSession;
	
	public void enviar(EmailDTO email) {
		
		try {
			MimeMessage m = new MimeMessage(mailSession);
			Address from = new InternetAddress(System.getProperty("ADDRESS_EMAIL_FROM"));
			Address[] to = email.getTo();

			m.setFrom(from);
			m.setRecipients(Message.RecipientType.TO, to);
			m.setSubject(email.getSubject());
			m.setSentDate(new java.util.Date());
			m.setContent(email.getContent(), "text/plain");
			Transport.send(m);
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
		
	}

}
