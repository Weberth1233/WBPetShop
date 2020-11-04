package br.unitins.petshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.Servico;
import br.unitins.petshop.repository.ServicoRepository;

@Named
@ViewScoped
public class ServicoController extends Controller<Servico> {
	private static final long serialVersionUID = -4603573072138068045L;
	private List<Servico>listaServico;
	
	@Override
	public Servico getEntity() {
		if(entity == null) 
			entity= new Servico();
		return entity;
	}
	public void pesquisar() {
		ServicoRepository repo = new ServicoRepository();
		try {
			setListaServico(repo.findAll());
		}catch (RepositoryException e) {
			Util.addErrorMessage("Erro ao pesquisar serviços");
			setListaServico(null);
			e.printStackTrace();
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
}
