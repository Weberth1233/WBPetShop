package br.unitins.petshop.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.AgendamentoConsulta;
import br.unitins.petshop.model.Diagnostico;
import br.unitins.petshop.model.Exame;

@Named
@ViewScoped
public class DiagnosticoController extends Controller<Diagnostico>{
	private static final long serialVersionUID = -7374918795180548036L;
	private AgendamentoConsulta evento;
	
	public DiagnosticoController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("eventoconsulta");
		evento = (AgendamentoConsulta) flash.get("eventoconsulta");
		
		getEntity().setAgendamento(evento);
		
		getEntity().setVeterinario(evento.getVeterinario());
	}
	public void adcExame(Exame exame) {
		getEntity().setExame(exame);
		getEntity().getAgendamento().getListaExame().remove(getEntity().getExame());
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
	
}
