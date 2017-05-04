package com.niitbejai.onlinecollaboration.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Chat implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7845439595216876143L;
	
	@Id
	private int chatid; // using sequence FRIEND_LIST_ID_SEQ

	private int senderuserid;
	private int receiveruserid;
	private String filename;
	@Transient 
	private String chatData;
	@Transient
	private String senderfname;
	@Transient 
	private String sendersname;
	@Transient
	private String receiverfname;
	@Transient
	private String receiversname;
	

	@Override
	public String toString() {
		return "Chat [chatid=" + chatid + ", senderuserid=" + senderuserid + ", receiveruserid=" + receiveruserid
				+ ", filename=" + filename + ", chatData=" + chatData + ", senderfname=" + senderfname
				+ ", sendersname=" + sendersname + ", receiverfname=" + receiverfname + ", receiversname="
				+ receiversname + "]";
	}
	
	
	
	public String getChatData() {
		return chatData;
	}


	public void setChatData(String chatData) {
		this.chatData = chatData;
	}


	public String getSenderfname() {
		return senderfname;
	}


	public void setSenderfname(String senderfname) {
		this.senderfname = senderfname;
	}


	public String getSendersname() {
		return sendersname;
	}


	public void setSendersname(String sendersname) {
		this.sendersname = sendersname;
	}


	public String getReceiverfname() {
		return receiverfname;
	}


	public void setReceiverfname(String receiverfname) {
		this.receiverfname = receiverfname;
	}


	public String getReceiversname() {
		return receiversname;
	}


	public void setReceiversname(String receiversname) {
		this.receiversname = receiversname;
	}


	public int getChatid() {
		return chatid;
	}


	public void setChatid(int chatid) {
		this.chatid = chatid;
	}


	public int getSenderuserid() {
		return senderuserid;
	}


	public void setSenderuserid(int senderuserid) {
		this.senderuserid = senderuserid;
	}


	public int getReceiveruserid() {
		return receiveruserid;
	}


	public void setReceiveruserid(int receiveruserid) {
		this.receiveruserid = receiveruserid;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
