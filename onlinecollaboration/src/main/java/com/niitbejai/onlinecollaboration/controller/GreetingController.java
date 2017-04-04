package com.niitbejai.onlinecollaboration.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niitbejai.onlinecollaboration.model.DomainResponse;

@RestController
public class GreetingController 
{
	@RequestMapping(value="/greeting")
	public ResponseEntity<DomainResponse> greeting()
	{
		System.out.println("Inside /greeting");
		return new ResponseEntity<DomainResponse>(new DomainResponse("Welcome from Spring RestController", 200), HttpStatus.OK);
	}

}
