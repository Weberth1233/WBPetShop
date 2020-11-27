package br.unitins.petshop.repository;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.unitins.petshop.application.JPAUtil;
import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.model.DefaultEntity;

public class Repository <T extends DefaultEntity<? super T>> {
	private EntityManager entityManager;

	public Repository() {
		this(JPAUtil.getEntityManager());
	}
	public Repository(EntityManager em) {
		setEntityManager(em);
	}
	public void beginTransaction() {
		try {
			getEntityManager().getTransaction().begin();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void rollbackTransaction(){
		try {
			getEntityManager().getTransaction().rollback();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void commitTransaction() throws RepositoryException {
		try {
			getEntityManager().getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar o commit");
		}
	}
	public T salvar(T entity) throws RepositoryException{
		try {
			return getEntityManager().merge(entity);
		}
		catch(PersistenceException e) {
			System.out.println("Informações duplicadas");
			e.printStackTrace();
			throw new RepositoryException("Algum dos seguintes registro estão duplicados:Nome, CPF ou E-mail");
		}
		catch (Exception e) {
			System.out.println("Erro ao salvar!");
			e.printStackTrace();
			throw new RepositoryException("Erro ao salvar!");
		}
	}
	public void salvarPersistence(T entity) throws RepositoryException{
		try {
			getEntityManager().persist(entity);;
		}
		catch(PersistenceException e) {
			System.out.println("Informações duplicadas");
			e.printStackTrace();
			throw new RepositoryException("Algum dos seguintes registro estão duplicados:Nome, CPF ou E-mail");
		}
		catch (Exception e) {
			System.out.println("Erro ao salvar!");
			e.printStackTrace();
			throw new RepositoryException("Erro ao salvar!");
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
	public T findById(Integer id)  {
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
