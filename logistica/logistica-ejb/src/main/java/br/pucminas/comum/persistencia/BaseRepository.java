package br.pucminas.comum.persistencia;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.pucminas.comum.modelo.IEntidade;

/**
 * Classe base de todos os repositórios
 *
 * @param <T>  o tipo genérico da entidade
 * @param <PK> o tipo genérico da chave da entidade
 */
public abstract class BaseRepository<T extends IEntidade, PK extends Serializable> {
    /**
     * entity manager
     */
    @PersistenceContext(unitName = "sislogDB")
    protected EntityManager entityManager;

    /**
     * Persiste a entidade
     *
     * @param entity
     */
    public void persist(T entity) {
        entityManager.persist(entity);
    }

    /**
     * Faz um merge da entidade
     *
     * @param entity
     */
    public void merge(T entity) {
        entityManager.merge(entity);
    }

    /**
     * Remove (exclui) a entidade
     *
     * @param entity
     */
    public void remove(T entity) {
        entityManager.remove(entity);
    }

    /**
     * Remove (exclui) pelo id
     *
     * @param id
     */
    public void removeById(PK id) {
        T entity = consultarPorId(id);

        entityManager.remove(entity);
    }

    /**
     * Consulta por id
     *
     * @param id
     * @return t
     */
    public T consultarPorId(PK id) {
        return entityManager.find(getTypeClass(), id);
    }

    /**
     * Consulta todos os registros
     *
     * @return list
     */
    @SuppressWarnings("unchecked")
    public List<T> consultarTodos() {
        return entityManager.createQuery("FROM " + getTypeClass().getName()).getResultList();
    }

    /**
     * Consulta total de registros
     *
     * @return total
     */
    public long consultarTotal() {
        return (long) entityManager.createQuery("SELECT COUNT(o) " + "FROM " + getTypeClass().getName() + " o")
                .getSingleResult();
    }

    /**
     * Consulta via JPQL
     *
     * @param hql
     * @return list
     */
    @SuppressWarnings("unchecked")
    protected List<T> consultar(String jpql) {
        return entityManager.createNamedQuery(jpql).getResultList();
    }

    /**
     * Consulta customizada deste tipo
     *
     * @return list
     */
    protected List<?> consultar(String jpql, Object... parameters) {
        Query q = entityManager.createNamedQuery(jpql);

        setParameters(q, parameters);

        return q.getResultList();
    }

    protected List<?> consultar(String jpql, Map<String, Object> parameters) {
        Query q = createQuery(jpql, parameters);

        return q.getResultList();
    }

    protected List<?> consultar(String jpql, Map<String, Object> parameters, int maxResult) {
        Query q = createQuery(jpql, parameters);
        q.setMaxResults(maxResult);
        return q.getResultList();
    }

    protected List<?> consultarPaginado(String jpql, Map<String, Object> parameters, PageRequest pageRequest) {
        Query q = createQuery(jpql, parameters);
        q.setFirstResult((pageRequest.getPageNumber() - 1) * pageRequest.getPageSize());
        q.setMaxResults(pageRequest.getPageSize());
        return q.getResultList();
    }

    protected Object consultarSingleResult(String jpql, Map<String, Object> parameters) {
        Query q = createQuery(jpql, parameters);
        return q.getSingleResult();
    }

    /**
     * Cria uma consulta JPQL
     *
     * @param query
     * @param parameters
     * @return query
     */
    protected Query createQuery(String query, Object... parameters) {
        Query q = entityManager.createQuery(query);

        setParameters(q, parameters);

        return q;
    }

    private void setParameters(Query q, Object[] parameters) {
        for (int i = 1; i <= parameters.length; i++) {
            q.setParameter(i, parameters[i - 1]);
        }
    }

    /**
     * Cria uma consulta JPQL
     *
     * @param query
     * @param parameters
     * @return query
     */
    protected Query createQuery(String query, Map<String, Object> parameters) {
        Query q = entityManager.createNamedQuery(query);

        return addParameters(parameters, q);
    }

    protected Query createNativeQuery(String query, Map<String, Object> parameters) {
        Query q = entityManager.createNativeQuery(query);

        return addParameters(parameters, q);
    }

    /**
     * Cria uma consulta nativa
     *
     * @param query
     * @param parameters
     * @return query
     */
    protected Query createNativeQuery(String query, Map<String, Object> parameters, Class<?> resultClass) {
        Query q = entityManager.createNativeQuery(query, resultClass);

        return addParameters(parameters, q);
    }

    /**
     * Obtém uma classe tipada
     *
     * @return type class
     */
    @SuppressWarnings("unchecked")
    protected Class<T> getTypeClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Adiciona parâmetros à uma consulta
     *
     * @param parameters
     * @param query
     * @return query
     */
    private Query addParameters(Map<String, Object> parameters, Query query) {
        Set<Entry<String, Object>> entrySet = parameters.entrySet();

        for (Entry<String, Object> param : entrySet) {
            query.setParameter(param.getKey(), param.getValue());
        }

        return query;
    }

    public boolean executeNamedQuery(String namedQuery, List<Object> params) {
        Query query = entityManager.createNamedQuery(namedQuery);

        for (int i = 0; i < params.size(); i++) {
            query.setParameter("param" + i, params.get(i)).toString();

        }

        return query.executeUpdate() == 1;
    }

    @SuppressWarnings("unchecked")
    public List<T> list(String namedQuery) {
        Query query = entityManager.createNamedQuery(namedQuery);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<T> list(String namedQuery, List<Object> params) {
        Query query = entityManager.createNamedQuery(namedQuery);

        for (int i = 0; i < params.size(); i++) {
            query.setParameter("param" + i, params.get(i));
        }

        return query.getResultList();
    }
}
