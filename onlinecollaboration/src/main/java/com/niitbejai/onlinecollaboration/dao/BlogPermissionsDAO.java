package com.niitbejai.onlinecollaboration.dao;

import java.util.List;

import com.niitbejai.onlinecollaboration.dto.BlogPermissions;

public interface BlogPermissionsDAO 
{

	public BlogPermissions get(int id);
	public boolean add(BlogPermissions blogpermissions);
	public boolean update(BlogPermissions blogpermissions);
	public boolean delete(BlogPermissions blogpermissions);
	
	public boolean addpermissions(int blogid, int ownerid, int requesterid, boolean status);
	public BlogPermissions getRowByOwnerIdRequesterIdBlogId(int ownerid, int requesterid, int blogid);
	public List<BlogPermissions> getPermissionsAwaitingList(int ownerid, boolean permitted);
	public boolean deletepermissions(int blogid, int ownerid, int requesterid );
	public boolean isPermitted(int blogid, int ownerid, int requesterid);
	public int getLastInsertedID();
	
}
