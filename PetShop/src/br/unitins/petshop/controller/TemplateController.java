package br.unitins.petshop.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.petshop.application.Session;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.Funcionario;

@Named
@ViewScoped
public class TemplateController implements Serializable{

	private static final long serialVersionUID = -3625157056539958909L;
	private Funcionario funcionarioLogado = null;
	
	public TemplateController() {}
	
	public Funcionario getFuncionarioLogado() {
		if(funcionarioLogado == null) 
			funcionarioLogado = (Funcionario) Session.getInstance().getAttribute("funcionarioLogado");
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}
	
	public void encerrarSessao() {
		Session.getInstance().invalidateSession();
		Util.redirect("/PetShop/faces/login.xhtml");
	}
}
