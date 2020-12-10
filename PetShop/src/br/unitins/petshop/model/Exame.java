package br.unitins.petshop.model;

import javax.persistence.Entity;
import javax.validation.constraints.DecimalMax;

@Entity
public class Exame extends DefaultEntity<Exame> {
	@DecimalMax("5000.00")
	private Float precoExame;
	private String nome;
	private String descricao;
	
	public Float getPrecoExame() {
		return precoExame;
	}
	public void setPrecoExame(Float precoExame) {
		this.precoExame = precoExame;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
