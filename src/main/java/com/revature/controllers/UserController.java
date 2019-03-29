package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Principal;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.JwtConfig;
import com.revature.util.JwtGenerator;

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
	
	@GetMapping(value ="/scores", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getHighScores(){
		return service.getHighScores();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public User getUserById(@PathVariable int id) {
		return service.getUserById(id);
	}
	
	@PostMapping(value="/id", produces=MediaType.APPLICATION_JSON_VALUE)
	public User getIdByUsername(@RequestBody User user) {
		String username = user.getUsername();
		return service.getIdByUsername(username);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addUser(@RequestBody User user){
		String username = user.getUsername();
		String password = user.getPassword();
		String email = user.getEmail();
		String recovery  = user.getSecurityquestion();
		String answer = user.getSecurityanswer();
		
		User createdUser = service.addUser(username, password, email, recovery, answer);
		Principal principal = new Principal(createdUser.getUserId(), createdUser.getUsername(), createdUser.getPassword());
		
		HttpHeaders response = new HttpHeaders();
		response.set(JwtConfig.HEADER, JwtConfig.PREFIX + JwtGenerator.createJwt(createdUser));
		response.set("Principal", principal.toString());
		
		return new ResponseEntity(createdUser, response, HttpStatus.CREATED);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateUser(@RequestBody User user){
		User updatedUser = service.updateUser(user);
		Principal principal = new Principal(updatedUser.getUserId(), updatedUser.getUsername(), updatedUser.getPassword());
		
		HttpHeaders response = new HttpHeaders();
		response.set(JwtConfig.HEADER, JwtConfig.PREFIX + JwtGenerator.createJwt(updatedUser));
		
		return new ResponseEntity(updatedUser, response, HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value="/auth", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity authUser(@RequestBody User user){
		String username = user.getUsername();
		String password = user.getPassword();
		
		User authUser = service.getUserByCredentials(username, password);
		Principal principal = new Principal(authUser.getUserId(), authUser.getUsername(), authUser.getPassword());
		System.out.println(authUser);
		HttpHeaders response = new HttpHeaders();
		response.set(JwtConfig.HEADER, JwtConfig.PREFIX + JwtGenerator.createJwt(authUser));
		
		return new ResponseEntity(principal, response, HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value="/recoveryquestion", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public User recoveryQuestion(@RequestBody User user){
		return service.recoveryQuestion(user);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value="/recoveryanswer", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public User recoveryAnswer(@RequestBody User user){
		return service.recoverPassword(user);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value="/delete", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void delete(@RequestBody User user){
		
		int userid = user.getUserId();
		service.deleteUser(userid);
		
	}
	
}
