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
	public List<Veterinario> findVeterinario(String nome) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT f FROM Veterinario v, Funcionario f WHERE f.id = v.funcionario.id and UPPER(f.nome) like UPPER(:nome) ORDER BY f.nome");
			query.setParameter("nome", "%"+ nome + "%");
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
	}

	public List<Veterinario> findByNome(String nome, int maxResults) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT f FROM Veterinario v, Funcionario f WHERE f.id = v.funcionario.id and UPPER(f.nome) like UPPER(:nome) ORDER BY f.nome");
			query.setParameter("nome", "%"+ nome + "%");
			query.setMaxResults(maxResults);
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
	}
}
