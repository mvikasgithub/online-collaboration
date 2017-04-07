package com.niitbejai.onlinecollaboration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niitbejai.onlinecollaboration.dao.UserDAO;
import com.niitbejai.onlinecollaboration.dto.User_Detail;
import com.niitbejai.onlinecollaboration.model.DomainResponse;

@RestController
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value = "/{id}")
	public ResponseEntity<User_Detail> get(@PathVariable int id) {
		
		return new ResponseEntity<User_Detail>(userDAO.get(id), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/all")
	public ResponseEntity<List<User_Detail>> getallusers() {
		
		return new ResponseEntity<List<User_Detail>>(userDAO.list(), HttpStatus.OK);
		
	}	
	
	@PostMapping("/receive")
	public ResponseEntity<DomainResponse> post_userlogin(@RequestBody User_Detail user) {
		
		user.setActive(true);
		System.out.println(user);
		
		User_Detail duplicateUser = userDAO.getUserByEmail(user.getEmail());
		
		System.out.println("Duplicate user = " + duplicateUser);
		
		
		if( duplicateUser == null)
		{
			int nextId = userDAO.getLastIndertedID() + 1;
			user.setUserid(nextId);
			user.setAuthenticated(false);
			userDAO.add(user);
			System.out.println("User Added to Database successfully");
			return new ResponseEntity<DomainResponse> (new DomainResponse("User Added to DB",200), HttpStatus.OK);
		}
		else
		{
			System.out.println("Duplicate Email, so not adding to database");
			return new ResponseEntity<DomainResponse> (new DomainResponse("Duplicate Email",500), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<User_Detail> post(@RequestBody User_Detail user) {
		
		System.out.println(user);
		
		User_Detail existingUser = userDAO.getUserByEmail(user.getEmail());
		
		System.out.println("Existing user = " + existingUser);
		
		
		if( existingUser == null)
		{
			//means there is no user with that mail id, return appropriate error message
			System.out.println("Unknown user. Username does not exist");
			User_Detail erroruser = new User_Detail();
			erroruser.setFname("Username does not exist"); // piggy-backing on fname rather than creating a separate error string
			return new ResponseEntity<User_Detail> (erroruser, HttpStatus.BAD_REQUEST);
		}
		else
		{
			// user exists, now cross check the password
			if(existingUser.getPassword().equals(user.getPassword()))
			{
				System.out.println("Password matches. ");
				
				if(existingUser.isAuthenticated())
				{
					System.out.println("User successfully authenticated");
					return new ResponseEntity<User_Detail> (existingUser, HttpStatus.OK);
				}
				else
				{
					System.out.println("User not authenticated by Admin.");
					User_Detail erroruser = new User_Detail();
					erroruser.setFname("User Not Authenticated By Admin"); // piggy-backing on fname rather than creating a separate error string
					return new ResponseEntity<User_Detail> (erroruser, HttpStatus.BAD_REQUEST);					
				}
			}
			else
			{
				System.out.println("Wrong password, try again");
				User_Detail erroruser = new User_Detail();
				erroruser.setFname("Wrong Password. Try Again !"); // piggy-backing on fname rather than creating a separate error string
				return new ResponseEntity<User_Detail> (erroruser, HttpStatus.BAD_REQUEST);					
			}
		}
	}	
	
}
