package br.unitins.petshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.Funcionario;
import br.unitins.petshop.model.TipoFuncionario;
import br.unitins.petshop.repository.FuncionarioRepository;
@Named
@ViewScoped
public class FuncionarioController extends Controller<Funcionario>{

	private static final long serialVersionUID = 9011861114364637235L;
	private List<Funcionario>listaFuncionario;

	@Override
	public Funcionario getEntity() {
		if(entity == null)
			entity = new Funcionario();
		return entity;
	}
	public void pesquisar() {
		FuncionarioRepository repo = new FuncionarioRepository();
		try {
			setListaFuncionario(repo.findAll());
		}catch (RepositoryException e) {
			Util.addErrorMessage("Erro ao retornar a lista de funcionarios!");
			setListaFuncionario(null);
			e.printStackTrace();
		}
	}
	public TipoFuncionario[] tipoFuncionariosValues() {
		return TipoFuncionario.values();
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
		entity = null;
	}
}