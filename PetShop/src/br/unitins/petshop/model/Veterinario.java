package br.unitins.petshop.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Veterinario extends DefaultEntity{
	private String nome;
	
	private Integer cargaHoraria;
	
	@Temporal(TemporalType.DATE)
	private Date dataAdmissao;
	
	private Float salario;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public Date getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	public Float getSalario() {
		return salario;
	}
	public void setSalario(Float salario) {
		this.salario = salario;
	}
}
