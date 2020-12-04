package br.unitins.petshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.application.Session;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.controller.listing.ClienteListing;
import br.unitins.petshop.model.Animal;
import br.unitins.petshop.model.Cliente;
import br.unitins.petshop.repository.ClienteRepository;
import br.unitins.petshop.repository.Repository;

@Named
@ViewScoped
public class ClienteContoller extends Controller<Cliente>{

	private static final long serialVersionUID = 1982086533161935915L;
	private List<Cliente>listaCliente;
	private Animal animal;
	private String buscar;
	private String buscarCpf;

	@Override
	public Cliente getEntity() {

		if(entity == null) {
			entity = (Cliente) Session.getInstance().getAttribute("dadosCli");
		}
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
	private boolean verficarBuscar() {
		if(getBuscar().isBlank() == false) {
			return true;
		}
		return false;
	}
	private boolean verficarBuscarCpf() {
		if(getBuscarCpf().isBlank() == false) {
			return true;
		}
		return false;
	}
	public void pesquisarPorNome() {
		ClienteRepository repo = new ClienteRepository();
		try {
			if(verficarBuscar() && verficarBuscarCpf() ){
				setListaCliente(null);
				Util.addErrorMessage("Somente um campo deve ser selecionado");
			}else {
				setListaCliente(repo.findByNome(getBuscar()));
			}
		} catch (RepositoryException e) {
			Util.addErrorMessage("Erro ao pesquisar cliente");
			e.printStackTrace();
			setListaCliente(null);
		}
		setBuscar(null);
		setBuscarCpf(null);
	}
	public void pesquisarPorCPF() {
		ClienteRepository repo = new ClienteRepository();
		try {
			if(verficarBuscar() && verficarBuscarCpf() ){
				setListaCliente(null);
				Util.addErrorMessage("Somente um campo deve ser selecionado");
			}else {
				setListaCliente(repo.findByCPF(getBuscarCpf()));
			}
		} catch (RepositoryException e) {
			Util.addErrorMessage("Erro ao pesquisar cliente");
			e.printStackTrace();
			setListaCliente(null);
		}
		setBuscar(null);
		setBuscarCpf(null);
	}
	public List<Cliente> getListaCliente() {
		if(listaCliente == null) {
			listaCliente = new ArrayList<Cliente>();
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
	public void retornarAnimal(Cliente entity) {
		setEntity(entity);
		Session.getInstance().setAttribute("dadosCli", getEntity());
	}
	public Animal getAnimal() {
		if(animal == null) 
			animal = new Animal();
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public String getBuscarCpf() {
		return buscarCpf;
	}

	public void setBuscarCpf(String buscarCpf) {
		this.buscarCpf = buscarCpf;
	}
	public void abrirClienteListing() {
		ClienteListing listing = new ClienteListing();
		listing.open();
	}
	public void obterClienteListing(SelectEvent<Cliente> event) {
		setEntity(event.getObject());
	}
	public void editarAnimal(Animal entity) {
		setAnimal(entity);
	}
	public String adicionarNovo() {
		Session.getInstance().setAttribute("dadosCli", new Cliente());
		return "cadcliente.xhtml?faces-redirect=true";
	}
	
	public void criarRedirect() {
		Session.getInstance().setAttribute("dadosCli", new Cliente());
		Util.redirect("cadcliente.xhtml?faces-redirect=true.xhtml?faces-redirect=true");
	}
	
	public String agendarCliente(Cliente cliente) {
		setEntity(cliente);
		Session.getInstance().setAttribute("dadosCli", getEntity());
		return "agendar.xhtml?faces-redirect=true";
	}
	public String editarCliente(Cliente cliente) {
		setEntity(cliente);
		Session.getInstance().setAttribute("dadosCli", getEntity());
		return "cadcliente.xhtml?faces-redirect=true";
	}
}
