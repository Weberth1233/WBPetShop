package br.unitins.petshop.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Entity
public class Veterinario extends DefaultEntity<Veterinario>{
	
	private String crnv;
	@OneToOne(cascade = CascadeType.ALL)
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
