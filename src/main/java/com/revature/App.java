package com.revature;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.revature.models.User;

public class App 
{
    public static void main( String[] args )
    {
    	
    	SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		// Create a new user
		System.out.println("Creating new user object...");
		User user_1 = new User("Fadi", "Alzoubi", "fa@gmail.com");
		System.out.println(user_1);
		
		// Start a transaction
		session.beginTransaction();
		
		// Save the user
		System.out.println("Saving the user to the DB...");
		session.save(user_1);
		
		// Commit the transaction
		session.getTransaction().commit();
		
		System.out.println("User successfully saved!");
		System.out.println(user_1);
		
		session.close();
		
//		int id = 10;
//		
//		
//		// Get a new session and begin a transaction
//		session = factory.getCurrentSession();
//		session.beginTransaction();
//		
//		// Retrieve user from DB using PK
//		System.out.println("\nRetrieving user with id: " + id);
//		
//		// session.load lazily fetches, returns a proxy
//		User myUser = session.load(User.class, id);
//		System.out.println(myUser);
		
		
		try {
			
		} catch(Exception e) {
			// If an exception occurs, rollback the transaction
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			factory.close();
		}
    	
    	
    }
}
