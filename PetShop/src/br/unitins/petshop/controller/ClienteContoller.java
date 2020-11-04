package br.unitins.petshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.Animal;
import br.unitins.petshop.model.Cliente;
import br.unitins.petshop.repository.ClienteRepository;

@Named
@ViewScoped
public class ClienteContoller extends Controller<Cliente>{

	private static final long serialVersionUID = 1982086533161935915L;
	private List<Cliente>listaCliente;
	private Animal animal;
	
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
	
	public List<Cliente> getListaCliente() {
		if(listaCliente==null) 
			listaCliente= new ArrayList<Cliente>();
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}
	
	public void adicionarAnimal() {
		if(getEntity().getListaAnimal() == null) 
			getEntity().setListaAnimal(new ArrayList<Animal>());
		
		getAnimal().setCliente(getEntity());
		getEntity().getListaAnimal().add(getAnimal());
		
		animal = null;
		
	}
	public Animal getAnimal() {
		if(animal == null) 
			animal = new Animal();
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
}
