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
	public ResponseEntity<DomainResponse> post(@RequestBody User_Detail user) {
		
		System.out.println(user);
		
		
		return new ResponseEntity<DomainResponse> (new DomainResponse("User receieved",200), HttpStatus.OK);
	}
	
}
