package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Game;
import com.revature.services.GameService;

@RestController
@RequestMapping("/games")
public class GameController {

	private GameService service;
	
	@Autowired
	public GameController(GameService gameService) {
		service = gameService;
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Game> getAllGames(){
		return service.getAll();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Game getGameById(@PathVariable int id) {
		return service.getGameById(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Game addGame(@RequestBody Game game){
		int playerX = game.getPlayerX();
		int playerO = game.getPlayerO();
		int isPublic = game.isPublic();
		
		return service.createGame(playerX, playerO, isPublic);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Game updateGame(@RequestBody Game game){
		
		return service.updateGame(game);

	}
	
	
}
