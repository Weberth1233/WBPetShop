package br.unitins.petshop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.petshop.application.JPAUtil;
import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.model.Funcionario;

public class FuncionarioRepository extends Repository<Funcionario> {
	
	public FuncionarioRepository() {}
	public FuncionarioRepository(EntityManager em) {
		super(em);
	}
	
	public List<Funcionario> findAll() throws RepositoryException {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT f FROM Funcionario f ORDER BY f.nome");
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro na lista do funcionario no repository");
		}
	}
}
