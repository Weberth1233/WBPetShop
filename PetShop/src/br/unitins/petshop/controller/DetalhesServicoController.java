package br.unitins.petshop.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.petshop.model.AgendamentoServico;

@Named
@ViewScoped
public class DetalhesServicoController implements Serializable {
	private static final long serialVersionUID = -6858974525998575440L;
	private AgendamentoServico evento;
	
	public DetalhesServicoController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("eventodetalhes");
		evento = (AgendamentoServico) flash.get("eventodetalhes");
	}
	public AgendamentoServico getEvento() {
		return evento;
	}
	public void setEvento(AgendamentoServico evento) {
		this.evento = evento;
	}
}
