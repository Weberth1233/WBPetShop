package br.unitins.petshop.controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.controller.listing.ClienteListing;
import br.unitins.petshop.model.Animal;
import br.unitins.petshop.model.Cliente;
import br.unitins.petshop.repository.ClienteRepository;

@Named
@ViewScoped
public class ClienteContoller extends Controller<Cliente>{

	private static final long serialVersionUID = 1982086533161935915L;
	private List<Cliente>listaCliente;
	private Animal animal;
	private String buscar;
	
	@Override
	public Cliente getEntity() {
		if(entity == null) 
			entity= new Cliente();
		return entity;
	}

	/*public void pesquisar() {
		ClienteRepository repo = new ClienteRepository();
		try {
			setListaCliente(repo.findAll());
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Erro ao pesquisar");
			System.out.println("ERRO AQUI NO PESQUISAR DO CONTROLLER..VEM AQUI OLHAR!");
			setListaCliente(null);
		}
	}*/
	public void pesquisarPorNome() {
		ClienteRepository repo = new ClienteRepository();
		try {
			setListaCliente(repo.findByNome(getBuscar()));
		} catch (RepositoryException e) {
			Util.addErrorMessage("Erro ao pesquisar cliente");
			e.printStackTrace();
			setListaCliente(null);
		}
		buscar = null;
	}
	public List<Cliente> getListaCliente() {
		ClienteRepository repo = new ClienteRepository();
		if(listaCliente==null) {
			try {
				listaCliente= repo.findAll();
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}
	
	public String getBuscar() {
		return buscar;
	}

	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}

	public void adicionarAnimal() {
		if(getEntity().getListaAnimal() == null) 
			getEntity().setListaAnimal(new ArrayList<Animal>());
		
		getAnimal().setCliente(getEntity());
		getEntity().getListaAnimal().add(getAnimal());
		
		animal = null;
		
	}
	public void editar(Animal animal) {
		setAnimal(animal);
	}
	public Animal getAnimal() {
		if(animal == null) 
			animal = new Animal();
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	public void abrirClienteListing() {
		ClienteListing listing = new ClienteListing();
		listing.open();
	}
	public void obterClienteListing(SelectEvent<Cliente> event) {
		setEntity(event.getObject());
	}
}
