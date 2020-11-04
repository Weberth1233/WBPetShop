package br.unitins.petshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.Exame;
import br.unitins.petshop.repository.ExameRepository;

@Named
@ViewScoped
public class ExameController extends Controller<Exame> {

	private static final long serialVersionUID = 2404598018914367942L;
	
	private List<Exame>listaExames;
	
	public void pesquisar() {
		ExameRepository repo = new ExameRepository();
		try {
			setListaExames(repo.findAll());
		} catch (RepositoryException e) {
			System.out.println("Erro no pesquisar no controller");
			Util.addErrorMessage("Erro ao pesquisar exames no banco!");
			e.printStackTrace();
			setListaExames(null);
		}
	}
	
	@Override
	public Exame getEntity() {
		if(entity == null) 
			entity = new Exame();
		return entity;
	}

	public List<Exame> getListaExames() {
		if(listaExames == null) 
			listaExames= new ArrayList<Exame>();
		return listaExames;
	}

	public void setListaExames(List<Exame> listaExames) {
		this.listaExames = listaExames;
	}
	
	
}
