package com.niitbejai.onlinecollaboration.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.junit.Assert.assertEquals;

import com.niitbejai.onlinecollaboration.dao.BlogPermissionsDAO;

public class BlogPermissionsTestCase 
{
	private static AnnotationConfigApplicationContext context;
	
	private static BlogPermissionsDAO blogpermissionsDAO;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niitbejai.onlinecollaboration");
		context.refresh();
		
		blogpermissionsDAO = (BlogPermissionsDAO) context.getBean("blogpermissionsDAO");
		  
	}		
	
	@Test
	public void testAddBlog()
	{
		
		assertEquals("Successfully added Blog Permission!", true, blogpermissionsDAO.addpermissions(4, 3, 3, true));
	}

//	@Test
//	public void testDeleteBlog()
//	{
//		
//		assertEquals("Successfully added Blog Permission!", true, blogpermissionsDAO.deletepermissions(4, 3, 3));
//	}

}
