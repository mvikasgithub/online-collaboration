package com.niitbejai.onlinecollaboration.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niitbejai.onlinecollaboration.dao.UserBlogDAO;


public class UserBlogTestCase 
{
	private static AnnotationConfigApplicationContext context;
	
	private static UserBlogDAO userblogDAO;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niitbejai.onlinecollaboration");
		context.refresh();
		
		userblogDAO = (UserBlogDAO) context.getBean("userblogDAO");
		  
	}		
	
//	@Test
//	public void testAddBlog()
//	{
//		UserBlog userblog = new UserBlog();
//		
//		userblog.setAuthorfname("Rahul");
//		userblog.setAuthorsname("Dravid");
//		userblog.setTitle("This is the title of Rahul's second blog");
//		userblog.setFullblog("This is the body of Rahul's second blog");
//		userblog.setUserid(3);
//		
//		assertEquals("Successfully added Blog !", true, userblogDAO.addblog(userblog));
//	}
	
	
//	@Test
//	public void testDeleteBlog()
//	{
//		assertEquals("Successfully deleted Blog !", true, userblogDAO.deleteById(5));
//	}
	
	@Test
	public void testListBlog()
	{
		assertEquals("Number of Blogs for the user matches !", 3, userblogDAO.listAllBlogs().size());
	}

}
