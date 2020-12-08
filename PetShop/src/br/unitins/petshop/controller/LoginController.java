package br.unitins.petshop.controller;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.application.Session;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.Funcionario;
import br.unitins.petshop.repository.FuncionarioRepository;

@Named
@RequestScoped
public class LoginController {
	private Funcionario funcionario;
	private String login;
	private String senha;

	public Funcionario getFuncionario() {
		if(funcionario==null)
			funcionario = new Funcionario();
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
	private void limpar() {
		senha=null;
		login=null;
	}
	public void logar() {
		FuncionarioRepository repo= new FuncionarioRepository();
		try {
			Funcionario func = repo.verificarlogin(getLogin(), Util.hashSHA256(getSenha()));
			if(func != null) {
				Session.getInstance().setAttribute("funcionarioLogado", func);
				Util.redirect("/PetShop/faces/pages/index.xhtml");
			}
		}
		catch (RepositoryException e) {
			Util.addErrorMessage("Funcionário não encontrado!");
			e.printStackTrace();
		}
	}
}
