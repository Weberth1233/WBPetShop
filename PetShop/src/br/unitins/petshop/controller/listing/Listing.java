package br.unitins.petshop.controller.listing;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.PrimeFaces;

import br.unitins.petshop.model.DefaultEntity;
import br.unitins.petshop.repository.Repository;

public class Listing <T extends DefaultEntity<T>> implements Serializable{
	
	private static final long serialVersionUID = 6681502910604238471L;
	private String page;
	private Repository<T> repository;
	private List<T> list;

	public Listing(String page, Repository<T> repository) {
		super();
		this.page = page;
		this.repository = repository;
	}
	
	public void open() {
		Map<String,Object> options = new HashMap<String, Object>();
		options.put("resizable", true);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("width", 800);
        options.put("height", 400);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
         
        PrimeFaces.current().dialog().openDynamic(getPage(), options, null);
	}
	
	public void select(int id) {
		T obj = repository.findById(id);
		PrimeFaces.current().dialog().closeDynamic(obj);
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Repository<T> getRepository() {
		return repository;
	}

	public void setRepository(Repository<T> repository) {
		this.repository = repository;
	}
	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
