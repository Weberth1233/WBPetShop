package br.unitins.petshop.controller.listing;

import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.model.Cliente;
import br.unitins.petshop.repository.ClienteRepository;

@Named
@ViewScoped
public class ClienteListing extends Listing<Cliente>{
	
	private static final long serialVersionUID = -4461821415416769497L;
	private String filtro;
	
	public ClienteListing() {
		super("clientelisting", new ClienteRepository());
	}
	public void pesquisar() {
		ClienteRepository repo = (ClienteRepository)getRepository();
		try {
			setList(repo.findByNome(getFiltro()));
		} catch (RepositoryException e) {
			e.printStackTrace();
			setList(new ArrayList<Cliente>());
		}
	}
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
}
