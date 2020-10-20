package br.unitins.petshop.model;

import javax.persistence.Entity;


@Entity
public class Veterinario extends DefaultEntity<Veterinario>{
	
	private String crnv;
	private Funcionario funcionario;
	
	public String getCrnv() {
		return crnv;
	}
	public void setCrnv(String crnv) {
		this.crnv = crnv;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
}
