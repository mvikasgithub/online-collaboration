package com.niitbejai.onlinecollaboration.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niitbejai.onlinecollaboration.dao.FriendListDAO;
import com.niitbejai.onlinecollaboration.dto.Friend_List;

@Repository("friendListDAO")
@Transactional
public class FriendListDAOImpl implements FriendListDAO 
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Long> getAllFriendsId(int userid) 
	{
		String selectFriendId = "SELECT friendid FROM FRIEND_LIST WHERE userid = :userid AND status = 'ACCEPTED'";
		Query query = sessionFactory.getCurrentSession().createNativeQuery(selectFriendId).addScalar("friendid", StandardBasicTypes.LONG);
		query.setParameter("userid", userid);
		
		return query.getResultList();	
	}

	@Override
	public boolean updateFriendStatus(int userid, int friendid, String Status) 
	{

		Friend_List friend = new Friend_List();
		
		friend.setUserid(userid);
		friend.setFriendid(friendid);
		friend.setStatus("ACCEPTED");
		update(friend);
		
		
		// Also add add "userid" as a friend of "friendid" (if "a" is frined of "b" then "b" is friend of "a")
		friend.setFriendid(userid);
		friend.setUserid(friendid);
		friend.setStatus("ACCEPTED");
		add(friend);
		
		return false;
	}
	

	
	@Override
	public String update(Friend_List friend) 
	{
		try
		{
			// update this category in the database
			sessionFactory.getCurrentSession().update(friend);
			return "true";	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return "false";
		}		
	}

	@Override
	public String add(Friend_List friend) 
	{
		try
		{
			// persist this category in the database
			sessionFactory.getCurrentSession().persist(friend);
			return "true";	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return "false";
		}	

	}

	@Override
	public boolean delete(Friend_List friend) 
	{	
		try
		{
			// delete this category from the database
			sessionFactory.getCurrentSession().delete(friend);
			return true;	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}				
	}

	@Override
	public boolean deletefriend(int userid, int friendid) 
	{
		Friend_List friend = new Friend_List();
		friend.setUserid(userid);
		friend.setFriendid(friendid);
		return delete(friend);
	}

	@Override
	public boolean addfriend(int userid, int friendid) 
	{
		
		Friend_List friend = new Friend_List();
		friend.setUserid(userid);
		friend.setFriendid(friendid);
		friend.setStatus("ADDED");
		
		return delete(friend);
	}
	
	

}
