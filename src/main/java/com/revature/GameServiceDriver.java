package com.revature;

import com.revature.models.Game;
import com.revature.services.GameService;

public class GameServiceDriver {

	public static void main(String[] args) {
		GameService gameServe = new GameService();
		Game game = gameServe.getGameById(21);
		
		game.setGamestate("13579012");
		gameServe.deleteGame(1);
	}
	
}
