package com.planner.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;


import com.planner.dao.Usuario_DAO;
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
		
		try {
			Usuario_DAO udao = new Usuario_DAO();
			usuarioLogado = udao.login(usuario.getLogin(), usuario.getSenha()  );
			
			if(usuarioLogado ==  null){
				Messages.addGlobalError("Login ou senha incorretos !");
				return;
			}
			
			if(usuarioLogado != null){
				FacesContext context = FacesContext.getCurrentInstance();  
			    HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();  
			    HttpSession session = request.getSession();

			    request.getSession().setAttribute("usuarioLogado", usuarioLogado);
			    System.out.println("tarefa bean " + usuarioLogado );
			}
			
			
			
			
			Faces.redirect("./usuario.xhtml");
		} catch (IOException e) {
			Messages.addGlobalError(e.getMessage() );
			e.printStackTrace();
		}
	}
	
}
