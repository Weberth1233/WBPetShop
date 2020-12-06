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
import br.unitins.petshop.controller.listing.ExameListing;
import br.unitins.petshop.model.Cliente;
import br.unitins.petshop.model.Exame;
import br.unitins.petshop.model.Servico;
import br.unitins.petshop.repository.ClienteRepository;
import br.unitins.petshop.repository.ExameRepository;

@Named
@ViewScoped
public class ExameController extends Controller<Exame> {

	private static final long serialVersionUID = 2404598018914367942L;
	
	private List<Exame>listaExames;
	private String buscar;
	
	/*public void pesquisar() {
		ExameRepository repo = new ExameRepository();
		try {
			setListaExames(repo.findAll());
		} catch (RepositoryException e) {
			System.out.println("Erro no pesquisar no controller");
			Util.addErrorMessage("Erro ao pesquisar exames no banco!");
			e.printStackTrace();
			setListaExames(null);
		}
	}*/
	public void pesquisarPorNome() {
		ExameRepository repo = new ExameRepository();
		try {
			setListaExames(repo.findByNome(getBuscar()));
		} catch (RepositoryException e) {
			Util.addErrorMessage("Erro ao pesquisar cliente");
			e.printStackTrace();
			setListaExames(null);
		}
		buscar = null;
	}
	@Override
	public Exame getEntity() {
		if(entity == null) 
			entity = (Exame) Session.getInstance().getAttribute("dadosExam");
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
	public String getBuscar() {
		return buscar;
	}
	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}
	@Override
	public void salvar() {
		super.salvar();
		Session.getInstance().setAttribute("dadosExam",new Exame());
	}
	@Override
	public void remover() {
		super.remover();
		Session.getInstance().setAttribute("dadosExam",new Exame());
	}
	
	public void criarRedirect() {
		Session.getInstance().setAttribute("dadosExam", new Exame());
		Util.redirect("cadexame.xhtml?faces-redirect=true.xhtml?faces-redirect=true");
	}
	public void editarExame(Exame exame) {
		setEntity(exame);
		Session.getInstance().setAttribute("dadosExam", getEntity());
		Util.redirect("cadexame.xhtml?faces-redirect=true.xhtml?faces-redirect=true");
	}
	
	public void abrirExameListing() {
		ExameListing listing = new ExameListing();
		listing.open();
	}
	public void obterExameListing(SelectEvent<Exame> event) {
		setEntity(event.getObject());
	}
}
