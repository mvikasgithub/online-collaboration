package com.niitbejai.onlinecollaboration.dao;

import java.util.List;

import com.niitbejai.onlinecollaboration.dto.User_Detail;

public interface UserDAO 
{
	User_Detail get(int id);
	List<User_Detail> list();
	User_Detail getUserByEmail(String email); // username will be email id
	
	//Methods for JUnit
	String add(User_Detail user);
	String update(User_Detail user);
	boolean delete(int id);	
	int getLastInsertedID();
}
