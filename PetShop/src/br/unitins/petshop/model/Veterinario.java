package br.unitins.petshop.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Veterinario extends DefaultEntity<Veterinario>{
	private String nome;
	
	private Integer cargaHoraria;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAdmissao;
	
	private Float salario;
	
	private String crnv;
	
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
	public String getCrnv() {
		return crnv;
	}
	public void setCrnv(String crnv) {
		this.crnv = crnv;
	}
	@PrePersist
	private void adicionarDataAdmissao() {
		dataAdmissao = new Date();
	}
}
