package br.unitins.petshop.model;

import javax.persistence.Entity;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;

@Entity
public class Servico extends DefaultEntity<Servico> {
	@DecimalMax("5000.00")
	private Float preco;
	
	@NotBlank(message = "Descri��o n�o pode ser um campo vazio!")
	private String descricao;
	
	@NotBlank(message = "Nome n�o pode ser um campo vazio!")
	private String nome;
	
	public Float getPreco() {
		return preco;
	}
	public void setPreco(Float preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
