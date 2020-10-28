package br.unitins.petshop.repository;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import br.unitins.petshop.application.JPAUtil;
import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.model.DefaultEntity;

public class Repository <T extends DefaultEntity<T>> {
	private EntityManager entityManager;

	public Repository() {
		this(JPAUtil.getEntityManager());
	}
	public Repository(EntityManager em) {
		setEntityManager(em);
	}
	public void beginTransaction() throws RepositoryException {
		try {
			entityManager.getTransaction().begin();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void rollbackTransaction(){
		try {
			entityManager.getTransaction().rollback();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void commitTransaction() throws RepositoryException {
		try {
			entityManager.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar o commit");
		}
	}
	public void salvar(T entity) throws RepositoryException{
		try {
			getEntityManager().merge(entity);
		}catch (Exception e) {
			System.out.println("Erro ao salvar!");
			e.printStackTrace();
			throw new RepositoryException("Salvar deu errooooo!");
		}
	}
	public void remover(T entity) throws RepositoryException{
		try {
			T t = getEntityManager().merge(entity);
			getEntityManager().remove(t);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Remover deu errooooo!");
			throw new RepositoryException("Erro ao remover!");
		}
	}
	public T findById(Integer id) throws RepositoryException {
		// obtendo o tipo da classe de forma generica (a classe deve ser publica)
		final ParameterizedType type = 
				(ParameterizedType) getClass().getGenericSuperclass();
		Class<T> tClass = (Class<T>) (type).getActualTypeArguments()[0];

		T t = (T) getEntityManager().find(tClass, id);

		return t;
	}
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
