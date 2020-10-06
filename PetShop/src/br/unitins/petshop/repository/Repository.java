package br.unitins.petshop.repository;

import javax.persistence.EntityManager;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.model.DefaultEntity;

public class Repository <T extends DefaultEntity<T>> {
	private EntityManager entityManager;
	
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
	public void rollbackTransaction() {
		entityManager.getTransaction().rollback();
	}
	public void salvar(T entity) throws RepositoryException{
		try {
			getEntityManager().merge(entity);
		}catch (Exception e) {
			System.out.println("Erro ao salvar!");
			e.printStackTrace();
			throw new RepositoryException("Erro ao salvar!");
		}
	}
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
