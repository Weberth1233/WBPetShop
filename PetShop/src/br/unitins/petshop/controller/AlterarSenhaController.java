package br.unitins.petshop.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.application.Session;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.Funcionario;
import br.unitins.petshop.repository.FuncionarioRepository;

@Named
@ViewScoped
public class AlterarSenhaController implements Serializable {

	private static final long serialVersionUID = 1841555940214236400L;
	private String antigaSenha;
	private String novaSenha;

	public void alterarSenha() {
		Funcionario funcionarioLogado = (Funcionario) Session.getInstance().getAttribute("funcionarioLogado");
		FuncionarioRepository repo= new FuncionarioRepository();
		try {
			if(!(getAntigaSenha().isBlank() || getNovaSenha().isBlank())) {
				Funcionario func = repo.verificarSenha(Util.hashSHA256(getAntigaSenha()),funcionarioLogado.getId());
				if(func != null) {
					repo.beginTransaction();
					func.setSenha(Util.hashSHA256(novaSenha));
					repo.salvar(func);
					repo.commitTransaction();
					Util.addInfoMessage("Senha alterada com sucesso!");
				}
			}else {
				Util.addErrorMessage("Informe antiga e nova senha!");
			}
		}catch (RepositoryException e) {
			repo.rollbackTransaction();
			Util.addErrorMessage("Senha não encontrado!");
			e.printStackTrace();

		}
	}
	public String getAntigaSenha() {
		return antigaSenha;
	}
	public void setAntigaSenha(String antigaSenha) {
		this.antigaSenha = antigaSenha;
	}
	public String getNovaSenha() {
		return novaSenha;
	}
	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}


}
