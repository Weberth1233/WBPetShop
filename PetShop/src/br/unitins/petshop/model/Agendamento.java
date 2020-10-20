package br.unitins.petshop.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public abstract class Agendamento extends DefaultEntity<Agendamento>  {
	private Date data;
	private Time horaria_inicial;
	private Time horario_final;
	private int status;
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Time getHoraria_inicial() {
		return horaria_inicial;
	}
	public void setHoraria_inicial(Time horaria_inicial) {
		this.horaria_inicial = horaria_inicial;
	}
	public Time getHorario_final() {
		return horario_final;
	}
	public void setHorario_final(Time horario_final) {
		this.horario_final = horario_final;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
