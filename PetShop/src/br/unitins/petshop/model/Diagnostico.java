package br.unitins.petshop.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Diagnostico extends DefaultEntity<Diagnostico> {
	@ManyToOne
	private AgendamentoConsulta agendamento;
	@ManyToOne
	private Funcionario veterinario;
	@ManyToOne
	private Exame exame;
	
	private String resultado;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	public AgendamentoConsulta getAgendamento() {
		return agendamento;
	}
	public void setAgendamento(AgendamentoConsulta agendamento) {
		this.agendamento = agendamento;
	}
	public Funcionario getVeterinario() {
		return veterinario;
	}
	public void setVeterinario(Funcionario veterinario) {
		this.veterinario = veterinario;
	}
	public Exame getExame() {
		return exame;
	}
	public void setExame(Exame exame) {
		this.exame = exame;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
}
