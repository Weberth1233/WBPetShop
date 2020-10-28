package br.unitins.petshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.Cliente;
import br.unitins.petshop.repository.ClienteRepository;

@Named
@ViewScoped
public class ClienteContoller extends Controller<Cliente>{

	private static final long serialVersionUID = 1982086533161935915L;
	private List<Cliente>listaCliente;
	private String nome; 
	private String cpf;
	
	
	@Override
	public Cliente getEntity() {
		if(entity == null) 
			entity= new Cliente();
		return entity;
	}

	public void pesquisar() {
		ClienteRepository repo = new ClienteRepository();
		try {
			setListaCliente(repo.findAll());
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Erro ao pesquisar");
			System.out.println("ERRO AQUI NO PESQUISAR DO CONTROLLER..VEM AQUI OLHAR!");
			setListaCliente(null);
		}
	}
	
	public void pesquisarPorNomeECpf() {
		ClienteRepository repo = new ClienteRepository();
		try {
			setListaCliente(repo.findTheCliente(getNome()));
			Util.addInfoMessage("Cliente encontrado com sucesso!");
		} catch (RepositoryException e) {
			System.out.println("Erro no pesquisar no controller");
			Util.addErrorMessage("Erro ao pesquisar cliente!");
			e.printStackTrace();
			setListaCliente(null);
		}
	}
	
	public List<Cliente> getListaCliente() {
		if(listaCliente==null) 
			listaCliente= new ArrayList<Cliente>();
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}	
}
