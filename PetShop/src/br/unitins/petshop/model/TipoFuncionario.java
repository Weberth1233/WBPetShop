package br.unitins.petshop.model;

public enum TipoFuncionario {
	ATENDENTE(),GERENTE(),TOSADOR();
	
	private String descricao;
	private int id;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
