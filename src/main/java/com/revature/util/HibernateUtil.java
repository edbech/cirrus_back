package com.revature.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.revature.models.Game;
import com.revature.models.Message;
import com.revature.models.User;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	private static SessionFactory buildSessionFactory() {
		
		try {
			
			// Create the SessionFactory using the hibernate.cfg.xml file
			Configuration config = new Configuration();
			config.configure("hibernate.cfg.xml"); // not required if you kept the default config file name
			config.addAnnotatedClass(User.class)
			.addAnnotatedClass(Game.class)
			.addAnnotatedClass(Message.class);
			
			return config.buildSessionFactory();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return (sessionFactory == null) ? sessionFactory = buildSessionFactory() : sessionFactory;
	}

}
