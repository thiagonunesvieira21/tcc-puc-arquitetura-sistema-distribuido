package br.pucminas.comum.servico;

import java.io.Serializable;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.pucminas.comum.modelo.IEntidade;
import br.pucminas.comum.persistencia.IBaseRepository;

@SuppressWarnings("unchecked")
public abstract class BaseApplicationService {
	
	@SuppressWarnings("rawtypes")
	public abstract IBaseRepository getRepository();

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public IEntidade salvar(IEntidade entidade) {
		getRepository().persist(entidade);
		return entidade;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public IEntidade alterar(IEntidade entidade) {
		getRepository().merge(entidade);
		return entidade;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void excluir(IEntidade entidade) {
		getRepository().remove(entidade);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void excluirPorId(Serializable pk) {
		getRepository().removeById(pk);
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Object consultarPorId(Serializable pk) {
		return getRepository().consultarPorId(pk);
	}
	
	@SuppressWarnings("rawtypes")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List consultarTodos() {
		return getRepository().consultarTodos();
	}
}
