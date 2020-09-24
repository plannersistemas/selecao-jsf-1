package com.planner.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.planner.treina.entity.Tarefa;
import com.planner.util.ConnectionFactory;




public class Tarefa_DAO {

	
	public Tarefa save(Tarefa tarefa){
		EntityManager em = new ConnectionFactory().getConnection();
		try{
			em.getTransaction().begin();
			
			if(tarefa.getId() == null){
				em.persist(tarefa);
			}else{
				em.merge(tarefa);
			}
			
			em.getTransaction().commit();
			
		}catch (Exception e) {
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
		
		return tarefa;
	}
	
	
	public Tarefa findById(Integer id){
		Tarefa tarefa =  null;
		EntityManager em = new ConnectionFactory().getConnection();
		try{
			tarefa =  em.find(Tarefa.class, id);
		}catch (Exception e) {
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
		return tarefa;
	}
	
	public List findByUserId(Integer id){
		List<Tarefa> tarefas =  new ArrayList<Tarefa>();
		EntityManager em = new ConnectionFactory().getConnection();
		try{
			Query query = em.createNativeQuery("select * from tarefa where id_usuario = :id", Tarefa.class);
			query.setParameter("id", id);
			tarefas = query.getResultList();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
		return tarefas;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Tarefa> findAll(){
		List<Tarefa> tarefas =  new ArrayList<Tarefa>();
		EntityManager em = new ConnectionFactory().getConnection();	
		try{
			Query query = em.createNativeQuery("select * from tarefa", Tarefa.class);
			tarefas = query.getResultList();
			//tarefas =  em.createQuery("From tarefa").getResultList();
			
		}catch (Exception e) {
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
		return tarefas;
	}
	
}
