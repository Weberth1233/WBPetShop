package br.unitins.petshop.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
public class Funcionario extends DefaultEntity<Funcionario> {
	private String nome;
	private String cpf;
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	private Integer cargaHoraria;
	private String cargoOcupado;
	private Date dataAdmissao;
	private Float salario;
	private String login;
	private String email;
	private TipoFuncionario TipoFuncionario;
	/*@Size(min = 10, max = 15 ,message = "Senha fraca!")*/
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
	public String getCargoOcupado() {
		return cargoOcupado;
	}
	public void setCargoOcupado(String cargoOcupado) {
		this.cargoOcupado = cargoOcupado;
	}
	public Date getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
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
}
