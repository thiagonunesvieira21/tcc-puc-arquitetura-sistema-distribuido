package br.pucminas.persistencia.interfaces;

import java.util.List;

import br.pucminas.comum.persistencia.IBaseRepository;
import br.pucminas.modelo.Solicitacao;

public interface ISolicitacaoRepository extends IBaseRepository<Solicitacao, Long> {

	Long countTotalRegistroPendente();

	List<?> consultarPaginadoPendente();

}
