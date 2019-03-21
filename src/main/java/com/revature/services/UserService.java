package com.revature.services;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.revature.models.User;

public class UserService {

	public UserService() {
		super();
	}

	public List<User> getAll() {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		session.beginTransaction();

		System.out.println("\nRetrieving all users");

		List<User> users = session.createQuery("from User").getResultList();
		factory.close();
		return users;
	}

	public User getUserById(int id) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		session.beginTransaction();

		// Retrieve user from DB using PK
		System.out.println("\nRetrieving user with id: " + id);

		// session.load lazily fetches, returns a proxy
		User myUser = session.get(User.class, id);
		factory.close();
		return myUser;
	}

	public List<User> getUserByCredentials(String username, String password) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		session.beginTransaction();

		// Retrieve user from DB using PK
		System.out.println("\nRetrieving user with username: " + username + " and password: " + password);

		// Create the HQL query
		Query query = session.createQuery("from User u where (u.username = :username) AND (u.password=:password)");

		// Set the parameters for the query inputs, like a prepared statement
		query.setParameter("username", username);
		query.setParameter("password", password);

		// Retrieve the results into a list of users
		List<User> users = query.getResultList();
		factory.close();
		return users;
	}

	public String recoveryQuestion(String username) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		Query query = session.createQuery("from User u where (u.username = :username)");
		query.setParameter("username", username);
		
		User user = (User) query.getSingleResult();
		
		factory.close();
		return user.getSecurityquestion();
	}

	public String recoverPassword(String username, String securityAnswer) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		session.beginTransaction();

		Query query = session.createQuery("from User u where (u.username = :username) AND (u.securityanswer = :securityanswer)");
		query.setParameter("username", username);
		query.setParameter("securityanswer", securityAnswer);

		User user = (User) query.getSingleResult();
		factory.close();
		
		return user.getPassword();
	}

	public User addUser(String username, String password, String email) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		session.beginTransaction();

		System.out.println(
				"Creating new user with username: " + username + ", password: " + password + ", and email: " + email);

		User user = new User(username, password, email);

		session.save(user);
		session.getTransaction().commit();
		factory.close();
		return user;
	}

	public User updateUser(User user) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		session.beginTransaction();

		User oldUser = session.get(User.class, user.getUserId());
		if (oldUser == null)
			return null;
		try {
			oldUser = user;
			session.merge(oldUser);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

		return oldUser;
	}

	// Make this boolean and base it around a successful or unsuccessful user
	// removal
	public void deleteUser(int id) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		session.beginTransaction();

		Query query = session.createQuery("delete from User u where u.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();

		session.getTransaction().commit();
		factory.close();
	}

}
