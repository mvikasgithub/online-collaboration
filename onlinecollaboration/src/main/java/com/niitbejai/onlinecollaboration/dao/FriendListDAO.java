package com.niitbejai.onlinecollaboration.dao;

import java.util.List;

import com.niitbejai.onlinecollaboration.dto.Friend_List;

public interface FriendListDAO 
{
	List<Long> getAllFriendsId(int userid);
	
	boolean updateFriendStatus(int userid, int friendid, String status);
	boolean deletefriend(int userid, int friendid);
	boolean addfriend(int userid, int friendid);
	
	boolean add(Friend_List friend);
	boolean update(Friend_List friend);
	boolean delete(Friend_List friend);	
	
}
