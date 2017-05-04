package com.niitbejai.onlinecollaboration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niitbejai.onlinecollaboration.dao.BlogPermissionsDAO;
import com.niitbejai.onlinecollaboration.dao.UserBlogDAO;
import com.niitbejai.onlinecollaboration.dto.UserBlog;
import com.niitbejai.onlinecollaboration.dto.User_Detail;
import com.niitbejai.onlinecollaboration.model.DomainResponse;

@RestController
@RequestMapping("/blog")
public class BlogController 
{
	@Autowired
	private BlogPermissionsDAO blogpermissionsDAO;
		
	@Autowired
	private UserBlogDAO userblogDAO;
	
	
	@RequestMapping(value = "/{id}")
	public ResponseEntity<User_Detail> get(@PathVariable int id) 
	{
		
		//return new ResponseEntity<User_Detail>(userDAO.get(id), HttpStatus.OK);
		
		return null;
		
	}
	
	@RequestMapping(value = "/all")
	public ResponseEntity<List<UserBlog>> getallusers() {
		
		return new ResponseEntity<List<UserBlog>>(userblogDAO.listAllBlogs(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/add")
	public ResponseEntity<DomainResponse> addBlog(@RequestBody UserBlog userblog) 	
	{
		System.out.println("Inside /blogadd -> " + userblog);
		int blogid = userblogDAO.addblog(userblog);
		
		System.out.println("After adding blog -> " + userblog);
		
		blogpermissionsDAO.addpermissions(blogid, userblog.getUserid(), userblog.getUserid(), true);
		
		return new ResponseEntity<DomainResponse>(new DomainResponse("New Blog added", 200), HttpStatus.OK);
		
	}	
}
