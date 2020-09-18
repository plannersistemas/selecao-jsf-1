package com.planner.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import org.omnifaces.util.Messages;

import com.planner.dao.UsuarioDAO;
import com.planner.treina.entity.Usuario;



@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Usuario usuarioNovoLogin;
	private List<Usuario> usuarios;
	
	
	
	public Usuario getUsuarioNovoLogin() {
		return usuarioNovoLogin;
	}

	public void setUsuarioNovoLogin(Usuario usuarioNovoLogin) {
		this.usuarioNovoLogin = usuarioNovoLogin;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	 
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@PostConstruct
	public void listarTodos(){
		try{
			UsuarioDAO udao = new UsuarioDAO();
			usuarios = udao.listar();
			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro listar os usuários !");
			e.printStackTrace();
		}
	}

	public void novo(){
		usuario = new Usuario();
		usuarioNovoLogin = new Usuario();
	}
	
	public void salvar(){
		
		String msg =  "Usuário salvo com sucesso";
		
		try{
			UsuarioDAO udao = new UsuarioDAO();
			udao.save(usuario);
			
			//atualizar a tabela
			usuario = new Usuario();
			usuarios = udao.listar();
			
			Messages.addGlobalInfo(msg + " " + usuario);
			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao salvar o usuário !");
			e.printStackTrace();
		}
		
	}
	
public void salvarNovoLogin(){
		//usuarioNovoLogin = new Usuario();
		
		String msg =  "Usuário salvo com sucesso";
		
		try{
		
			UsuarioDAO udao = new UsuarioDAO();
			udao.save(usuarioNovoLogin);
			
			
			Messages.addGlobalInfo(msg + " " + usuario);
			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao salvar o usuário !");
			e.printStackTrace();
		}
		
	}

}
