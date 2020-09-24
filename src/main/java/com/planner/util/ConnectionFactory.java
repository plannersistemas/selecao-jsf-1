package com.planner.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE_PLANNER");
	
	public EntityManager getConnection(){
		return emf.createEntityManager();
	}
	
}
