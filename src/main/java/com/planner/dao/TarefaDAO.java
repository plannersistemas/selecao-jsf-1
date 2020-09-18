package com.planner.dao;



import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.planner.treina.entity.Tarefa;
import com.planner.treina.entity.Usuario;
import com.planner.util.HibernateUtil;




public class TarefaDAO {
	
	public void save(Tarefa tarefas) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		
		try {
			transacao = sessao.beginTransaction();
			sessao.save(tarefas);
			transacao.commit();
			
		}catch (RuntimeException ex) {
			if(transacao != null) {
				transacao.rollback();
			}
			throw ex;
		}finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Tarefa> listarTodas(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Tarefa> tarefas = null;
		Query consulta = null;
		
		
		try {
			consulta = sessao.getNamedQuery("Tarefa.listarTodas");
			tarefas =  consulta.list();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return tarefas;
	}
	
	@SuppressWarnings("unchecked")
	public List<Tarefa> listarTarefasFromUser(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Tarefa> tarefas = null;
		Query consulta = null;
		
		
		try {
			consulta = sessao.getNamedQuery("Tarefa.listarTodas");
			tarefas =  consulta.list();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return tarefas;
	}

}
