package com.niitbejai.onlinecollaboration.dao;

import java.util.List;

import com.niitbejai.onlinecollaboration.dto.UserBlog;

public interface UserBlogDAO 
{
	public boolean add(UserBlog userblog);
	public boolean update(UserBlog userblog);
	public boolean delete(UserBlog userblog);
	public boolean deleteById(int id);
	
	public int getLastInsertedID();
	public int addblog(UserBlog userblog);
	public UserBlog get(int id);
	public List<UserBlog> listAllBlogsForId(int userid);
	public List<UserBlog> listAllBlogs();

}
