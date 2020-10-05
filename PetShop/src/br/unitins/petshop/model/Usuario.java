package br.unitins.petshop.model;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Usuario extends DefaultEntity{
	@NotBlank(message = "Nome não pode ser uma campo nulo!")
	@Size(min = 20, max = 35 ,message = "Nome muito pequeno!")
	private String nome;
	
	@CPF(message = "CPF inválido!")
	private String cpf;
	
	@Past(message = "Data não pode estar no futuro!")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	private String telefone;
	
	@NotBlank(message = "Endereço não pode ser um campo nulo!")
	private String endereco;
	
	@Email(message = "E-mail inválido!")
	private String email;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
