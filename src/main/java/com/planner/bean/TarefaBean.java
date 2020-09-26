package com.planner.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Messages;


import com.planner.dao.Tarefa_DAO;
import com.planner.treina.entity.Tarefa;
import com.planner.treina.entity.Usuario;




@ManagedBean
@SessionScoped
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
			FacesContext context = FacesContext.getCurrentInstance();  
		    HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();  
		    HttpSession session = request.getSession();

		    Usuario logado = (Usuario)request.getSession().getAttribute("usuarioLogado");
		    System.out.println("tarefa bean " + logado );
			
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
		FacesContext context = FacesContext.getCurrentInstance();  
	    HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();  
	    HttpSession session = request.getSession();

	    Usuario usuarioLogado = (Usuario)request.getSession().getAttribute("usuarioLogado");
	    System.out.println(usuarioLogado);
		
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
			
			Messages.addGlobalInfo(msg);
			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao salvar a tarefa !");
			e.printStackTrace();
		}
		
	}
	
	public void editar(ActionEvent evento) {
		try {
			tarefa = (Tarefa) evento.getComponent().getAttributes().get("tarefaSelecionada");
			System.out.println("tarefa editar " + tarefa  );
			
			Messages.addGlobalInfo("Tarefa a ser editada: " + tarefa);

			Tarefa_DAO tdao = new Tarefa_DAO();
			tdao.save(tarefa);
			
			//Messages.addGlobalInfo("Tarefa editada com sucesso !");

			// Messages.addGlobalInfo("resp selecionado: " + responsavelOS.getNome());
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao editar a tarefa !");
			e.printStackTrace();
		}
	}
	
	
}
