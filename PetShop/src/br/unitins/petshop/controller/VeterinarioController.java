package br.unitins.petshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.Veterinario;
import br.unitins.petshop.repository.VeterinarioRepository;
@Named
@ViewScoped
public class VeterinarioController extends Controller<Veterinario>{

	private static final long serialVersionUID = 2728371485171185844L;

	private List<Veterinario>listaVeterinarios;
	
	@Override
	public Veterinario getEntity() {
		if(entity == null) 
			entity = new Veterinario();
		return entity;
	}
	public void pesquisar() {
		VeterinarioRepository repo = new VeterinarioRepository();
		try {
			setListaVeterinarios(repo.findAll());
		} catch (RepositoryException e) {
			System.out.println("Erro no controller no metodo pesquisar");
			Util.addErrorMessage("Erro ao buscar informações no banco de dados!");
			e.printStackTrace();
			setListaVeterinarios(null);
		}
		
	}
	public List<Veterinario> getListaVeterinarios() {
		if(listaVeterinarios == null) 
			listaVeterinarios= new ArrayList<Veterinario>();
		return listaVeterinarios;
	}

	public void setListaVeterinarios(List<Veterinario> listaVeterinarios) {
		this.listaVeterinarios = listaVeterinarios;
	}
	
}
