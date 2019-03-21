package com.revature;

import com.revature.services.MessageService;

public class MessageServiceDriver {
	
	public static void main(String[] args) {
		MessageService messageServe = new MessageService();
		messageServe.deleteMessage(21);
		
		
		
		
	}
}
