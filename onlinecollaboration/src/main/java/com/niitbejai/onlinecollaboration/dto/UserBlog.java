package com.niitbejai.onlinecollaboration.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component 
@Entity
public class UserBlog implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2865446832372635502L;
	
	@Id
	private int blogid; 

	private int userid;
	@NotEmpty
	private String authorfname;
	@NotEmpty
	private String authorsname;
	@NotEmpty
	private String title;
	@NotEmpty
	private String fullblog;
	
	@Override
	public String toString() {
		return "UserBlog [blogid=" + blogid + ", userid=" + userid + ", authorfname=" + authorfname + ", authorsname="
				+ authorsname + ", title=" + title + ", fullblog=" + fullblog + "]";
	}	

	public int getBlogid() {
		return blogid;
	}
	

	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getAuthorfname() {
		return authorfname;
	}
	public void setAuthorfname(String authorfname) {
		this.authorfname = authorfname;
	}
	public String getAuthorsname() {
		return authorsname;
	}
	public void setAuthorsname(String authorsname) {
		this.authorsname = authorsname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFullblog() {
		return fullblog;
	}
	public void setFullblog(String fullblog) {
		this.fullblog = fullblog;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
