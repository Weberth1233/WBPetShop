package br.unitins.petshop.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.petshop.application.JPAUtil;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.Funcionario;
 @Named
 @ViewScoped
public class FuncionarioController implements Serializable{
	private static final long serialVersionUID = 2004215866035987320L;
	private Funcionario funcionario;
	private List<Funcionario>listaFuncionario;
	
	public Funcionario getFuncionario() {
		if(funcionario == null) 
			funcionario= new Funcionario();
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public List<Funcionario> getListaFuncionario() {
		if(listaFuncionario==null)
			listaFuncionario= new ArrayList<Funcionario>();
		return listaFuncionario;
	}
	public void setListaFuncionario(List<Funcionario> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}
	public void limpar() {
		funcionario = null;
	}
	public void salvar() {
		/*Instanciar o gerenciador do jpa*/
		EntityManager em= JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(getFuncionario());
		em.getTransaction().commit();
		limpar();
		Util.addInfoMessage("Operação realizada com sucesso!");
	}
	public void pesquisar() {
		EntityManager em= JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT func from Funcionario func");
		setListaFuncionario(query.getResultList());
	}
}
