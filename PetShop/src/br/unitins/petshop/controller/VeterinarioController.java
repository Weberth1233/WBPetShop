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
import br.unitins.petshop.model.Veterinario;
@Named
@ViewScoped
public class VeterinarioController implements Serializable {
	
	private static final long serialVersionUID = -1879016343691848203L;

	private Veterinario veterinario;
	
	private List<Veterinario>listaVeterinarios;
	
	public void salvar() {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(getVeterinario());
		em.getTransaction().commit();
		limpar();
		Util.addInfoMessage("Salvo com sucesso!");
	}
	public void pesquisar() {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT p FROM Veterinario p");
		setListaVeterinarios(query.getResultList());
	}
	public void desativar() {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		Veterinario veterinario = em.merge(getVeterinario());
		em.remove(veterinario);
		em.getTransaction().commit();
		Util.addInfoMessage("Veterinario desativado!");
		limpar();
	}
	public void limpar() {
		veterinario = null;
	}
	public void editar(Veterinario veterinario) {
		setVeterinario(veterinario);
	}
	public Veterinario getVeterinario() {
		if(veterinario == null) 
			veterinario = new Veterinario();
		return veterinario;
	}
	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}
	public List<Veterinario> getListaVeterinarios() {
		if(listaVeterinarios==null) {
			listaVeterinarios= new ArrayList<Veterinario>();
		}
		return listaVeterinarios;
	}
	public void setListaVeterinarios(List<Veterinario> listaVeterinarios) {
		this.listaVeterinarios = listaVeterinarios;
	}
	
}
