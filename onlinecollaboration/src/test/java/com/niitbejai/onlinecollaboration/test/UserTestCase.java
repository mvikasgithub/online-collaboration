package com.niitbejai.onlinecollaboration.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niitbejai.onlinecollaboration.dao.FriendListDAO;
import com.niitbejai.onlinecollaboration.dao.UserDAO;
import com.niitbejai.onlinecollaboration.dto.User_Detail;


public class UserTestCase 
{
	private static AnnotationConfigApplicationContext context;
	
	private static UserDAO userDAO;
	private User_Detail user;
	private static FriendListDAO friendListDAO;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niitbejai.onlinecollaboration");
		context.refresh();
		
		userDAO = (UserDAO) context.getBean("userDAO");
		friendListDAO = (FriendListDAO) context.getBean("friendListDAO");
		  
	}	
	
/*	@Test
	public void testAddUser()
	{
		user = new User_Detail();
		
//		user.setUserid(1);
//		user.setFname("Sachin");
//		user.setSname("Tendulkar");
//		user.setEmail("sachin.t@gmail.com");
//		user.setPassword("123");
//		user.setAddress("Bandra");
//		user.setCity("Mumbai");
//		user.setZip("400001");
//		user.setPhoneno("123456789");
//		user.setRole("STUDENT");
//		user.setActive(true);
//		user.setState("Maharashtra");
		
		user.setUserid(2);
		user.setFname("Rahul");
		user.setSname("Dravid");
		user.setEmail("rahul.d@gmail.com");
		user.setPassword("123");
		user.setAddress("Indiranagar");
		user.setCity("Bangalore");
		user.setZip("560001");
		user.setPhoneno("123456789");
		user.setRole("STUDENT");
		user.setActive(true);
		user.setState("Karnataka");
		
		
		
		
		assertEquals("Successfully fetched a single category from the table !", "true", userDAO.add(user));
	}
*/
	
/*	@Test
	public void testDeleteUser()
	{
		
		assertEquals("Successfully fetched a single category from the table !", true, userDAO.delete(2));
	}
*/
/*	
	@Test
	public void testgetUser()
	{
		
		assertEquals("Successfully fetched a single category from the table !", "Sachin", userDAO.get(1).getFname());
	}
	*/
	
	@Test
	public void testAddFriend()
	{
		assertEquals("Successfully added Friend !", true, friendListDAO.addfriend(2, 3));
	}
	
	
}
