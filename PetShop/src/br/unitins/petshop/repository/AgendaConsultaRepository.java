package br.unitins.petshop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.petshop.application.JPAUtil;
import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.model.AgendamentoConsulta;

public class AgendaConsultaRepository extends Repository<AgendamentoConsulta> {
	
	public AgendaConsultaRepository() {};
	
	public AgendaConsultaRepository(EntityManager em) {
		super(em);
	}
	
	public List<AgendamentoConsulta> findAll() throws RepositoryException{
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query= em.createQuery("SELECT e FROM AgendamentoConsulta e ORDER BY e.id");
			return query.getResultList();
		}catch (Exception e) {
			e.getStackTrace();
			System.out.println("Erro ao buscar exames");
			throw new RepositoryException("Erro ao buscar exames");
		}	
	}
	
	public List<AgendamentoConsulta> findListVet(Integer id) throws RepositoryException{
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query= em.createQuery("SELECT e FROM AgendamentoConsulta e WHERE e.veterinario.id =: id");
			query.setParameter("id", id);
			return query.getResultList();
		}catch (Exception e) {
			e.getStackTrace();
			System.out.println("Erro ao buscar exames");
			throw new RepositoryException("Erro ao buscar exames");
		}	
	}
	
	public List<AgendamentoConsulta> findListAguardo() throws RepositoryException{
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query= em.createQuery("SELECT e FROM AgendamentoConsulta e WHERE e.status = false");
			return query.getResultList();
		}catch (Exception e) {
			e.getStackTrace();
			System.out.println("Erro ao buscar exames");
			throw new RepositoryException("Erro ao buscar exames");
		}	
	}
	
	public List<Object[]> findByConsultasSQL(String nome) throws RepositoryException {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("  c.nome, a.data_incio, a.data_fim ");
			sql.append("FROM ");
			sql.append("  agendamento a, ");
			sql.append("  cliente c  ");
			sql.append("WHERE ");
			sql.append("  c.id = a.clienteagenda_id ");
			sql.append("  AND a.status = false ");
			sql.append("  AND UPPER(c.nome) like UPPER(?) ");
			sql.append("ORDER BY c.nome ");

			Query query = em.createNativeQuery(sql.toString());
			query.setParameter(1, "%" + nome + "%");
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}

	}
	
}
