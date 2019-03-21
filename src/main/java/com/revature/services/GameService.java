package com.revature.services;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import com.revature.models.Game;
import com.revature.models.User;

@Service
public class GameService {
	
	public GameService() {
		super();
	}
	
	public List<Game> getAll(){
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Game.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		session.beginTransaction();
		
		System.out.println("\nRetrieving all games");

		List<Game> games = session.createQuery("from Game").getResultList();
		factory.close();
		
		return games;
	}
	
	public Game createGame(int playerX, int playerO, int isPublic) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Game.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		session.beginTransaction();
		
		System.out.println("\nCreating new game");
		
		Game game = new Game(playerX, playerO, isPublic);
		
		session.save(game);
		session.getTransaction().commit();
		factory.close();
		return game;
	}

	public Game getGameById(int id) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Game.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		session.beginTransaction();
		
		System.out.println("\nRetrieving game with id: " + id);
		
		Game game = session.get(Game.class, id);
		
		factory.close();
		return game;
	}

	public Game updateGame(Game game) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Game.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		session.beginTransaction();
		

		Game oldGame = session.get(Game.class, game.getGameId());
		if (oldGame == null) return null;
		
		try {
			oldGame = game;
			session.merge(oldGame);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

		return oldGame;
	}
	
	public void deleteGame(int id) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Game.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		session.beginTransaction();
		
		Query query = session.createQuery("delete from Game g where g.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();

		session.getTransaction().commit();
		factory.close();
	}

}
