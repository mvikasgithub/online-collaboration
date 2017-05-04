package com.niitbejai.onlinecollaboration.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component 
@Entity
public class BlogPermissions implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6503949377028608047L;

	@Id
	private int blogpermissionsid; // dummy entry to act as primary key - sequence BLOG_PERMISSIONS_ID_SEQ
	
	private int blogid;

	private int ownerid;
	private int requesterid;
	private boolean permitted;
	
	
	@Override
	public String toString() {
		return "BlogPermissions [blogpermissionsid=" + blogpermissionsid + ", blogid=" + blogid + ", ownerid=" + ownerid
				+ ", requesterid=" + requesterid + ", permitted=" + permitted + "]";
	}


	public int getBlogpermissionsid() {
		return blogpermissionsid;
	}


	public void setBlogpermissionsid(int blogpermissionsid) {
		this.blogpermissionsid = blogpermissionsid;
	}	
	
	public int getBlogid() {
		return blogid;
	}
	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}
	public int getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}
	public int getRequesterid() {
		return requesterid;
	}
	public void setRequesterid(int requesterid) {
		this.requesterid = requesterid;
	}
	public boolean isPermitted() {
		return permitted;
	}
	public void setPermitted(boolean permitted) {
		this.permitted = permitted;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
