package com.planner.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;


import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import com.planner.dao.TarefaDAO;
import com.planner.dao.Tarefa_DAO;
import com.planner.treina.entity.Tarefa;
import com.planner.treina.entity.Usuario;


@ManagedBean
@ViewScoped
public class TarefaBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tarefa tarefa;
	private List<Tarefa> tarefas;
	
	
	
	
	
	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	public List<Tarefa> getTarefas() {
		return tarefas;
	}
	
	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	
	@PostConstruct
	public void listarTodas(){
		try{
			//TarefaDAO tdao = new TarefaDAO();
			Tarefa_DAO  tdao = new Tarefa_DAO();
			tarefas = tdao.findAll();
			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro listar as tarefas !");
			e.printStackTrace();
		}
	}

	public void novo(){
		tarefa = new Tarefa();
	}
	
	public void salvar(){
		
		String msg =  "Tarefa salva com sucesso";
		
		try{
			//TarefaDAO tdao = new TarefaDAO();
			Tarefa_DAO tdao = new Tarefa_DAO();
			tdao.save(tarefa);
			
			//atualizar a tabela
			tarefa = new Tarefa();
			tarefas = tdao.findAll();
			
			Messages.addGlobalInfo(msg + " " + tarefa);
			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao salvar a tarefa !");
			e.printStackTrace();
		}
		
	}
}
