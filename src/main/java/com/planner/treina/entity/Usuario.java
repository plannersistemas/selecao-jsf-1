package com.planner.treina.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="usuario")
@NamedQueries({
	@NamedQuery(name="Usuario.listar", query= "SELECT u FROM Usuario u"),
	@NamedQuery(name="Usuario.login", query= "SELECT u FROM Usuario u WHERE u.login =:login AND u.senha =:senha" )
})


public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String usuario;
	
	private String senha;
	
	@Transient
	private String senhaSemCriptografia;
	
	
	private String login;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	
	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}
	
	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", login=" + login + "]";
	}
	
	
	
	
}
