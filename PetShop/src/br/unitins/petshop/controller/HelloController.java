package br.unitins.petshop.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.model.AgendamentoConsulta;
import br.unitins.petshop.repository.AgendaConsultaRepository;

@Named
@ViewScoped
public class HelloController implements Serializable {

	private static final long serialVersionUID = 1935775511554974550L;
	private List<AgendamentoConsulta> lista;
	
	public HelloController() {
		AgendaConsultaRepository repo= new AgendaConsultaRepository();
		try {
			lista = repo.findListVet(2);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}
	
	public List<AgendamentoConsulta> getLista() {
		return lista;
	}
	public void setLista(List<AgendamentoConsulta> lista) {
		this.lista = lista;
	}
	
	
}
