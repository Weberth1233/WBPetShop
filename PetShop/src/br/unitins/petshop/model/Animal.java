package br.unitins.petshop.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Animal extends DefaultEntity<Animal>{
	private String nome;
	private String peso;
	private String observacoes;
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	@ManyToOne
	private Cliente cliente;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
