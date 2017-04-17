package com.niitbejai.onlinecollaboration.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Friend_List implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2044823641396302924L;
	@Id
	private int friendlistid;

	private int userid;
	private int friendid;
	private String status;
	
	@Override
	public String toString() {
		return "Friend_List [friendlistid=" + friendlistid + ", userid=" + userid + ", friendid=" + friendid
				+ ", status=" + status + "]";
	}
	
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getFriendid() {
		return friendid;
	}
	public void setFriendid(int friendid) {
		this.friendid = friendid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getFriendlistid() {
		return friendlistid;
	}

	public void setFriendlistid(int friendlistid) {
		this.friendlistid = friendlistid;
	}
	
	
	
}
