package br.unitins.petshop.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.AgendamentoConsulta;
import br.unitins.petshop.model.Diagnostico;
import br.unitins.petshop.model.Exame;
import br.unitins.petshop.repository.Repository;

@Named
@ViewScoped
public class DiagnosticoController extends Controller<Diagnostico>{
	private static final long serialVersionUID = -7374918795180548036L;
	private AgendamentoConsulta evento;
	private List<Exame>lista;
	
	@PostConstruct
	public void DiagnosticoController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("eventoconsulta");
		evento = (AgendamentoConsulta) flash.get("eventoconsulta");
		getEntity().setAgendamento(evento);
		getEntity().setVeterinario(evento.getVeterinario());
		List<Exame>lista = getEntity().getAgendamento().getListaExame();
		if(lista == null){
			lista= new ArrayList<Exame>();
		}
		setLista(lista);
	}
	
	@Override
	public void salvar() {
		Repository<Diagnostico> repo = new Repository<Diagnostico>();
		try {
			repo.beginTransaction();
			setEntity(repo.salvar(getEntity()));
			repo.commitTransaction();
			Util.addInfoMessage("Operação realizada com sucesso.");
		}
		catch (RepositoryException e) {
			repo.rollbackTransaction();
			System.out.println("Erro ao salvar.");
			e.printStackTrace();
//			Util.addErrorMessage("Erro ao Salvar.");
			Util.addErrorMessage(e.getMessage());
		}
		entity = new Diagnostico();
		DiagnosticoController();
	}
	
	public void adcExame(Exame exame) {
		getEntity().setExame(exame);
		Util.addInfoMessage("Exame adicionado com sucesso!");
	}
	public AgendamentoConsulta getEvento() {
		return evento;
	}

	public void setEvento(AgendamentoConsulta evento) {
		this.evento = evento;
	}
	
	@Override
	public Diagnostico getEntity() {
		if(entity == null) {
			entity = new Diagnostico();
		}
		return entity;
	}
	public List<Exame> getLista() {
		if(lista == null) {
			lista = new ArrayList<Exame>();
		}
		return lista;
	}
	public void setLista(List<Exame> lista) {
		this.lista = lista;
	}
}
