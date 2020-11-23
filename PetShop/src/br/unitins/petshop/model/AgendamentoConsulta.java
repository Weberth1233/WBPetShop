package br.unitins.petshop.model;

import javax.persistence.Entity;


@Entity
public class AgendamentoConsulta extends Agendamento {
	private Float precoConsulta;

	public Float getPrecoConsulta() {
		return precoConsulta;
	}
	public void setPrecoConsulta(Float precoConsulta) {
		this.precoConsulta = precoConsulta;
	}
}
