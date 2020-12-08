package br.unitins.petshop.model;

public enum StatusConsulta {
	AGUARDANDO(0,"Aguardando"),CONCLUIDO(1,"Concluído");
	private String descricao;
	private int id;
	
	StatusConsulta(int id, String desc) {
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
	public static StatusConsulta valueOf(Integer id) {
		if(id == null) {
			return null;
		}
		for (StatusConsulta status : StatusConsulta.values()) {
			if(status.getId() == id) 
				return status;
		}
		return null;
	}
}
