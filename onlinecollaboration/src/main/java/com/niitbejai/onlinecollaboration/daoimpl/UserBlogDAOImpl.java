package com.niitbejai.onlinecollaboration.daoimpl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niitbejai.onlinecollaboration.dao.UserBlogDAO;
import com.niitbejai.onlinecollaboration.dto.Friend_List;
import com.niitbejai.onlinecollaboration.dto.UserBlog;
import com.niitbejai.onlinecollaboration.dto.User_Detail;

@Repository("userblogDAO")
@Transactional
public class UserBlogDAOImpl implements UserBlogDAO 
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public UserBlog get(int id) 
	{
		return sessionFactory.getCurrentSession().get(UserBlog.class, Integer.valueOf(id));
	}	
	
	@Override
	public List<UserBlog> listAllBlogsForId(int userid) 
	{
		// v v imp here that the "User" is the entity name and not the table name.
		// In case in the class when using the @Entity annotation is used, if no class name is given
		// then it default picks the class name as entity name.
		
		String selectActiveCategory = "FROM UserBlog WHERE userid = :userid";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		
		query.setParameter("userid", userid);
		
		return query.getResultList();		
	}	
	
	@Override
	public List<UserBlog> listAllBlogs() 
	{
		// v v imp here that the "User" is the entity name and not the table name.
		// In case in the class when using the @Entity annotation is used, if no class name is given
		// then it default picks the class name as entity name.
		
		String selectActiveCategory = "FROM UserBlog";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		
		//query.setParameter("userid", userid);
		
		return query.getResultList();		
	}		

	@Override
	public boolean add(UserBlog userblog) 
	{
		try
		{
			// persist this category in the database
			sessionFactory.getCurrentSession().persist(userblog);
			return true;	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}		
	}

	@Override
	public boolean update(UserBlog userblog) 
	{
		try
		{
			// update this category in the database
			sessionFactory.getCurrentSession().update(userblog);
			return true;	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}		
	}
	
	@Override
	public boolean delete(UserBlog userblog) 
	{	
		try
		{
			// delete this category from the database
			sessionFactory.getCurrentSession().delete(userblog);
			return true;	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}				
	}	

	@Override
	public boolean deleteById(int id) 
	{
		UserBlog userblog = new UserBlog();
		userblog = get(id);
		return delete(userblog);
	}	
	
	@Override
	public int addblog(UserBlog userblog) 
	{
		userblog.setBlogid(getLastInsertedID());
		System.out.println("Inside addblog -> " + userblog);
		if (add(userblog) == true)
			return userblog.getBlogid();
		
		return -1;
	}	
	
	@Override
	public int getLastInsertedID() 
	{
		BigDecimal lastBIseqid = (BigDecimal) sessionFactory.getCurrentSession().createSQLQuery("select USER_BLOG_ID_SEQ.nextval from dual").uniqueResult();
		System.out.println("User Blog NextVal = " + lastBIseqid.toString());
		Integer lastId = lastBIseqid.intValue();
		return lastId.intValue();
	}	

}
