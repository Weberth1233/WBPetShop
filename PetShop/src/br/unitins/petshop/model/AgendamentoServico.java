package br.unitins.petshop.model;

import javax.persistence.Entity;

@Entity
public class AgendamentoServico extends Agendamento{
	private Float valorServico;
	
	public Float getValorServico() {
		return valorServico;
	}
	public void setValorServico(Float valorServico) {
		this.valorServico = valorServico;
	}
}
