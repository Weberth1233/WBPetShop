package br.unitins.petshop.model;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Agendamento extends DefaultEntity<Agendamento>  {
	private String titulo;
	private LocalDateTime data_incio;
	private LocalDateTime data_fim;
	private String descricao;
	@ManyToOne
	private Cliente clienteAgenda;
	
	@ManyToOne
	private Animal animalAgenda;
	
	private boolean status;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public LocalDateTime getData_incio() {
		return data_incio;
	}
	public void setData_incio(LocalDateTime data_incio) {
		this.data_incio = data_incio;
	}
	public LocalDateTime getData_fim() {
		return data_fim;
	}
	public void setData_fim(LocalDateTime data_fim) {
		this.data_fim = data_fim;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Cliente getClienteAgenda() {
		return clienteAgenda;
	}
	public void setClienteAgenda(Cliente clienteAgenda) {
		this.clienteAgenda = clienteAgenda;
	}
	public Animal getAnimalAgenda() {
		return animalAgenda;
	}
	public void setAnimalAgenda(Animal animalAgenda) {
		this.animalAgenda = animalAgenda;
	}
}
