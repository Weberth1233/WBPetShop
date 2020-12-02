package br.unitins.petshop.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class AgendamentoConsulta extends Agendamento {
	private Float precoConsulta;
	
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
	
}
