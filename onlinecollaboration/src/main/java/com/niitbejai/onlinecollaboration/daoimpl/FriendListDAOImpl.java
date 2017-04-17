package com.niitbejai.onlinecollaboration.daoimpl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niitbejai.onlinecollaboration.dao.FriendListDAO;
import com.niitbejai.onlinecollaboration.dto.Friend_List;
import com.niitbejai.onlinecollaboration.dto.User_Detail;

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
	
	//This method selects all those initiatorids that have sent a friend request to any other userid.
	//and the status of the request is "ADDED".
	//This will be shown as "ACCEPT PENDING" (against the initiated) in the FE and upon clicking on that link need to invoke   
	//"updateFriendStatus" so that the status is changed to "ACCEPTED" for both.
	@Override
	public List<Long> getAcceptPendingList(int initiatorid) 
	{
		String selectFriendId = "SELECT friendid FROM FRIEND_LIST WHERE userid = :initiatorid AND status = 'ADDED'";
		Query query = sessionFactory.getCurrentSession().createNativeQuery(selectFriendId).addScalar("friendid", StandardBasicTypes.LONG);
		query.setParameter("initiatorid", initiatorid);
		
		return query.getResultList();	
	}
	
	//This method selects all those friendids for which some initiators have sent a friend request. These will show
	// up as "ACCEPT" in the FE for that. For the terminatorid, in the FE, "ACCEPT" link will be displayed in
	// the FE. When the user clicks on the "ACCEPT" link FE will invoke "updateFriendStatus" so that the 
	// status is changed to "ACCEPTED" for both.
	@Override
	public List<Long> getFriendRequestPendingList(int terminatorid) 
	{
		String selectFriendId = "SELECT userid FROM FRIEND_LIST WHERE friendid = :terminatorid AND status = 'ADDED'";
		Query query = sessionFactory.getCurrentSession().createNativeQuery(selectFriendId).addScalar("userid", StandardBasicTypes.LONG);
		query.setParameter("terminatorid", terminatorid);
		
		return query.getResultList();	
	}
	
	

	// when a userid accepts another userid as a friend. Then both the users will be added as freinds of each other
	// and their status updated.
	@Override
	public boolean updateFriendStatus(int userid, int friendid, String status) 
	{

		/*Friend_List friend = new Friend_List();
		
		friend.setUserid(userid);
		friend.setFriendid(friendid);
		friend.setStatus(status);
		if(!update(friend)) // updating status of initiator
			return false; */
		Friend_List friend = getRowByUserIdFriendId(userid, friendid);
		friend.setStatus(status);
		if(!update(friend)) // updating status of initiator
			return false; 
			
		
		
		// Also add add "userid" as a friend of "friendid" (if "a" is frined of "b" then "b" is friend of "a")
		/*Friend_List refriend = new Friend_List();
		refriend.setFriendid(userid);
		refriend.setUserid(friendid);
		refriend.setStatus(status);
		
		if(!add(refriend)) // adding the status of acceptor
			return false;*/
		if(!addfriend(friendid, userid, "ACCEPTED"))
			return false;
		
		return true;
	}
	

	
	@Override
	public boolean update(Friend_List friend) 
	{
		try
		{
			// update this category in the database
			sessionFactory.getCurrentSession().update(friend);
			return true;	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}		
	}

	@Override
	public boolean add(Friend_List friend) 
	{
		try
		{
			// persist this category in the database
			sessionFactory.getCurrentSession().persist(friend);
			return true;	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
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
		/*Friend_List friend = new Friend_List();
		friend.setUserid(userid);
		friend.setFriendid(friendid);
		return delete(friend);*/
		
		Friend_List friend = getRowByUserIdFriendId(userid, friendid);
		return delete(friend);
	}

	@Override
	public boolean addfriend(int userid, int friendid, String status) 
	{
		
		Friend_List friend = new Friend_List();
		friend.setFriendlistid(getLastInsertedID());
		friend.setUserid(userid);
		friend.setFriendid(friendid);
		friend.setStatus(status);
		
		return add(friend);
	}
	
	@Override
	public int getLastInsertedID() {
		BigDecimal lastBIseqid = (BigDecimal) sessionFactory.getCurrentSession().createSQLQuery("select FRIEND_LIST_ID_SEQ.nextval from dual").uniqueResult();
		System.out.println("Friend List NextVal = " + lastBIseqid.toString());
		Integer lastId = lastBIseqid.intValue();
		return lastId.intValue();
	}
	
	@Override
	public Friend_List getRowByUserIdFriendId(int userid, int friendid) 
	{
		System.out.println("Inside getRowByUserIdFriendId ");
		
		String selectActiveCategory = "FROM Friend_List WHERE userid = :userid AND friendid = :friendid";
		
		Query<Friend_List> query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory, Friend_List.class);
		
		query.setParameter("userid", userid);
		query.setParameter("friendid", friendid);
		
		try
		{
			return query.getSingleResult();
		}
		catch(Exception ex)
		{
			return null;
		}		
	}		

}
