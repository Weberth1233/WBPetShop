package br.unitins.petshop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.petshop.application.JPAUtil;
import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.model.Funcionario;
import br.unitins.petshop.model.Servico;

public class ServicoRepository extends Repository<Servico> {

	public ServicoRepository() {}
	
	public ServicoRepository(EntityManager em) {
		super(em);
	}
	
	public List<Servico> findAll() throws RepositoryException{
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT s FROM Servico s ORDER BY s.nome");
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro aqui no pesquisar tudo do serviço");
			throw new RepositoryException("Erro no buscar do Serviço");
		}
	}
	public List<Servico> findByNome(String nome, int maxResults) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  s ");
			jpql.append("FROM ");
			jpql.append("  Servico s ");
			jpql.append("WHERE ");
			jpql.append("  UPPER(s.nome) like UPPER(:nome) ");
			jpql.append("ORDER BY s.nome ");
			
			Query query = em.createQuery(jpql.toString());
			query.setParameter("nome", "%"+ nome + "%");
			
			query.setMaxResults(maxResults);
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}	
	}
	public List<Servico> findByNome(String nome) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  s ");
			jpql.append("FROM ");
			jpql.append("  Servico s ");
			jpql.append("WHERE ");
			jpql.append("  UPPER(s.nome) like UPPER(:nome) ");
			jpql.append("ORDER BY s.nome ");
			
			Query query = em.createQuery(jpql.toString());
			query.setParameter("nome", "%"+ nome + "%");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
	}
}
