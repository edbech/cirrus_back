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
import com.revature.util.HibernateUtil;

@Service
public class UserService {

	public UserService() {
		super();
	}


	public List<User> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			System.out.println("\nRetrieving all users");

			@SuppressWarnings("unchecked")
			List<User> users = session.createQuery("from User").getResultList();
			HibernateUtil.getSessionFactory().close();
			return users;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			HibernateUtil.getSessionFactory().close();
			return null;
		} 

	}
	
	public List<User> getHighScores() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			System.out.println("\nRetrieving all users");

			@SuppressWarnings("unchecked")
			List<User> users = session.createQuery("SELECT username, score FROM User u WHERE u.score > 0 ORDER BY u.score desc").getResultList();
			return users;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			HibernateUtil.getSessionFactory().close();
			return null;

		} 

	}

	public User getUserById(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			// Retrieve user from DB using PK
			System.out.println("\nRetrieving user with id: " + id);

			// session.load lazily fetches, returns a proxy
			User myUser = session.get(User.class, id);
			HibernateUtil.getSessionFactory().close();
			return myUser;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			HibernateUtil.getSessionFactory().close();
			return null;
		} 

	}
	
	public User getIdByUsername(String username) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

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
			HibernateUtil.getSessionFactory().close();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			HibernateUtil.getSessionFactory().close();
			return null;
		} 

	}

	public User getUserByCredentials(String username, String password) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

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
			HibernateUtil.getSessionFactory().close();
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			HibernateUtil.getSessionFactory().close();
			return null;

		} 

	}

	public User recoveryQuestion(User recoverQuestion) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			String username = recoverQuestion.getUsername();
			Query query = session.createQuery("from User u where (u.username = :username)");
			query.setParameter("username", username);

			User temp = (User) query.getSingleResult();
			User user = new User();
			user.setUsername(temp.getUsername());
			user.setUserId(temp.getUserId());
			user.setSecurityquestion(temp.getSecurityquestion());
			HibernateUtil.getSessionFactory().close();
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			HibernateUtil.getSessionFactory().close();
			return null;
		} 

	}

	public User recoverPassword(User recoverAnswer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			String username = recoverAnswer.getUsername();
			String securityAnswer = recoverAnswer.getSecurityanswer();
			
			Query query = session
					.createQuery("from User u where (u.username = :username) AND (u.securityanswer = :securityanswer)");
			query.setParameter("username", username);
			query.setParameter("securityanswer", securityAnswer);

			User temp = (User) query.getSingleResult();
			User user = new User();
			user.setUserId(temp.getUserId());
			user.setSecurityquestion(temp.getSecurityquestion());
			user.setSecurityanswer(temp.getSecurityanswer());
			user.setPassword(temp.getPassword());
			user.setUsername(temp.getUsername());
			
			HibernateUtil.getSessionFactory().close();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction();
			HibernateUtil.getSessionFactory().close();
			return null;
		} 
	}
	

	public User addUser(String username, String password, String email, String recovery, String answer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			System.out.println("Creating new user with username: " + username + ", password: " + password
					+ ", and email: " + email);

			User user = new User(username, password, email, recovery, answer);

			session.save(user);
			session.getTransaction().commit();
			HibernateUtil.getSessionFactory().close();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			HibernateUtil.getSessionFactory().close();
			return null;
		} 

	}

	public User updateUser(User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {

			session.beginTransaction();

			User oldUser = session.get(User.class, user.getUserId());
			if (oldUser == null)
				return null;

			user.setEmail(oldUser.getEmail());
			user.setUsername(oldUser.getUsername());
			session.merge(user);
			session.getTransaction().commit();
			HibernateUtil.getSessionFactory().close();
			return user;
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			HibernateUtil.getSessionFactory().close();
			return null;
			
		} 
	}

	// Make this boolean and base it around a successful or unsuccessful user
	// removal
	public void deleteUser(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			Query query = session.createQuery("delete from User u where u.id = :id");
			query.setParameter("id", id);
			query.executeUpdate();

			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

}
