package com.planner.test;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import com.planner.dao.Usuario_DAO;
import com.planner.treina.entity.Usuario;

public class UsuarioDAOTest {
	
	@Test
	//@Ignore
	public void salvar(){
		Usuario u1 = new Usuario();
		u1.setId(10);
		u1.setLogin("admin");
		u1.setUsuario("admin");
		u1.setSenha("admin");
		
		Usuario_DAO udao = new Usuario_DAO();
		udao.save(u1);
		
	}
	
	@Test
	@Ignore
	public void autenticar(){
		System.out.println("autenticar");
		//UsuarioDAO udao = new UsuarioDAO();
		Usuario_DAO udao = new Usuario_DAO();
		Usuario u1 = new Usuario();
		u1.setLogin("marcia@planner.com");
		u1.setSenha("marcia");
		
		//Usuario usuarioLogado = udao.autentica(u1.getLogin(), u1.getSenha() );
		Usuario usuarioLogado = udao.login(u1.getLogin() , u1.getSenha() );
		
	    System.out.println("Usuario pesquisado :" + usuarioLogado);
		
		
	}
	@Test
	@Ignore
	public void listar(){
		Usuario_DAO udao = new Usuario_DAO();
		List<Usuario> usuarios = udao.findAll();
		
		for(Usuario u : usuarios){
			System.out.println(u);
		}
		
	}

}
