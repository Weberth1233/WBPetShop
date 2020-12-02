package br.unitins.petshop.controller.listing;

import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.model.Funcionario;
import br.unitins.petshop.model.Veterinario;
import br.unitins.petshop.repository.FuncionarioRepository;
import br.unitins.petshop.repository.VeterinarioRepository;
@Named
@ViewScoped
public class VeterinarioListing extends Listing<Veterinario>{
	
	private static final long serialVersionUID = -7705780131135931109L;
	private String filtro;
	
	public VeterinarioListing() {
		super("vetlista", new VeterinarioRepository());
	}
	public void pesquisar() {
		VeterinarioRepository repo = (VeterinarioRepository)getRepository();
		try {
			setList(repo.findVeterinario(getFiltro()));
		} catch (RepositoryException e) {
			e.printStackTrace();
			setList(new ArrayList<Veterinario>());
		}
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	public void selectVeterinario(int id) {
		FuncionarioRepository repository = new FuncionarioRepository();
		
		Funcionario obj = repository.findById(id);
		
		Veterinario vet = new Veterinario();
		vet.setFuncionario(obj);
		
		PrimeFaces.current().dialog().closeDynamic(obj);
	}

}
