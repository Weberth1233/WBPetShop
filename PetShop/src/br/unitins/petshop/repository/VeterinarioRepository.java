package br.unitins.petshop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.petshop.application.JPAUtil;
import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.model.Servico;
import br.unitins.petshop.model.Veterinario;

public class VeterinarioRepository extends Repository<Veterinario>{
	
	public VeterinarioRepository() {}
	
	public VeterinarioRepository(EntityManager em) {
		super(em);
	}
	public List<Veterinario> findAll() throws RepositoryException {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT v FROM Veterinario v, Funcionario f WHERE v.funcionario_id = f.id");
			return query.getResultList();
		}catch (Exception e) {
			e.getStackTrace();
			System.out.println("Erro ao buscar dados da lista");
			throw new RepositoryException("Erro ao buscar no banco dados do veteriáario");
		}
	}
	public List<Veterinario> findVeterinario() throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  v.crnv, f.nome ");
			jpql.append("FROM ");
			jpql.append("  Veterinario v, Funcionario f ");
			jpql.append("WHERE ");
			jpql.append("  v.funcionario_id = f.id ");
			jpql.append("ORDER BY f.nome ");
			
			Query query = em.createQuery(jpql.toString());
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
	}
}
