package br.unitins.petshop.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.model.AgendamentoServico;
import br.unitins.petshop.repository.AgendaServicoRepository;

@Named
@ViewScoped
public class FiltroAgendaController implements Serializable {
	private static final long serialVersionUID = -5794068887063474243L;
	private List<AgendamentoServico>listaServicos;
	private boolean status;
	
	public void filtroAgenda() {
		AgendaServicoRepository repo = new AgendaServicoRepository();
		try {
			setListaServicos(repo.agendaFiltro(status));
		} catch (RepositoryException e) {
			e.printStackTrace();
			setListaServicos(new ArrayList<AgendamentoServico>());
		}
	}
	
	public List<AgendamentoServico> getListaServicos() {
		if(listaServicos == null) {
			listaServicos = new ArrayList<AgendamentoServico>();
		}
		return listaServicos;
	}
	public void setListaServicos(List<AgendamentoServico> listaServicos) {
		this.listaServicos = listaServicos;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
