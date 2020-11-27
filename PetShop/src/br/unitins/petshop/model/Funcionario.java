package br.unitins.petshop.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.br.CPF;
@Entity
public class Funcionario extends DefaultEntity<Funcionario> {
	private String nome;
	
	@CPF(message = "CPF não pode ser um campo nulo!")
	@Column(unique=true, nullable=false) 
	private String cpf;
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(fetch = FetchType.LAZY)
	private Servico servico;
	
	private Integer cargaHoraria;
	
	private Date dataAdmissao;
	
	@NotNull(message = "Salario não pode ser um campo nulo!")
	private Float salario;
	
	@NotBlank(message= "Login não pode ser um campo nulo!")
	private String login;
	
	@NotBlank(message = "E-mail não pode ser um campo nulo!")
	@Column(unique=true, nullable=false) 
	private String email;
	
	private TipoFuncionario TipoFuncionario;
	
	@NotBlank(message = "Senha não pode ser um campo nulo!")
	@Size(min = 9, message = "Senha fraca!")
	@Column(unique=true, nullable=false) 
	private String senha;
	
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
	@PrePersist
	private void atualizaDataAdmissao() {
		dataAdmissao = new Date();
	}
	public Float getSalario() {
		return salario;
	}
	public void setSalario(Float salario) {
		this.salario = salario;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public TipoFuncionario getTipoFuncionario() {
		return TipoFuncionario;
	}
	public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
		TipoFuncionario = tipoFuncionario;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
}
