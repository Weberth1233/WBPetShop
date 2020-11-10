package br.unitins.petshop.controller.listing;

import java.util.ArrayList;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.model.Servico;
import br.unitins.petshop.repository.ServicoRepository;
@Named
@ViewScoped
public class ServicoListing extends Listing<Servico>{
	
	private static final long serialVersionUID = -8289720467505057786L;
	private String filtro;
	
	public ServicoListing() {
		super("servicolisting", new ServicoRepository());
	}

	public void pesquisar() {
		ServicoRepository repo = (ServicoRepository)getRepository();
		try {
			setList(repo.findByNome(filtro));
		} catch (RepositoryException e) {
			e.printStackTrace();
			setList(new ArrayList<Servico>());
		}
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
}
