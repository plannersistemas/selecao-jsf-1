package com.planner.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.omnifaces.util.Messages;

import com.planner.dao.UsuarioDAO;
import com.planner.treina.entity.Usuario;

@ManagedBean
@ViewScoped
public class NewUserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	public String nova(){
		System.out.println("entrou no nova user");
		return "/newuser.xhtml?faces-redirect=true";
	}
	
	
	public void novo(){
		usuario = new Usuario();
	}
	
	public String salvar(){
		String msg =  "Usuário salvo com sucesso";
		
	  
		try{
			UsuarioDAO udao = new UsuarioDAO();
			udao.save(usuario);
			
			Messages.addGlobalInfo(msg + " " + usuario);
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/newuser.xhtml");
		
			
		}catch (RuntimeException | IOException e) {
			Messages.addGlobalError("Erro ao salvar o usuário !");
			e.printStackTrace();
		}
		return "login.xhtml?faces-redirect=true";
		
	}
	
	
	
}
