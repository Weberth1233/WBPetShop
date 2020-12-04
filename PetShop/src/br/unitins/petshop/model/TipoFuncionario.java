package br.unitins.petshop.model;


public enum TipoFuncionario {
	GERENTE(0,"Gerente"),FUNCIONARIO(1,"Funcionário"), VETERINARIO(2,"Veterinário");
	private String descricao;
	private int id;
	
	TipoFuncionario(int id, String desc) {
		this.id = id;
		this.descricao = desc;
	}
	
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
	public static TipoFuncionario valueOf(Integer id) {
		if(id == null) {
			return null;
		}
		for (TipoFuncionario tipoFunc : TipoFuncionario.values()) {
			if(tipoFunc.getId() == id) 
				return tipoFunc;
		}
		return null;
	}
}
