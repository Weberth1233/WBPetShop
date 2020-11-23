package br.unitins.petshop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.petshop.application.JPAUtil;
import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.model.AgendamentoServico;

public class AgendaServicoRepository extends Repository<AgendamentoServico> {
	
	public AgendaServicoRepository() {};
	public AgendaServicoRepository(EntityManager em) {
		super(em);
	}
	public List<AgendamentoServico> findAll() throws RepositoryException{
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query= em.createQuery("SELECT e FROM AgendamentoServico e ORDER BY e.id");
			return query.getResultList();
		}catch (Exception e) {
			e.getStackTrace();
			System.out.println("Erro ao buscar exames");
			throw new RepositoryException("Erro ao buscar exames");
		}	
	}
}
