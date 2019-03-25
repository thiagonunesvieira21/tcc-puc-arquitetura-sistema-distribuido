package br.pucminas.persistencia.implementacao;

import javax.annotation.Priority;
import javax.interceptor.Interceptor;

import br.pucminas.comum.persistencia.BaseRepository;
import br.pucminas.modelo.TipoSituacaoTransporte;
import br.pucminas.persistencia.interfaces.ITipoSituacaoTransporteRepository;

@Priority(Interceptor.Priority.APPLICATION + 10)
public class TipoSituacaoTransporteRepository extends BaseRepository<TipoSituacaoTransporte, Long> implements ITipoSituacaoTransporteRepository {

}
