package com.planner.test;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import com.planner.dao.UsuarioDAO;
import com.planner.dao.Usuario_DAO;
import com.planner.treina.entity.Usuario;

public class UsuarioDAOTest {
	
	@Test
	@Ignore
	public void salvar(){
		Usuario u1 = new Usuario();
		
		u1.setLogin("antonio@planner.com");
		u1.setUsuario("Antonio.Antonio");
		
		//u1.setSenhaSemCriptografia("antonio");
		u1.setSenha("antonio");
		//UsuarioDAO udao = new UsuarioDAO();
		Usuario_DAO udao = new Usuario_DAO();
		udao.save(u1);
		
	}
	
	@Test
	//@Ignore
	public void autenticar(){
		System.out.println("autenticar");
		//UsuarioDAO udao = new UsuarioDAO();
		Usuario_DAO udao = new Usuario_DAO();
		Usuario u1 = new Usuario();
		u1.setLogin("fernanda@planner.com");
		u1.setSenha("fernanda");
		
		//Usuario usuarioLogado = udao.autentica(u1.getLogin(), u1.getSenha() );
		Usuario usuarioLogado = udao.login(u1.getLogin() , u1.getSenha() );
		
	    System.out.println("Usuario pesquisado :" + usuarioLogado);
		
		
	}
	@Test
	@Ignore
	public void listar(){
		UsuarioDAO udao = new UsuarioDAO();
		List<Usuario> usuarios = udao.listar();
		
		for(Usuario u : usuarios){
			System.out.println(u);
		}
		
	}

}
