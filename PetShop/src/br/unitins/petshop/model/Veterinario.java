package br.unitins.petshop.model;

import javax.persistence.Entity;


@Entity
public class Veterinario extends DefaultEntity<Veterinario>{
	
	private String crnv;
	
	public String getCrnv() {
		return crnv;
	}
	public void setCrnv(String crnv) {
		this.crnv = crnv;
	}
}
