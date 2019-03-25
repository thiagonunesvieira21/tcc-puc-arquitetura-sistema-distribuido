package br.pucminas.persistencia.interfaces;

import java.util.List;

import br.pucminas.comum.persistencia.IBaseRepository;
import br.pucminas.modelo.Avaliacao;

public interface IAvaliacaoRepository extends IBaseRepository<Avaliacao, Long> {

	List<Avaliacao> consultar();

}
