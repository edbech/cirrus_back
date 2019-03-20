package com.revature;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.revature.models.Game;
import com.revature.models.Message;
import com.revature.models.User;

public class App 
{
    public static void main( String[] args )
    {
    	
    	SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Message.class)
				.addAnnotatedClass(Game.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		//------------------------------------------------------------------------------------------
		//---------------------------User Creation/Retrieval Testing--------------------------------
		//------------------------------------------------------------------------------------------
		
//		// Create a new user
//		System.out.println("Creating new user object...");
//		User user_1 = new User("Ankit", "Patel", "ap@gmail.com");
//		System.out.println(user_1);
//		
//		// Start a transaction
//		session.beginTransaction();
//		
//		// Save the user
//		System.out.println("Saving the user to the DB...");
//		session.save(user_1);
//		
//		// Commit the transaction
//		session.getTransaction().commit();
//		
//		System.out.println("User successfully saved!");
//		System.out.println(user_1);
//		
//		session.close();
//		
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
		
		//------------------------------------------------------------------------------------------
		//--------------------------Message Creation/Retrieval Testing------------------------------
		//------------------------------------------------------------------------------------------
		
//		// Create a new message
//		System.out.println("Creating new user object...");
//		
//		Message message_1 = new Message(10, 12, 5, "Hello Friend!");
//		System.out.println(message_1);
//		
//		// Start a transaction
//		session.beginTransaction();
//		
//		// Save the message
//		System.out.println("Saving the message to the DB...");
//		session.save(message_1);
//		
//		// Commit the transaction
//		session.getTransaction().commit();
//		
//		System.out.println("Message successfully saved!");
//		System.out.println(message_1);
//		
//		session.close();
//		
//		int id = 1;
//		
//		// Get a new session and begin a transaction
//		session = factory.getCurrentSession();
//		session.beginTransaction();
//		
//		// Retrieve user from DB using PK
//		System.out.println("\nRetrieving message with id: " + id);
//		
//		// session.load lazily fetches, returns a proxy
//		Message myMessage = session.load(Message.class, id);
//		System.out.println(myMessage);
//		
//		session.close();
		
		//------------------------------------------------------------------------------------------
		//----------------------------Game Creation/Retrieval Testing-------------------------------
		//------------------------------------------------------------------------------------------	
		
		// Create a new game
//		System.out.println("Creating new game object...");
//		
//		//public Game(int playerX, int playerO, boolean isPublic) 
//		Game game_1 = new Game(10, 12, 0);
//		System.out.println(game_1);
//		
//		// Start a transaction
//		session.beginTransaction();
//		
//		// Save the game
//		System.out.println("Saving the game to the DB...");
//		session.save(game_1);
//		
//		// Commit the transaction
//		session.getTransaction().commit();
//		
//		System.out.println("Game successfully saved!");
//		System.out.println(game_1);
		
//		session.close();
		// GET GAME NOT WORKING
		int id = 1;
		
		// Get a new session and begin a transaction
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		// Retrieve user from DB using PK
		System.out.println("\nRetrieving game with id: " + id);
		
		// session.load lazily fetches, returns a proxy
		Game myGame = session.get(Game.class, id);
		System.out.println(myGame);
		
		session.close();
		
		
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
