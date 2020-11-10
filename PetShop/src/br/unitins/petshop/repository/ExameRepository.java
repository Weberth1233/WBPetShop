package br.unitins.petshop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.petshop.application.JPAUtil;
import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.model.Cliente;
import br.unitins.petshop.model.Exame;

public class ExameRepository extends Repository<Exame>{
	
	public ExameRepository() {};
	public ExameRepository(EntityManager em) {
		super(em);
	}
	
	public List<Exame> findAll() throws RepositoryException{
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query= em.createQuery("SELECT e FROM Exame e ORDER BY e.nome");
			return query.getResultList();
		}catch (Exception e) {
			e.getStackTrace();
			System.out.println("Erro ao buscar exames");
			throw new RepositoryException("Erro ao buscar exames");
		}	
	}
	public List<Exame> findByNome(String nome) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  e ");
			jpql.append("FROM ");
			jpql.append("  Exame e ");
			jpql.append("WHERE ");
			jpql.append("  UPPER(e.nome) like UPPER(:nome) ");
			jpql.append("ORDER BY e.nome ");
			
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
