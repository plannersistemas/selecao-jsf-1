package com.planner.dao;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.planner.treina.entity.Usuario;
import com.planner.util.HibernateUtil;



public class UsuarioDAO {
	
	public void save(Usuario usuario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		
		try {
			transacao = sessao.beginTransaction();
			sessao.save(usuario);
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
	
	public Usuario autentica(String login, String senha){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try{
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("usuario", "u");
			
			
			consulta.add(Restrictions.eq("u.login", login));
			
			SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("u.senha", hash.toHex() ));
			
			Usuario resultado = (Usuario)consulta.uniqueResult();
			return resultado;
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		
	
	}
	
	
	public Usuario login(String login, String senha){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Usuario usuario  = null;
		Query consulta = null;
		
		
		try {
			consulta = sessao.getNamedQuery("Usuario.login");
			consulta.setString("login", login);
			consulta.setString("senha", senha );
			
			usuario =  (Usuario) consulta.uniqueResult();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Usuario> usuarios = null;
		Query consulta = null;
		
		
		try {
			consulta = sessao.getNamedQuery("Usuario.listar");
			usuarios =  consulta.list();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return usuarios;
	}

}
