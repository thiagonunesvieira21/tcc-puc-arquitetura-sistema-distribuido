package br.pucminas.persistencia.implementacao;

import java.util.List;

import javax.annotation.Priority;
import javax.interceptor.Interceptor;

import br.pucminas.comum.persistencia.BaseRepository;
import br.pucminas.modelo.Avaliacao;
import br.pucminas.persistencia.interfaces.IAvaliacaoRepository;

@Priority(Interceptor.Priority.APPLICATION + 10)
public class AvaliacaoRepository extends BaseRepository<Avaliacao, Long> implements IAvaliacaoRepository {

	@Override
	public List<Avaliacao> consultar() {
		return (List<Avaliacao>) consultar(Avaliacao.ALL_AVALIACAO);
	}

}
