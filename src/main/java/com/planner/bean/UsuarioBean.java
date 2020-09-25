package com.planner.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import org.omnifaces.util.Messages;

import com.planner.dao.Usuario_DAO;
import com.planner.treina.entity.Usuario;



@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Usuario usuarioLogin;
	
	private List<Usuario> usuarios;
	
	
	
	

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	 
	public Usuario getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(Usuario usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
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
			Usuario_DAO udao = new Usuario_DAO();
			usuarios = udao.findAll();
			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro listar os usuários !");
			e.printStackTrace();
		}
	}

	public void novo(){
		usuario = new Usuario();
		System.out.println("entrou no novo");
		
	}
	
	
	public String nova(){
		return "newuser.xhtml?faces-redirect=true";
	}
	
	
	
	public void salvarLogin(){
		
		
		System.out.println("Novo usuario " + usuarioLogin);
	}
	
	public void salvar(){
		
		String msg =  "Usuário salvo com sucesso";
		
		try{
			Usuario_DAO udao = new Usuario_DAO();
			udao.save(usuario);
			
			//atualizar a tabela
			usuario = new Usuario();
			usuarios = udao.findAll();
			
			Messages.addGlobalInfo(msg + " " + usuario);
			
		}catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao salvar o usuário !");
			e.printStackTrace();
		}
		
	}
	
	
		

}
