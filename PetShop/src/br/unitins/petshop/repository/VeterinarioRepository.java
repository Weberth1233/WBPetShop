package br.unitins.petshop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.petshop.application.JPAUtil;
import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.model.Veterinario;

public class VeterinarioRepository extends Repository<Veterinario>{
	
	public VeterinarioRepository() {}
	
	public VeterinarioRepository(EntityManager em) {
		super(em);
	}
	public List<Veterinario> findAll() throws RepositoryException {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT v FROM Veterinario v ORDER BY v.nome");
			return query.getResultList();
		}catch (Exception e) {
			e.getStackTrace();
			System.out.println("Erro ao buscar dados da lista");
			throw new RepositoryException("Erro ao buscar no banco dados do veteriáario");
		}
	}
}
