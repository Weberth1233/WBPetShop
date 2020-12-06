package br.unitins.petshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.application.Session;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.Cliente;
import br.unitins.petshop.model.Servico;
import br.unitins.petshop.repository.ServicoRepository;

@Named
@ViewScoped
public class ServicoController extends Controller<Servico> {
	private static final long serialVersionUID = -4603573072138068045L;
	private List<Servico>listaServico;
	private String buscar;
	
	@Override
	public Servico getEntity() {
		if(entity == null) 
			entity= (Servico) Session.getInstance().getAttribute("dadosServ");
		return entity;
	}
	/*public void pesquisar() {
		ServicoRepository repo = new ServicoRepository();
		try {
			setListaServico(repo.findAll());
		}catch (RepositoryException e) {
			Util.addErrorMessage("Erro ao pesquisar serviços");
			setListaServico(null);
			e.printStackTrace();
		}
	}*/
	public void pesquisarPorNome() {
		ServicoRepository repo = new ServicoRepository();
		try {
			setListaServico(repo.findByNome(getBuscar()));
		} catch (RepositoryException e) {
			Util.addErrorMessage("Erro ao pesquisar serviços");
			e.printStackTrace();
			setListaServico(null);
		}
		buscar = null;
	}
	public List<Servico>completeServico(String query) {
		ServicoRepository repo = new ServicoRepository();
		try {
			return repo.findByNome(query, 6); 
		} catch (RepositoryException e) {
			e.printStackTrace();
			return new ArrayList<Servico>();
		}
	}
	public List<Servico> getListaServico() {
		if(listaServico == null)
			listaServico= new ArrayList<Servico>();
		return listaServico;
	}
	public void setListaServico(List<Servico> listaServico) {
		this.listaServico = listaServico;
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
		Session.getInstance().setAttribute("dadosServ", new Servico());
	}
	@Override
	public void remover() {
		super.remover();
		Session.getInstance().setAttribute("dadosServ", new Servico());
	}
	public void criarRedirect() {
		Session.getInstance().setAttribute("dadosServ", new Servico());
		Util.redirect("cadservico.xhtml?faces-redirect=true.xhtml?faces-redirect=true");
	}
	
	public void editarServico(Servico servico) {
		setEntity(servico);
		Session.getInstance().setAttribute("dadosServ", getEntity());
		Util.redirect("cadservico.xhtml?faces-redirect=true.xhtml?faces-redirect=true");
	}
}