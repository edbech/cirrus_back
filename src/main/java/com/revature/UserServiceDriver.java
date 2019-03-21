package com.revature;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.services.UserService;

public class UserServiceDriver {

	public static void main(String[] args) {
		UserService userServe = new UserService();
		List<User> newUser = new ArrayList<>();
		
		
		System.out.println(userServe.recoverPassword("Billiam", "Red"));
		
		
	}
	
	
}
