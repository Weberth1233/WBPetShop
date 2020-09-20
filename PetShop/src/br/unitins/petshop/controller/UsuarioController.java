package br.unitins.petshop.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.catalina.startup.SetAllPropertiesRule;

import br.unitins.petshop.application.JPAUtil;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.Usuario;

@Named
@ViewScoped
public class UsuarioController implements Serializable {
	private static final long serialVersionUID = 456996203947858448L;

	private Usuario usu;
	private List<Usuario>lista;

	public Usuario getUsu() {
		if(usu == null) 
			usu= new Usuario();
		return usu;
	}

	public void setUsu(Usuario usu) {
		this.usu = usu;
	}
	public List<Usuario> getLista() {
		if(lista == null)
			lista= new ArrayList<Usuario>();
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}
	public void pesquisar() {
		/*Criando um gerenciador*/
		EntityManager em = JPAUtil.getEntityManager();
		
		Query query = em.createQuery("SELECT usu FROM Usuario usu");
		setLista(query.getResultList());
	}
	public void salvar() {
		EntityManager em = JPAUtil.getEntityManager();
		// iniciando uma transacao com o banco de dados
		em.getTransaction().begin();
		
		em.merge(getUsu());
		
		em.getTransaction().commit();
		
		Util.addInfoMessage("Operação realizado com sucesso!");
		limpar();
	}
	public void excluir() {
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		Usuario usu = em.merge(getUsu());
		em.remove(usu);
		em.getTransaction().commit();
		limpar();
		Util.addInfoMessage("Excluido com sucesso!");
	}
	public void editar(Usuario usuario) {
		setUsu(usuario);
	}
	public void limpar() {
		usu = null;
	}
}
