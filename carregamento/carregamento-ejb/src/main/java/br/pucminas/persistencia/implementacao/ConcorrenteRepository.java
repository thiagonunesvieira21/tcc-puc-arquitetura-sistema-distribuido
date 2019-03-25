package br.pucminas.persistencia.implementacao;

import javax.annotation.Priority;
import javax.interceptor.Interceptor;

import br.pucminas.comum.persistencia.BaseRepository;
import br.pucminas.modelo.Concorrente;
import br.pucminas.persistencia.interfaces.IConcorrenteRepository;

@Priority(Interceptor.Priority.APPLICATION + 10)
public class ConcorrenteRepository extends BaseRepository<Concorrente, Long> implements IConcorrenteRepository {

}
