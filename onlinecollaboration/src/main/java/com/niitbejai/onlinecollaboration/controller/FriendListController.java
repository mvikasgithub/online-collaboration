package com.niitbejai.onlinecollaboration.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niitbejai.onlinecollaboration.dao.FriendListDAO;
import com.niitbejai.onlinecollaboration.dao.UserDAO;
import com.niitbejai.onlinecollaboration.dto.User_Detail;
import com.niitbejai.onlinecollaboration.model.DomainResponse;

@RestController
@RequestMapping("/friend")
public class FriendListController 
{
	@Autowired
	private FriendListDAO friendlistDAO;
	
	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/all")
	public ResponseEntity<List<User_Detail>> getallfriends(@RequestBody User_Detail user) 
	{
		List<Long> friendids = friendlistDAO.getAllFriendsId(user.getUserid());
		
		List<User_Detail> allusers = new ArrayList<>();
		
		for(Iterator<Long> it = friendids.iterator(); it.hasNext(); )
		{
			int id = it.next().intValue();
			allusers.add(userDAO.get(id));
		}
		
		return new ResponseEntity<List<User_Detail>>(allusers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addfriend")
	public ResponseEntity<DomainResponse> addfriendrequest(@RequestBody User_Detail user) 
	{
		
		friendlistDAO.addfriend(Integer.parseInt(user.getPhoneno()), user.getUserid(), "ADDED");
		
		System.out.println("Friend request made from :" + user.getUserid() + " to :" + user.getPhoneno());
		
		return new ResponseEntity<DomainResponse>(new DomainResponse("Friend request added", 200), HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/acceptfriend")
	public ResponseEntity<DomainResponse> acceptfriendrequest(@RequestBody User_Detail user) 
	{
		System.out.println("Inside Accept Friend /acceptfriend");
		friendlistDAO.updateFriendStatus(user.getUserid(), Integer.parseInt(user.getPhoneno()), "ACCEPTED");
		
		System.out.println("Friend accepted by :" + user.getPhoneno());
		System.out.println("Friend request made by: " + user.getUserid());
		
		return new ResponseEntity<DomainResponse>(new DomainResponse("Friend request added", 200), HttpStatus.OK);
	}		
	
}
