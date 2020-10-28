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
	public List<Cliente> findTheCliente(String nome) throws RepositoryException {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append(" c ");
			jpql.append("FROM ");
			jpql.append(" Cliente c ");
			jpql.append("WHERE ");
			jpql.append(" c.nome = :nome) ");
			jpql.append("ORDER BY c.nome ");
			
			Query query = em.createQuery(jpql.toString());
			query.setParameter("nome", "%"+ nome + "%");
			return query.getResultList();	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao encontar o cliente no serviço ou consulta");
			throw new RepositoryException("Erro na busca");
		}
	}
}
