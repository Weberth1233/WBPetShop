package br.unitins.petshop.model;

import javax.persistence.Entity;

@Entity
public class Servico extends DefaultEntity<Servico> {
	private Float preco;
	private String descricao;
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
