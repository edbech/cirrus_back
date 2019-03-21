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

import com.revature.models.User;
import com.revature.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

private UserService service;
	
	@Autowired
	public UserController(UserService userService) {
		service = userService;
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers(){
		return service.getAll();
	}
	
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public User getCardById(@PathVariable int id) {
		return service.getUserById(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public User addUser(@RequestBody User user){
		String username = user.getUsername();
		String password = user.getPassword();
		String email = user.getEmail();
		
		return service.addUser(username, password, email);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@RequestBody User user){
		
		return service.updateUser(user);

	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value="/auth", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public User authUser(@RequestBody User user){
		String username = user.getUsername();
		String password = user.getPassword();
		
		return service.getUserByCredentials(username, password);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value="/recoveryquestion", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public String recoveryQuestion(@RequestBody User user){
		String username = user.getUsername();
		
		return service.recoveryQuestion(username);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value="/recoveryanswer", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public String recoveryAnswer(@RequestBody User user){
		String username = user.getUsername();
		String answer = user.getSecurityanswer();
		
		return service.recoverPassword(username, answer);
	}
	
}
