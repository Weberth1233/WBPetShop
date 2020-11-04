package br.unitins.petshop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.petshop.application.JPAUtil;
import br.unitins.petshop.application.RepositoryException;
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
}
