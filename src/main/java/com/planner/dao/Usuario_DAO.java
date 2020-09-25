package com.planner.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.common.primitives.Bytes;
import com.planner.treina.entity.Tarefa;
import com.planner.treina.entity.Usuario;
import com.planner.util.ConnectionFactory;

public class Usuario_DAO {
	
	public Usuario save(Usuario usuario){
		EntityManager em = new ConnectionFactory().getConnection();
		try{
			
			usuario.setSenha(DigestUtils.md5Hex( usuario.getSenha() ) );
			
			
			em.getTransaction().begin();
			
			if(usuario.getId() == null){
				em.persist(usuario);
			}else{
				em.merge(usuario);
			}
			
			em.getTransaction().commit();
			
		}catch (Exception e) {
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
		
		return usuario;
	}
	
	
	public Usuario findById(Integer id){
		Usuario Usuario =  null;
		EntityManager em = new ConnectionFactory().getConnection();
		
		try{
			Usuario =  em.find(Usuario.class, id);
		}catch (Exception e) {
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
		return Usuario;
	}
	
	
	public Usuario login(String login, String pass){
		Usuario usuario =  null;
		EntityManager em = new ConnectionFactory().getConnection();
		
		//System.out.println("login " + login + " " + pass);
		//System.out.println("Senha descriptografada " + DigestUtils.md5Hex(pass) );
		
		try{
			Query query = em.createNativeQuery("select * from usuario where login = :login and senha = :senha", Usuario.class);
			query.setParameter("login", login);
			query.setParameter("senha", DigestUtils.md5Hex(pass) );
			
			usuario =(Usuario)query.getSingleResult();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findAll(){
		List<Usuario> usuarios =  new ArrayList<Usuario>();
		EntityManager em = new ConnectionFactory().getConnection();
		
		try{
			Query query = em.createNativeQuery("select * from usuario", Usuario.class);
			usuarios = query.getResultList();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
		return usuarios;
	}
	

}
