package com.revature.services;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import com.revature.models.Game;
import com.revature.models.Message;
import com.revature.models.User;

@Service
public class UserService {

	public UserService() {
		super();
	}


	public List<User> getAll() {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Game.class)
				.addAnnotatedClass(Message.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			System.out.println("\nRetrieving all users");

			@SuppressWarnings("unchecked")
			List<User> users = session.createQuery("from User").getResultList();
			return users;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;

		} finally {
			factory.close();
		}

	}
	
	public List<User> getHighScores() {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Game.class)
				.addAnnotatedClass(Message.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			System.out.println("\nRetrieving all users");

			@SuppressWarnings("unchecked")
			List<User> users = session.createQuery("SELECT username, score FROM User u WHERE u.score > 0 ORDER BY u.score desc").getResultList();
			return users;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;

		} finally {
			factory.close();
		}

	}

	public User getUserById(int id) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Game.class)
				.addAnnotatedClass(Message.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			// Retrieve user from DB using PK
			System.out.println("\nRetrieving user with id: " + id);

			// session.load lazily fetches, returns a proxy
			User myUser = session.get(User.class, id);
			return myUser;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		} finally {
			factory.close();
		}

	}
	
	public User getIdByUsername(String username) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Game.class)
				.addAnnotatedClass(Message.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			// Retrieve user from DB using PK
			System.out.println("\nRetrieving userId from user: " + username);

			Query query = session.createQuery("from User u where (u.username = :username)");
			query.setParameter("username", username);
			
			User temp = (User) query.getSingleResult();
			User user = new User();
			user.setUserId(temp.getUserId());
			user.setUsername(temp.getUsername());
			
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		} finally {
			factory.close();
		}

	}

	public User getUserByCredentials(String username, String password) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Game.class)
				.addAnnotatedClass(Message.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			// Retrieve user from DB using PK
			System.out.println("\nRetrieving user with username: " + username + " and password: " + password);

			// Create the HQL query
			Query query = session.createQuery("from User u where (u.username = :username) AND (u.password=:password)");

			// Set the parameters for the query inputs, like a prepared statement
			query.setParameter("username", username);
			query.setParameter("password", password);

			// Retrieve the results into a list of users
			User user = (User) query.getSingleResult();
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;

		} finally {
			factory.close();
		}

	}

	public String recoveryQuestion(String username) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Game.class)
				.addAnnotatedClass(Message.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			Query query = session.createQuery("from User u where (u.username = :username)");
			query.setParameter("username", username);

			User user = (User) query.getSingleResult();
			return user.getSecurityquestion();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		} finally {
			factory.close();
		}

	}

	public String recoverPassword(String username, String securityAnswer) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Game.class)
				.addAnnotatedClass(Message.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			Query query = session
					.createQuery("from User u where (u.username = :username) AND (u.securityanswer = :securityanswer)");
			query.setParameter("username", username);
			query.setParameter("securityanswer", securityAnswer);

			User user = (User) query.getSingleResult();
			return user.getPassword();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction();
			return null;
		} finally {
			factory.close();
		}
	}
	

	public User addUser(String username, String password, String email, String recovery, String answer) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Game.class)
				.addAnnotatedClass(Message.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			System.out.println("Creating new user with username: " + username + ", password: " + password
					+ ", and email: " + email);

			User user = new User(username, password, email, recovery, answer);

			session.save(user);
			session.getTransaction().commit();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		} finally {
			factory.close();
		}

	}

	public User updateUser(User user) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Game.class)
				.addAnnotatedClass(Message.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			User oldUser = session.get(User.class, user.getUserId());
			if (oldUser == null)
				return null;

			user.setEmail(oldUser.getEmail());
			user.setUsername(oldUser.getUsername());
			session.merge(user);
			session.getTransaction().commit();
			return user;
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
			
		} finally {
			factory.close();
		}
	}

	// Make this boolean and base it around a successful or unsuccessful user
	// removal
	public void deleteUser(int id) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Game.class)
				.addAnnotatedClass(Message.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			Query query = session.createQuery("delete from User u where u.id = :id");
			query.setParameter("id", id);
			query.executeUpdate();

			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			factory.close();
		}
	}

}
