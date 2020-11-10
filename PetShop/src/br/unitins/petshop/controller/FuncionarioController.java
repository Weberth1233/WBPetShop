package br.unitins.petshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.Funcionario;
import br.unitins.petshop.model.Servico;
import br.unitins.petshop.model.TipoFuncionario;
import br.unitins.petshop.repository.ExameRepository;
import br.unitins.petshop.repository.FuncionarioRepository;
import br.unitins.petshop.repository.ServicoRepository;
@Named
@ViewScoped
public class FuncionarioController extends Controller<Funcionario>{

	private static final long serialVersionUID = 9011861114364637235L;
	private List<Funcionario>listaFuncionario;
	private String buscar;
	@Override
	public Funcionario getEntity() {
		if(entity == null) {
			entity = new Funcionario();
			entity.setServico(new Servico());
		}
		return entity;
	}
	/*public void pesquisar() {
		FuncionarioRepository repo = new FuncionarioRepository();
		try {
			setListaFuncionario(repo.findAll());
		}catch (RepositoryException e) {
			Util.addErrorMessage("Erro ao retornar a lista de funcionarios!");
			setListaFuncionario(null);
			e.printStackTrace();
		}
	}*/
	public void pesquisarPorNome() {
		FuncionarioRepository repo = new FuncionarioRepository();
		try {
			setListaFuncionario(repo.findByNome(getBuscar()));
		} catch (RepositoryException e) {
			Util.addErrorMessage("Erro ao pesquisar cliente");
			e.printStackTrace();
			setListaFuncionario(null);
		}
		buscar = null;
	}
	public TipoFuncionario[] tipoFuncionariosValues() {
		return TipoFuncionario.values();
	}
	
	public List<Servico>completeMarca(String query) {
		ServicoRepository repo = new ServicoRepository();
		try {
			return repo.findByNome(query, 6); 
		} catch (RepositoryException e) {
			e.printStackTrace();
			return new ArrayList<Servico>();
		}
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
	public String getBuscar() {
		return buscar;
	}
	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}
	
}