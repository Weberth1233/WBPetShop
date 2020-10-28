package br.unitins.petshop.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.unitins.petshop.application.JPAUtil;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.Funcionario;

public class TestJPA {
	public static void main(String[] args) {
		Funcionario func = new Funcionario();
		String login = "kaiohehj";
		String senha = "kaio123567";
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("PetShop");
		// gerenciador do jpa (responsavel por fazer transacoes ou selecoes no banco)
		try {
			EntityManager em = emf.createEntityManager();
			Query query = em.createQuery("SELECT f FROM Funcionario f WHERE f.login = :login");
			query.setParameter("login", login);
			func = (Funcionario)query.getSingleResult();
			if(func != null) {
				if(senha.equals(func.getSenha())) {
					System.out.println("Bem vindo!");
					System.out.println("Nome:"+func.getNome());
					System.out.println("Senha:"+func.getSenha());
				}
			}
		}catch (NoResultException e) {
			System.out.println("Usuario não encontrado");
		}
	}
	/*Usuario usuario = em.merge(usu);
		if(usuario.getNome().isBlank()) {
			em.getTransaction().rollback();
			System.out.println("Nome não pode ficar vazio");
		}else {
			em.getTransaction().commit();
			System.out.println("Não foi possivel");
		}*/
}

