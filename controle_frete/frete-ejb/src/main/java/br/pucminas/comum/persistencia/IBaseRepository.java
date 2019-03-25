package br.pucminas.comum.persistencia;


import java.io.Serializable;
import java.util.List;

import br.pucminas.comum.modelo.IEntidade;

/**
 * Interface base de todos os repositórios
 *
 * @param <T>  o tipo genérico da entidade
 * @param <PK> o tipo genérico da chave da entidade
 * @author pablo.gandulfo
 */
public interface IBaseRepository<T extends IEntidade, PK extends Serializable> {
    /**
     * Persiste a entidade
     *
     * @param entity
     */
    void persist(T entity);

    /**
     * Faz um merge da entidade
     *
     * @param entity
     */
    void merge(T entity);

    /**
     * Remove (exclui) a entidade
     *
     * @param entity
     */
    void remove(T entity);

    /**
     * Remove (exclui) pelo id
     *
     * @param id
     */
    void removeById(PK id);

    /**
     * Consultar por id
     *
     * @param id
     * @return t
     */
    T consultarPorId(PK id);

    /**
     * Consultar todos os registros
     *
     * @return list
     */
    List<T> consultarTodos();

    /**
     * Consultar total
     *
     * @return total
     */
    long consultarTotal();

    boolean executeNamedQuery(String namedQuery, List<Object> params);

    List<T> list(String namedQuery);

    List<T> list(String namedQuery, List<Object> params);
}
