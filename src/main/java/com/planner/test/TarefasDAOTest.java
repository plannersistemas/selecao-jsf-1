package com.planner.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.planner.dao.TarefaDAO;
import com.planner.dao.Tarefa_DAO;
import com.planner.dao.UsuarioDAO;
import com.planner.treina.entity.Tarefa;
import com.planner.treina.entity.Usuario;

public class TarefasDAOTest {

	@Test
	@Ignore
	public void salvar(){
		Usuario u1 = new Usuario();
		u1.setLogin("roberto@planner.com");
		u1.setUsuario("Roberto.Sousa");
		u1.setSenha("roberto");
		u1.setId(1);
		
		Tarefa t1 = new Tarefa();
		t1.setUsuario(u1);
		t1.setTitulo("tarefa01 roberto ");
		t1.setDescricao("desc. tarefa 01 roberto");
		
		Tarefa t2 = new Tarefa();
		t2.setUsuario(u1);
		t2.setTitulo("tarefa 02 roberto");
		t2.setDescricao("desc. tarefa 02 roberto");
		
		
		//TarefaDAO tdao = new TarefaDAO();
		Tarefa_DAO  tdao = new Tarefa_DAO();
		tdao.save(t1);
		tdao.save(t2);
		
		
	}
	
	@Test
	@Ignore
	public void listar(){
		Tarefa_DAO  tdao = new Tarefa_DAO();
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		tarefas = tdao.findAll();
		
		for(Tarefa t : tarefas){
			System.out.println(t);
		}
		
	}
	
	@Test
	public void listarAllFromUser(){
		Tarefa_DAO  tdao = new Tarefa_DAO();
		Integer iduser = 1;
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		tarefas = tdao.findByUserId(iduser);
		
		for(Tarefa t : tarefas){
			System.out.println(t);
		}
		
	}
	
	@Test
	@Ignore
	public void listById(){
		Tarefa_DAO  tdao = new Tarefa_DAO();
		Tarefa t = tdao.findById(1);
		System.out.println(t);
	}
	
}
