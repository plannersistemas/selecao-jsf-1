package com.planner.treina.entity;



public enum Prioridade {
	
	ALTA("ALTA"), MEDIA("MEDIA"), BAIXA("BAIXA");
	
	private String descricao;
	
	private Prioridade(String label){
		this.descricao =  label;
	}
	
	
	
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	//para o converter nativo jsf
	public Prioridade[] getPrioridades() {
		return Prioridade.values();
	}
	
	
	@Override
	public String toString() {
		return this.descricao;
	}
	
}
