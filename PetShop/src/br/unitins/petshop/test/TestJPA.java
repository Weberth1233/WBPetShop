package br.unitins.petshop.test;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.unitins.petshop.application.JPAUtil;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.AgendamentoConsulta;
import br.unitins.petshop.model.Animal;
import br.unitins.petshop.model.Cliente;
import br.unitins.petshop.model.Exame;
import br.unitins.petshop.model.Funcionario;
import br.unitins.petshop.repository.AgendaConsultaRepository;
import br.unitins.petshop.repository.Repository;

public class TestJPA {
	public static void main(String[] args) {
		Exame exameA = new Exame();
		exameA.setNome("Sangue");

		Exame exameB = new Exame();
		exameB.setNome("Fezes");
		
		Exame exameC = new Exame();
		exameC.setNome("Urina");

		Cliente cli= new Cliente();
		cli.setCpf("07162149111");
		cli.setEmail("weberth20-19@hotmail.com");
		cli.setId(1);
		cli.setNome("Weberth Erik Anolar Sirqueira");
		cli.setNumeroTelefone("(63)985099203");
		
		AgendamentoConsulta agendar= new AgendamentoConsulta();
		Animal animal = new Animal();
		animal.setNome("fifi");
		animal.setObservacoes("Grande");
		animal.setPeso("12,5");
		animal.setCliente(cli);
		animal.setId(1);
		
		agendar.setListaExame(new ArrayList<Exame>());
		
		agendar.getListaExame().add(exameA);
		agendar.getListaExame().add(exameB);
		agendar.getListaExame().add(exameC);
		
		agendar.setClienteAgenda(cli);
		agendar.setAnimalAgenda(animal);
		agendar.setStatus(1);
		
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("PetShop");
		// gerenciador do jpa (responsavel por fazer transacoes ou selecoes no banco)
		AgendaConsultaRepository repo= new AgendaConsultaRepository();
		try {
			repo.beginTransaction();
			repo.salvar(agendar);
			repo.commitTransaction();
			
		}catch (Exception e) {
			repo.rollbackTransaction();
			e.printStackTrace();
		}
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


