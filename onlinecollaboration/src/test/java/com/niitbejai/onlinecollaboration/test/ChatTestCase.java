package com.niitbejai.onlinecollaboration.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niitbejai.onlinecollaboration.dao.ChatDAO;
import com.niitbejai.onlinecollaboration.dto.Chat;

public class ChatTestCase 
{
	private static AnnotationConfigApplicationContext context;
	
	private static ChatDAO chatDAO;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niitbejai.onlinecollaboration");
		context.refresh();
		
		chatDAO = (ChatDAO) context.getBean("chatDAO");
		  
	}		
	
	@Test
	public void testWriteChat()
	{
		Chat chat = new Chat();
		chat.setSenderuserid(3);
		chat.setSenderfname("Rahul");
		chat.setSendersname("Dravid");
		chat.setReceiveruserid(2);
		chat.setReceiverfname("Sachin");
		chat.setReceiversname("Tendulkar");
		chat.setChatData("");
		
		
		assertEquals("Successfully Wrote chat Msg to file!", true, chatDAO.ReadChat(chat));
	}
}
