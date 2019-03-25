package br.pucminas.servico;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import br.pucminas.comum.persistencia.IBaseRepository;
import br.pucminas.comum.servico.BaseApplicationService;
import br.pucminas.dto.EmailDTO;
import br.pucminas.modelo.Avaliacao;
import br.pucminas.persistencia.interfaces.IAvaliacaoRepository;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class AvaliacaoAS extends BaseApplicationService {

	@Inject
	private IAvaliacaoRepository repository;
	
	@EJB
	private EmailAS emailAs;
	
	@Override
	public IBaseRepository<Avaliacao, Long> getRepository() {
		return repository;
	}
	
	public List<Avaliacao> consultar() {
        return (List<Avaliacao>) repository.consultar();
	}
	
	public Avaliacao salvar(Avaliacao avaliacao) {
		Avaliacao ava = (Avaliacao) super.salvar(avaliacao);
		
		EmailDTO dto = new EmailDTO();
		
		try {
			dto.setTo(
						new InternetAddress[] { 
							new InternetAddress(ava.getEmailContatoAvaliador()),
						}
					);
		} catch (AddressException e) {
			e.printStackTrace();
		}
		
		dto.setContent("	Prezados,\n Obrigado por avaliar a solicitação Nº "+ avaliacao.getSolicitacao());
		dto.setSubject("Avaliação da entrega Nº "+ avaliacao.getSolicitacao());
		
		return ava;
	}

}
