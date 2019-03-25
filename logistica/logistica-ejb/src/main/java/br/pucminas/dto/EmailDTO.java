package br.pucminas.dto;

import javax.mail.Address;

public class EmailDTO {
	private Address[] to;
	private String subject, content;
	
	public Address[] getTo() {
		return to;
	}
	public void setTo(Address[] to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
