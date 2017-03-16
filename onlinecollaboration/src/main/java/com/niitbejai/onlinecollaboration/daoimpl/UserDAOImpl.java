package com.niitbejai.onlinecollaboration.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niitbejai.onlinecollaboration.dao.UserDAO;
import com.niitbejai.onlinecollaboration.dto.User_Detail;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO 
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean delete(int id) 
	{
		// instead of deleting what is usually done is to set the flag as false so that the fetch command 
		// ignores it. Makes it easy when the same item has to be added again
		User_Detail user = this.get(id); 
		user.setActive(false);
		
		try
		{
			// delete this category from the database
			sessionFactory.getCurrentSession().update(user);
			return true;	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}		
	}

	@Override
	public User_Detail get(int id) 
	{
		return sessionFactory.getCurrentSession().get(User_Detail.class, Integer.valueOf(id));
	}

	@Override
	public List<User_Detail> list() 
	{
		// v v imp here that the "User" is the entity name and not the table name.
		// In case in the class when using the @Entity annotation is used, if no class name is given
		// then it default picks the class name as entity name.
		
		String selectActiveCategory = "FROM User_Detail WHERE active = :active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		
		query.setParameter("active", true);
		
		return query.getResultList();		
	}

	@Override
	public User_Detail getUserBuUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String add(User_Detail user) 
	{
		try
		{
			// persist this category in the database
			sessionFactory.getCurrentSession().persist(user);
			return "true";	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return "false";
		}	
	}

	@Override
	public String update(User_Detail user) 
	{
		try
		{
			// update this category in the database
			sessionFactory.getCurrentSession().update(user);
			return "true";	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return "false";
		}		
	}

}
