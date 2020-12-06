package br.unitins.petshop.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class AgendamentoConsulta extends Agendamento {
	private Float precoConsulta;
	
	@ManyToMany
	private List<Exame>listaExame;
	
	@ManyToOne
	private Funcionario veterinario;
	
	public Float getPrecoConsulta() {
		return precoConsulta;
	}
	public void setPrecoConsulta(Float precoConsulta) {
		this.precoConsulta = precoConsulta;
	}
	public Funcionario getVeterinario() {
		return veterinario;
	}
	public void setVeterinario(Funcionario veterinario) {
		this.veterinario = veterinario;
	}
	public List<Exame> getListaExame() {
		return listaExame;
	}
	public void setListaExame(List<Exame> listaExame) {
		this.listaExame = listaExame;
	}
	
	@Override
	public String toString() {
		return "AgendamentoConsulta [listaExame=" + listaExame + "]";
	}
	
}
