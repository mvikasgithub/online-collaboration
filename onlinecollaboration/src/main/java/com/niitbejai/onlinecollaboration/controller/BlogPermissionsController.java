package com.niitbejai.onlinecollaboration.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niitbejai.onlinecollaboration.dao.BlogPermissionsDAO;
import com.niitbejai.onlinecollaboration.dao.UserDAO;
import com.niitbejai.onlinecollaboration.dto.BlogPermissions;
import com.niitbejai.onlinecollaboration.dto.User_Detail;
import com.niitbejai.onlinecollaboration.model.DomainResponse;

@RestController
@RequestMapping("/blogpermission")
public class BlogPermissionsController 
{
	
	@Autowired
	private BlogPermissionsDAO blogpermissionsDAO;
	
	@Autowired
	private UserDAO userDAO;	
	
	@PostMapping("/check")
	public ResponseEntity<DomainResponse> authenticateuser(@RequestBody BlogPermissions bp) 
	{
		System.out.println("Inside /blogpermission/check: " + bp);
		if(blogpermissionsDAO.isPermitted(bp.getBlogid(), bp.getOwnerid(), bp.getRequesterid()))
			return new ResponseEntity<DomainResponse>(new DomainResponse("Permission Available", 200), HttpStatus.OK);
		else
			return new ResponseEntity<DomainResponse>(new DomainResponse("Permission NOT Available", 500), HttpStatus.BAD_REQUEST);
	}	
	
	@PostMapping("/request")
	public ResponseEntity<DomainResponse> requestPermission(@RequestBody BlogPermissions bp) 
	{
		System.out.println("Inside /blogpermission/request: " + bp);
		if(blogpermissionsDAO.addpermissions(bp.getBlogid(), bp.getOwnerid(), bp.getRequesterid(), false))
			return new ResponseEntity<DomainResponse>(new DomainResponse("Permission Grant Request - Submitted", 200), HttpStatus.OK);
		else
			return new ResponseEntity<DomainResponse>(new DomainResponse("Error: Permission Grant Request", 500), HttpStatus.BAD_REQUEST);
	} 
	
	@PostMapping("/grant")
	public ResponseEntity<DomainResponse> grantPermission(@RequestBody BlogPermissions bp) 
	{
		System.out.println("Inside /blogpermission/grant: " + bp);
		if(blogpermissionsDAO.addpermissions(bp.getBlogid(), bp.getOwnerid(), bp.getRequesterid(), true))
			return new ResponseEntity<DomainResponse>(new DomainResponse("Permission Grant Request - Approved", 200), HttpStatus.OK);
		else
			return new ResponseEntity<DomainResponse>(new DomainResponse("Error: Permission Grant Request", 500), HttpStatus.BAD_REQUEST);
	}		
	
	@PostMapping("/reject")
	public ResponseEntity<DomainResponse> rejectPermission(@RequestBody BlogPermissions bp) 
	{
		System.out.println("Inside /blogpermission/reject: " + bp);
		if(blogpermissionsDAO.deletepermissions(bp.getBlogid(), bp.getOwnerid(), bp.getRequesterid()))
			return new ResponseEntity<DomainResponse>(new DomainResponse("Blog Permission Rejected", 200), HttpStatus.OK);
		else
			return new ResponseEntity<DomainResponse>(new DomainResponse("Error: Permission Reject Request", 500), HttpStatus.BAD_REQUEST);
	}		
	
	@PostMapping("/awaiting")
	public ResponseEntity<List<User_Detail>> awaitingPermission(@RequestBody int userid) 
	{
		System.out.println("Inside /blogpermission/awaiting: " + userid);
		// get a list of all the blogs owned by this user but have been marked as false (indicating awaiting permission)
		List<BlogPermissions> bps = blogpermissionsDAO.getPermissionsAwaitingList(userid, false);
		
		List<User_Detail> allusers = new ArrayList<>();
		
		for(Iterator<BlogPermissions> it = bps.iterator(); it.hasNext(); )
		{
			BlogPermissions bp = it.next(); 
			int id = bp.getRequesterid();
			System.out.println("Requester id: " + id);
			User_Detail user = new User_Detail();
			user = userDAO.get(id);
			id = bp.getBlogid();
			System.out.println("User obtained. Now getting blogid: " + id);
			user.setPhoneno(String.valueOf(id));
			user.setAddress(String.valueOf(bp.getBlogpermissionsid()));

			allusers.add(user);
			
		}
		
		return new ResponseEntity<List<User_Detail>>(allusers, HttpStatus.OK);		

	}
}
