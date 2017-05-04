package com.niitbejai.onlinecollaboration.dao;

import com.niitbejai.onlinecollaboration.dto.Chat;

public interface ChatDAO 
{
	public String getChatFilename(int senderUserid, int receiverUserid);
	
	public boolean add(Chat chat);
	public boolean delete(Chat chat);
	public boolean update(Chat chat);
	
	public boolean WriteChat(Chat chat);
	public String ReadChat(Chat chat);
}
