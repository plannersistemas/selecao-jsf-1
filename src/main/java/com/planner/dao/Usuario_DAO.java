package com.planner.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.planner.treina.entity.Tarefa;
import com.planner.treina.entity.Usuario;
import com.planner.util.ConnectionFactory;

public class Usuario_DAO {
	
	public Usuario save(Usuario Usuario){
		EntityManager em = new ConnectionFactory().getConnection();
		try{
			em.getTransaction().begin();
			
			if(Usuario.getId() == null){
				em.persist(Usuario);
			}else{
				em.merge(Usuario);
			}
			
			em.getTransaction().commit();
			
		}catch (Exception e) {
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
		
		return Usuario;
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
		try{
			Query query = em.createNativeQuery("select * from usuario where login = :login and senha = :senha", Usuario.class);
			query.setParameter("login", login);
			query.setParameter("senha", pass);
			
			usuario =(Usuario)query.getSingleResult();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
		return usuario;
	}
	
	
	public List findAll(){
		List<Usuario> usuarios =  null;
		EntityManager em = new ConnectionFactory().getConnection();
		
		try{
			usuarios =  em.createQuery("From usuario").getResultList();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
		return usuarios;
	}
	

}
