package com.niitbejai.onlinecollaboration.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niitbejai.onlinecollaboration.dao.FriendListDAO;
import com.niitbejai.onlinecollaboration.dao.UserDAO;
import com.niitbejai.onlinecollaboration.dto.User_Detail;
import com.niitbejai.onlinecollaboration.model.DomainResponse;

@RestController
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private FriendListDAO friendlistDAO;	
	
	@RequestMapping(value = "/{id}")
	public ResponseEntity<User_Detail> get(@PathVariable int id) {
		
		return new ResponseEntity<User_Detail>(userDAO.get(id), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/all")
	public ResponseEntity<List<User_Detail>> getallusers() {
		
		return new ResponseEntity<List<User_Detail>>(userDAO.list(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/allbutme")
	public ResponseEntity<List<User_Detail>> getallusersexceptme(@RequestBody User_Detail user) 
	{
		// get all users in the database
		List<User_Detail> allusers = userDAO.list();
		
		// get all the friends of the current user 
		List<Long> allfriends =  friendlistDAO.getAllFriendsId(user.getUserid());
		
		// get accept pending for this user (initiator) and show the same as "Added (Accept Pending)"
		List<Long> acceptpending = friendlistDAO.getAcceptPendingList(user.getUserid());
		System.out.println("Accept Pending: " + acceptpending.size());
		for(Iterator<Long> it = acceptpending.iterator(); it.hasNext();)
		{
			Long tmpuserid = it.next();
			System.out.println(tmpuserid);
		}
		
		// get request pending for this user for friend requests initatied by other users.
		List<Long> requestpending = friendlistDAO.getFriendRequestPendingList(user.getUserid());
		System.out.println("Request Pending: " + requestpending.size());
		
		for(Iterator<Long> it = requestpending.iterator(); it.hasNext();)
		{
			Long tmpuserid = it.next();
			System.out.println(tmpuserid);
		}
		
		
		for(Iterator<User_Detail> it = allusers.iterator(); it.hasNext();)
		{
			User_Detail currusr = it.next();
			if(currusr.getEmail().equals(user.getEmail()))
			{
				// remove the user that has sent the request.
				it.remove();
			}
			else
			{
				// if this user is a frined of the "user" passed in the param then mark it as "Is a friend"
				// else as "Add as a friend".
				Long val = (Long) Long.valueOf(currusr.getUserid());
				if(allfriends.indexOf(val) == -1 )
				{
					currusr.setAddress("Add as friend");
				}
				else
				{
					currusr.setAddress("Is a friend");
				}
				

				// if currusr is added as a friend by the user (param) then show that currusr as 
				// "Added (Accept Pending)"
				if(acceptpending.indexOf(val) != -1)
				{
					currusr.setAddress("Added (Accept Pending)");
				}
				
				if(requestpending.indexOf(val) != -1)
				{
					currusr.setAddress("ACCEPT FRIEND REQUEST");
				}
			}
		}
		return new ResponseEntity<List<User_Detail>>(allusers, HttpStatus.OK);
	}	
	
	
	@PostMapping("/receive")
	public ResponseEntity<DomainResponse> post_userlogin(@RequestBody User_Detail user) {
		
		user.setActive(true);
		System.out.println(user);
		
		User_Detail duplicateUser = userDAO.getUserByEmail(user.getEmail());
		
		System.out.println("Duplicate user = " + duplicateUser);
		
		
		if( duplicateUser == null)
		{
			int nextId = userDAO.getLastInsertedID() + 1;
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
	
	@RequestMapping(value = "/allawaitingauth")
	public ResponseEntity<List<User_Detail>> getllAllUsersAwaitingAuth() 
	{
		System.out.println("Inside /user/allawaitingauth");
		return new ResponseEntity<List<User_Detail>>(userDAO.allUsersAwaitingAuth(), HttpStatus.OK);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<User_Detail> authenticateuser(@RequestBody User_Detail user) 
	{
		
		System.out.println("Authenticate user: " + user);
		user.setAuthenticated(true);
		userDAO.update(user);
		
		return new ResponseEntity<User_Detail> (user, HttpStatus.OK);
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
