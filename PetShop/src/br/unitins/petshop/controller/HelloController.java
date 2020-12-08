package br.unitins.petshop.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.AgendamentoServico;
import br.unitins.petshop.repository.AgendaConsultaRepository;
import br.unitins.petshop.repository.AgendaServicoRepository;

@Named
@ViewScoped
public class HelloController implements Serializable {

	private static final long serialVersionUID = 1935775511554974550L;
	private String filtro;
	private List<Object[]> list;
	
	public void pesquisar() {
		AgendaConsultaRepository repo = new AgendaConsultaRepository();
		try {
			setList(repo.findByConsultasSQL(filtro));
		} catch (RepositoryException e) {
			e.printStackTrace();
			setList(new ArrayList<Object[]>());
		}
	}
	public void gerarRelatorio() {
		Util.redirect("/PetShop/faces/agendamentoservlet");
	}
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Object[]> getList() {
		return list;
	}
	public void setList(List<Object[]> list) {
		this.list = list;
	}
}
