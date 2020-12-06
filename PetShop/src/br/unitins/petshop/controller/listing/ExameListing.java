package br.unitins.petshop.controller.listing;

import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.model.Exame;
import br.unitins.petshop.repository.ExameRepository;

@Named
@ViewScoped
public class ExameListing extends Listing<Exame> {

	private static final long serialVersionUID = 2777233003872012824L;
	private String filtro;
	
	public ExameListing() {
		super("examelisting", new ExameRepository());
		
	}
	public void pesquisar() {
		ExameRepository repo = (ExameRepository)getRepository();
		try {
			setList(repo.findByNome(filtro));
		} catch (RepositoryException e) {
			e.printStackTrace();
			setList(new ArrayList<Exame>());
		}
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
