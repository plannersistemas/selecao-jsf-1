package com.planner.util;


import java.sql.Connection;
import java.sql.SQLException;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	
	private static SessionFactory sessionFactory = null ;// buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
        	
        	
            Configuration configuration = new Configuration().configure();
        	ServiceRegistry registry = new StandardServiceRegistryBuilder()
        			.applySettings( configuration.getProperties() ).build();
        	
        	
        	SessionFactory fabrica = configuration.buildSessionFactory(registry);
        	return fabrica;
        	
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
    	sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
    
   
    
}