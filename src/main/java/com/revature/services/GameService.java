package com.revature.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.revature.models.Game;
import com.revature.util.HibernateUtil;

@Service
public class GameService {

	public static void main(String[] args) {
		GameService gs = new GameService();
		System.out.println(gs.getResult("000000000")); // return 0
		System.out.println(gs.getResult("135240000")); // return 1
		System.out.println(gs.getResult("130246050")); // return 2
		System.out.println(gs.getResult("135246000")); // return 4
		System.out.println(gs.getResult("169543287")); // return 4
		System.out.println(gs.getResult("102340500")); // return 1
		System.out.println(gs.getResult("102340650")); // return 2
		System.out.println(gs.getResult("102430650")); // return 0

		System.out.println("000000000");
		System.out.println(gs.aiMove("000000000")); // return 0
		System.out.println("135240000");
		System.out.println(gs.aiMove("135240000")); // return 1
		System.out.println("130246050");
		System.out.println(gs.aiMove("130246050")); // return 2
		System.out.println("135246000");
		System.out.println(gs.aiMove("135246000")); // return 4
		System.out.println("169543287");
		System.out.println(gs.aiMove("169543287")); // return 4
		System.out.println("102340500");
		System.out.println(gs.aiMove("102340500")); // return 1
		System.out.println("102340650");
		System.out.println(gs.aiMove("102340650")); // return 2
		System.out.println("102430650");
		System.out.println(gs.aiMove("102430650")); // return 0
	}

	public GameService() {
		super();
	}

	public List<Game> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			System.out.println("\nRetrieving all games");

			List<Game> games = session.createQuery("from Game").getResultList();
			session.close();
			return games;

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
			return null;
		} 

	}

	public Game createGame(String playerX, String playerO, int isPublic) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			System.out.println("\nCreating new game");
			Game game;
			Random rand = new Random();
			if(rand.nextBoolean()) {
				game = new Game(playerX, playerO, isPublic);
			} else {
				game = new Game(playerO, playerX, isPublic);
			}
			game.setGamestate("000000000");
			if(game.getPlayerX() == "IAmABot") {
				game.setGamestate(GameService.aiMove(game.getGamestate()));
			}
			game.setResult("INPROGRESS");
			session.save(game);
			session.getTransaction().commit();
			session.close();
			return game;

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
			return null;
		} 

	}

	public Game getGameById(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			System.out.println("\nRetrieving game with id: " + id);

			Game game = session.get(Game.class, id);
			session.close();
			return game;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
			return null;
		} 

	}

	public Game updateGame(Game game) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			Game oldGame = session.get(Game.class, game.getGameId());

			if (oldGame == null) {
				oldGame = game;
				return null;
			}
			oldGame.setGamestate(game.getGamestate());
			if(oldGame.getPlayerO() == "IAmABot"||oldGame.getPlayerX() == "IAmABot") {
				oldGame.setGamestate(GameService.aiMove(oldGame.getGamestate()));
			}
			int result = GameService.getResult(oldGame.getGamestate());
			switch(result) {
				case 0:
					break;
				case 1:
					oldGame.setResult(oldGame.getPlayerX());
					break;
				case 2:
					oldGame.setResult(oldGame.getPlayerO());
					break;
				case 3:
					oldGame.setResult("DRAW");
					break;
				case 4:
					oldGame.setResult("INVALID");
					break;
			}
			session.merge(oldGame);
			session.getTransaction().commit();
			session.close();
			return oldGame;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
			return null;
		} 

	}

	public void deleteGame(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			Query query = session.createQuery("delete from Game g where g.id = :id");
			query.setParameter("id", id);
			query.executeUpdate();

			session.getTransaction().commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	private static String aiMove(String gameState) {
		String[] strs = gameState.split("");

		Integer[] state = new Integer[strs.length];
		for (int i = 0; i < state.length; i++) {
			state[i] = Integer.parseInt(strs[i]);
		}
		int max = Collections.max(Arrays.asList(state));
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < state.length; i++) {
			if (state[i] == 0) {
				list.add(i);
			}
		}
		if(list.size() > 0) {
		Collections.shuffle(list, new Random());
		state[list.get(0)] = max +1;
		}
		for (int i = 0; i < state.length; i++) {
			strs[i] = String.valueOf(state[i]);
		}

		return String.join("", strs);

	}

	private static int getResult(String gameState) { // returns 0 for in progress, 1 for player x win, 2 for player o win, 3
												// for draw, 4 for invalid game
		String[] strs = gameState.split("");

		Integer[] state = new Integer[strs.length];
		for (int i = 0; i < state.length; i++) {
			state[i] = Integer.parseInt(strs[i]);
		}
		int count = 0;
		int max = Collections.max(Arrays.asList(state));

		if (max == 0) {
			return 0;
		}

		// rows
		if (getTriadResult(state[0], state[1], state[2])) {
			count++;
		}
		if (getTriadResult(state[3], state[4], state[5])) {
			count++;
		}
		if (getTriadResult(state[6], state[7], state[8])) {
			count++;
		}

		// collums
		if (getTriadResult(state[0], state[3], state[6])) {
			count++;
		}
		if (getTriadResult(state[1], state[4], state[7])) {
			count++;
		}
		if (getTriadResult(state[2], state[5], state[8])) {
			count++;
		}

		// diagonals
		if (getTriadResult(state[0], state[4], state[8])) {
			count++;
		}
		if (getTriadResult(state[2], state[4], state[6])) {
			count++;
		}
		if(count == 0) {
			if (max == 9) {
				return 3;
			}
			return 0;
		}else if (max % 2 == 0) {
			return 2;
		} else {
			return 1;
		}
	}

	private static boolean getTriadResult(int a, int b, int c) {// true if victorious, false if not
		if (a == 0 || b == 0 || c == 0) {
			return false;
		}
		a = a % 2;
		b = b % 2;
		c = c % 2;

		if (a == b && b == c) {
			return true;
		}

		return false;
	}
}
