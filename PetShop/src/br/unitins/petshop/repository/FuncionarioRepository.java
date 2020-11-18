package br.unitins.petshop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.petshop.application.JPAUtil;
import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.Exame;
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
	public List<Funcionario> findByNome(String nome) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  f ");
			jpql.append("FROM ");
			jpql.append("  Funcionario f ");
			jpql.append("WHERE ");
			jpql.append("  UPPER(f.nome) like UPPER(:nome) ");
			jpql.append("ORDER BY f.nome ");
			
			Query query = em.createQuery(jpql.toString());
			query.setParameter("nome", "%"+ nome + "%");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
	}
	public Funcionario verificarlogin(String login, String senha) throws RepositoryException {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT f FROM Funcionario f WHERE f.login = :login and f.senha = :senha");
			query.setParameter("login", login);
			query.setParameter("senha", senha);
			return (Funcionario) query.getSingleResult();
		}catch (NoResultException e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar dados para o login!");
			throw new RepositoryException("Erro ao buscar dados para o login.");
		}
		catch (Exception e){
			System.out.println("Erro geral no login!");
			e.printStackTrace();
			throw new RepositoryException("Erro ao geral ao realizar login.");
		}
	}
}
