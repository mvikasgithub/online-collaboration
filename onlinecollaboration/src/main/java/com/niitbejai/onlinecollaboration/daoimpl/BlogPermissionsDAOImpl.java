package com.niitbejai.onlinecollaboration.daoimpl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niitbejai.onlinecollaboration.dao.BlogPermissionsDAO;
import com.niitbejai.onlinecollaboration.dto.BlogPermissions;
import com.niitbejai.onlinecollaboration.dto.Friend_List;
import com.niitbejai.onlinecollaboration.dto.UserBlog;


@Repository("blogpermissionsDAO")
@Transactional
public class BlogPermissionsDAOImpl implements BlogPermissionsDAO 
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public BlogPermissions get(int id) 
	{
		return sessionFactory.getCurrentSession().get(BlogPermissions.class, Integer.valueOf(id));
	}		
	
	@Override
	public BlogPermissions getRowByOwnerIdRequesterIdBlogId(int ownerid, int requesterid, int blogid) 
	{
		System.out.println("Inside getRowByUserIdFriendIdBlogId ");
		
		String selectActiveCategory = "FROM BlogPermissions WHERE ownerid = :ownerid AND requesterid = :requesterid AND blogid = :blogid";
		
		Query<BlogPermissions> query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory, BlogPermissions.class);
		
		query.setParameter("ownerid", ownerid);
		query.setParameter("requesterid", requesterid);
		query.setParameter("blogid", blogid);
		
		try
		{
			return query.getSingleResult();
		}
		catch(Exception ex)
		{
			return null;
		}		
	}			
	
	@Override
	public  List<BlogPermissions>  getPermissionsAwaitingList(int ownerid, boolean permitted) 
	{
		System.out.println("Inside getPermissionsAwaitingList ");
				
		String selectActiveCategory = "FROM BlogPermissions WHERE ownerid = :ownerid AND permitted = :permitted";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		
		query.setParameter("ownerid", ownerid);
		query.setParameter("permitted", permitted);
		
		return query.getResultList();		
		
	}			
		
	
	@Override
	public boolean add(BlogPermissions blogpermissions) 
	{
		try
		{
			// persist this category in the database
			sessionFactory.getCurrentSession().persist(blogpermissions);
			return true;	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}		
	}

	@Override
	public boolean update(BlogPermissions blogpermissions) 
	{
		try
		{
			// update this category in the database
			sessionFactory.getCurrentSession().update(blogpermissions);
			return true;	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}		
	}
	
	@Override
	public boolean delete(BlogPermissions blogpermissions) 
	{	
		try
		{
			// delete this category from the database
			sessionFactory.getCurrentSession().delete(blogpermissions);
			return true;	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}				
	}	
	
	public boolean addpermissions(int blogid, int ownerid, int requesterid, boolean status)
	{
		BlogPermissions bp = new BlogPermissions();
		
		// if an entry for this blogid already exists, just update the status. Else add it afresh
		bp = getRowByOwnerIdRequesterIdBlogId(ownerid, requesterid , blogid);
		if( bp != null)
		{
			bp.setPermitted(status);
			update(bp);
		}
		else
		{
			BlogPermissions newbp = new BlogPermissions();
			
			newbp.setBlogpermissionsid(getLastInsertedID());
			newbp.setBlogid(blogid);
			newbp.setOwnerid(ownerid);
			newbp.setRequesterid(requesterid);
			newbp.setPermitted(status);
			
			return add(newbp);
		}
		return true;
	}
	
	public boolean deletepermissions(int blogid, int ownerid, int requesterid)
	{
		BlogPermissions bp = new BlogPermissions();
		
		bp = getRowByOwnerIdRequesterIdBlogId(ownerid, requesterid, blogid ); 
		if( bp != null)
		{
			// if bp is found, check status, if status is true, set it to false. If status is false, delete the entry(row)
			if(bp.isPermitted())
			{
				bp.setPermitted(false);
				return update(bp);
			}
			else
			{
				return delete(bp);
			}
		}		
		
		return false;
		
	}
	

	public boolean isPermitted(int blogid, int ownerid, int requesterid)
	{
		BlogPermissions bp = new BlogPermissions();
		
		bp = getRowByOwnerIdRequesterIdBlogId(ownerid, requesterid, blogid ); 
		
		if(bp != null)
			return bp.isPermitted();
		
		return false;
	}
	
	
	@Override
	public int getLastInsertedID() 
	{
		BigDecimal lastBIseqid = (BigDecimal) sessionFactory.getCurrentSession().createSQLQuery("select BLOG_PERMISSIONS_ID_SEQ.nextval from dual").uniqueResult();
		System.out.println("Blog Permissions NextVal = " + lastBIseqid.toString());
		Integer lastId = lastBIseqid.intValue();
		return lastId.intValue();
	}		
}
