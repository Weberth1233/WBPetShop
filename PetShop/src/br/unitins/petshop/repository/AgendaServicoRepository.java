package br.unitins.petshop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
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
	public void Updade(AgendamentoServico evento) throws RepositoryException{
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query= em.createQuery("UPDATE AgendamentoServico SET titulo=?,data_fim=?,data_incio=?,descricao=?,status=? WHERE id=?");
			query.setParameter(1, evento.getTitulo());
			query.setParameter(2, evento.getData_fim());
			query.setParameter(3, evento.getData_incio());
			query.setParameter(4, evento.getDescricao());
			query.setParameter(5, evento.getStatus());
			query.setParameter(6, evento.getId());
		}catch (Exception e) {
			e.getStackTrace();
			System.out.println("Erro ao atualizar evento");
			throw new RepositoryException("Erro ao atualizar no banco evento");
		}	
	}
	
}
