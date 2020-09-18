package com.planner.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.planner.dao.TarefaDAO;
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
		t1.setTitulo("tarefa 1 user 1");
		t1.setDescricao("desc. tarefa 1 user 1");
		
		Tarefa t2 = new Tarefa();
		t2.setUsuario(u1);
		t2.setTitulo("tarefa 2 user 1");
		t2.setDescricao("desc. tarefa 2 user 1");
		
		
		TarefaDAO tdao = new TarefaDAO();
		tdao.save(t1);
		tdao.save(t2);
		
		
	}
	
	@Test
	public void listar(){
		TarefaDAO tdao = new TarefaDAO();
		List<Tarefa> tarefas = tdao.listarTodas();
		
		for(Tarefa t : tarefas){
			System.out.println(t);
		}
		
	}
	
}
