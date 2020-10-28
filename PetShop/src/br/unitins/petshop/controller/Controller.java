package br.unitins.petshop.controller;

import java.io.Serializable;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.model.DefaultEntity;
import br.unitins.petshop.repository.Repository;

/*Esta classe vai extends somente daquelas que extendem de DefaultEntity*/
public abstract class Controller <T extends DefaultEntity<T>> implements Serializable{
	private static final long serialVersionUID = -6054233331412963859L;
	protected T entity;

	public Controller() {
		super();
	}
	public void salvar(){
		Repository<T> repo= new Repository<T>();
		try {
			repo.beginTransaction();
			repo.salvar(getEntity());
			repo.commitTransaction();
			Util.addInfoMessage("Operação realizado com sucesso!");
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			Util.addErrorMessage("Erro ao salvar!");
			e.printStackTrace();
		}
		limpar();
	}
	public void editar(T entity) {
		setEntity(entity);
	}
	public void remover() {
		Repository<T>repo= new Repository<T>();
		try {
			repo.beginTransaction();
			repo.remover(getEntity());
			Util.addInfoMessage("Remoção realizado com sucesso!");
			repo.commitTransaction();
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			Util.addErrorMessage("Erro ao remover do banco!");
			e.printStackTrace();
		}
		limpar();
	}
	public void limpar() {
		 setEntity(null);
	}
	public abstract T getEntity();
	
	public void setEntity(T entity) {
		this.entity = entity;
	}
}
