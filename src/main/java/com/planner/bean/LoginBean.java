package com.planner.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import com.planner.dao.UsuarioDAO;
import com.planner.treina.entity.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private Usuario usuarioLogado;
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	@PostConstruct
	public void iniciar(){
		usuario = new Usuario();
		usuarioLogado = new Usuario();
	}
	
	public void sair(){
		usuarioLogado = null;
		
		if(usuarioLogado ==  null){
			try {
				Faces.redirect("./login.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void autenticar(){
		System.out.println("autenticar " + usuario.getLogin() );
		System.out.println("autenticar " + usuario.getSenha() );
	
		try {
			UsuarioDAO udao = new UsuarioDAO();
			usuarioLogado = udao.login(usuario.getLogin(), usuario.getSenha() );
			
			if(usuarioLogado ==  null){
				Messages.addGlobalError("Login ou senha incorretos !");
				return;
			}
			
			
			Faces.redirect("./usuario.xhtml");
		} catch (IOException e) {
			Messages.addGlobalError(e.getMessage() );
			e.printStackTrace();
		}
	}
	
}
