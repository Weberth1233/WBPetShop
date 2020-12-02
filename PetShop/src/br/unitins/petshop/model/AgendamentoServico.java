package br.unitins.petshop.model;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class AgendamentoServico extends Agendamento{
	private Float valorServico;
	
	@ManyToOne
	private Servico servico;
	
	@ManyToOne
	private Funcionario funcionarioAgenda;
	
	public Float getValorServico() {
		return valorServico;
	}
	public void setValorServico(Float valorServico) {
		this.valorServico = valorServico;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public Funcionario getFuncionarioAgenda() {
		return funcionarioAgenda;
	}
	public void setFuncionarioAgenda(Funcionario funcionarioAgenda) {
		this.funcionarioAgenda = funcionarioAgenda;
	}
	
}
