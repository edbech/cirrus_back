package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Game;
import com.revature.models.Message;
import com.revature.services.MessageService;


@RestController
@RequestMapping("/messages")
public class MessageController {

	private MessageService service;
	
	@Autowired
	public MessageController(MessageService messageService) {
		service = messageService;
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Message> getAllMessages(){
		return service.getAll();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Message getMessageById(@PathVariable int id) {
		return service.getById(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Message addMessage(@RequestBody Message message){
		int receiver = message.getReceiver();
		int sender = message.getSender();
		String content = message.getMessage();
		
		return service.createMessage(receiver, sender, content);
	}
	
}
