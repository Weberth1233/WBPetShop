package br.unitins.petshop.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.application.Session;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.Exame;
import br.unitins.petshop.model.Funcionario;
import br.unitins.petshop.model.Servico;
import br.unitins.petshop.model.TipoFuncionario;
import br.unitins.petshop.repository.FuncionarioRepository;
import br.unitins.petshop.repository.ServicoRepository;
@Named
@ViewScoped
public class FuncionarioController extends Controller<Funcionario>{

	private static final long serialVersionUID = 9011861114364637235L;
	private List<Funcionario>listaFuncionario;
	private String buscar;
	
	private Funcionario funcionario;
	
	private InputStream fotoInputStream = null;
	private String nomeFoto = null;

	@Override
	public Funcionario getEntity() {
		if(entity == null) {
			entity = (Funcionario) Session.getInstance().getAttribute("dadosFunc");
			entity.setServico(new Servico());
		}
		return entity;
	}
	
	@Override
	public void salvar() {
		FuncionarioRepository repo = new FuncionarioRepository();
		try {
			repo.beginTransaction();
			setEntity(repo.salvar(getEntity()));
			if (! Util.saveImageUsuario(fotoInputStream, "png", getEntity().getId())) 
				throw new RepositoryException("Erro ao salvar. Não foi possível salvar a imagem do usuário.");
			repo.commitTransaction();
			Util.addInfoMessage("Operação realizada com sucesso.");
			limpar();
		}catch (RepositoryException e) {
			repo.rollbackTransaction();
			System.out.println("Erro ao salvar.");
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
		Session.getInstance().setAttribute("dadosFunc", new Funcionario());
	}
	/*public void pesquisar() {
		FuncionarioRepository repo = new FuncionarioRepository();
		try {
			setListaFuncionario(repo.findAll());
		}catch (RepositoryException e) {
			Util.addErrorMessage("Erro ao retornar a lista de funcionarios!");
			setListaFuncionario(null);
			e.printStackTrace();
		}
	}*/
	@Override
	public void remover() {
		super.remover();
		Session.getInstance().setAttribute("dadosFunc", new Funcionario());
	}
	
	public void pesquisarPorNome() {
		FuncionarioRepository repo = new FuncionarioRepository();
		try {
			setListaFuncionario(repo.findByFuncionarioServico(getBuscar()));
		} catch (RepositoryException e) {
			Util.addErrorMessage("Erro ao pesquisar cliente");
			e.printStackTrace();
			setListaFuncionario(null);
		}
		buscar = null;
	}
	
	public TipoFuncionario[] getListaFuncionarios() {
		return TipoFuncionario.values();
	}
	
	public void upload(FileUploadEvent event) {
		UploadedFile uploadFile = event.getFile();
		System.out.println("nome arquivo: " + uploadFile.getFileName());
		System.out.println("tipo: " + uploadFile.getContentType());
		System.out.println("tamanho: " + uploadFile.getSize());

		if (uploadFile.getContentType().equals("image/png")) {
			try {
				fotoInputStream = uploadFile.getInputStream();
				nomeFoto = uploadFile.getFileName();
				System.out.println("inputStream: " + uploadFile.getInputStream().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Util.addInfoMessage("Upload realizado com sucesso.");
		} else {
			Util.addErrorMessage("O tipo da image deve ser png.");
		}

	}
	public String getNomeFoto() {
		if (nomeFoto == null) 
			return "Selecione uma foto ...";
		return "Arquivo: "+ nomeFoto + " (Clique para alterar a foto...)";
	}
	
	public List<Servico>completeMarca(String query) {
		ServicoRepository repo = new ServicoRepository();
		try {
			return repo.findByNome(query, 6); 
		} catch (RepositoryException e) {
			e.printStackTrace();
			return new ArrayList<Servico>();
		}
	}
	public List<Funcionario> getListaFuncionario() {
		if(listaFuncionario==null)
			listaFuncionario= new ArrayList<Funcionario>();
		return listaFuncionario;
	}
	public void setListaFuncionario(List<Funcionario> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}
	public void limpar() {
		entity = null;
	}
	public String getBuscar() {
		return buscar;
	}
	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String adicionarNovo() {
		Session.getInstance().setAttribute("dadosFunc", new Funcionario());
		return "cadfuncionario.xhtml?faces-redirect=true";
	}
	public String editarFuncionario(Funcionario funcionario) {
		setEntity(funcionario);
		Session.getInstance().setAttribute("dadosFunc", getEntity());
		return "cadfuncionario.xhtml?faces-redirect=true";
	}
}