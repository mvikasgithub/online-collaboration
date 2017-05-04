package com.niitbejai.onlinecollaboration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niitbejai.onlinecollaboration.dao.ChatDAO;
import com.niitbejai.onlinecollaboration.dto.Chat;
import com.niitbejai.onlinecollaboration.model.DomainResponse;

@RestController
@RequestMapping("/chat")
public class ChatController 
{
	@Autowired
	private ChatDAO chatDAO;
	
	
	@PostMapping("/send")
	public ResponseEntity<DomainResponse> sendChat(@RequestBody Chat chat) 
	{
		System.out.println("Inside /chat/send: " + chat);
		
		if(chatDAO.WriteChat(chat))
			return new ResponseEntity<DomainResponse>(new DomainResponse("Chat Updated Successfully", 200), HttpStatus.OK);
		else
			return new ResponseEntity<DomainResponse>(new DomainResponse("Error while updating chat", 500), HttpStatus.BAD_REQUEST);
	}	
	
	@PostMapping("/receive")
	public ResponseEntity<Chat> reeiveChat(@RequestBody Chat chat) 
	{
		System.out.println("Inside /chat/receive: " + chat);
		
		String strChatMsg = chatDAO.ReadChat(chat);
		chat.setChatData(strChatMsg);
		
		return new ResponseEntity<Chat>(chat, HttpStatus.OK);
		
	}	
	

}
