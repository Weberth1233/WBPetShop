package br.unitins.petshop.controller;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.petshop.application.JPAUtil;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.Funcionario;

@Named
@RequestScoped
public class LoginController {
	private Funcionario funcionario;
	private String login;
	private String senha;

	public Funcionario getFuncionario() {
		if(funcionario==null)
			funcionario = new Funcionario();
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	private void limpar() {
		senha=null;
		login=null;
	}
	public String logar() {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT f FROM Funcionario f WHERE f.login = :login");
			query.setParameter("login", getLogin());
			funcionario = (Funcionario)query.getSingleResult();
			/*Coloquei pra caso não lembrasse de instanciar o funcionario*/
			if(getSenha().equals(funcionario.getSenha())) {
				return "cadcliente.xhtml?faces-redirect=true";
			}
		}catch (NoResultException e){
		}
		Util.addErrorMessage("Usuario não encontrado!");
		limpar();
		return null;
	}
}
