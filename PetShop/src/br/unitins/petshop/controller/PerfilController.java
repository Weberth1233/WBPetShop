package br.unitins.petshop.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.petshop.application.Session;
import br.unitins.petshop.model.Funcionario;

@Named
@ViewScoped
public class PerfilController extends Controller<Funcionario> {
	
	private static final long serialVersionUID = 9035060954957255317L;
	private Funcionario funcionario;

	public Funcionario getFuncionario() {
		funcionario = (Funcionario) Session.getInstance().getAttribute("funcionarioLogado");
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	@Override
	public Funcionario getEntity() {
		if(entity == null) {
			entity = getFuncionario();
		}
		return entity;
	}	
}
