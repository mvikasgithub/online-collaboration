package com.niitbejai.onlinecollaboration.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niitbejai.onlinecollaboration.dao.FriendListDAO;

public class FriendListTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static FriendListDAO friendListDAO;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niitbejai.onlinecollaboration");
		context.refresh();
		
		friendListDAO = (FriendListDAO) context.getBean("friendListDAO");
		  
	}		
	
//	@Test
//	public void testAddFriend()
//	{
//		assertEquals("Successfully added Friend !", true, friendListDAO.addfriend(2, 3));
//	}
	
	
//	@Test
//	public void testUpdateFriendStatus()
//	{
//		assertEquals("Successfully updated Friend !", true, friendListDAO.updateFriendStatus(2, 3, "ACCEPTED"));
//	}
	
}
