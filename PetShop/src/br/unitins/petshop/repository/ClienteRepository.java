package br.unitins.petshop.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.petshop.application.JPAUtil;
import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.model.Cliente;

public class ClienteRepository extends Repository<Cliente>{
	
	public ClienteRepository() {}
	public ClienteRepository(EntityManager em) {
		super(em);
	}
	
	public List<Cliente> findAll() throws RepositoryException {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT c FROM Cliente c ORDER BY c.nome ");
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("ERRO NA LISTA MEU JOVEM !");
			e.printStackTrace();
			throw new RepositoryException("Erro ao montar a lista");
		}
	}
	public List<Cliente> findByNome(String nome) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  c ");
			jpql.append("FROM ");
			jpql.append("  Cliente c ");
			jpql.append("WHERE ");
			jpql.append("  UPPER(c.nome) like UPPER(:nome) ");
			jpql.append("ORDER BY c.nome ");
			
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
