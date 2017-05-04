package com.niitbejai.onlinecollaboration.daoimpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niitbejai.onlinecollaboration.dao.ChatDAO;
import com.niitbejai.onlinecollaboration.dto.Chat;

@Repository("chatDAO")
@Transactional
public class ChatDAOImpl implements ChatDAO 
{
	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public String getChatFilename(int senderUserid, int receiverUserid) 
	{
		System.out.println("Inside getChatFilename ");
		
		String selectfilename = "Select filename FROM Chat WHERE senderuserid = :senderUserid AND receiveruserid= :receiverUserid ";
		
		Query query = sessionFactory.getCurrentSession().createNativeQuery(selectfilename).addScalar("filename", StandardBasicTypes.STRING);
		
		query.setParameter("receiverUserid", receiverUserid);
		query.setParameter("senderUserid", senderUserid);

		
		try
		{
			return (String) query.getSingleResult();
		}
		catch(Exception ex)
		{
			return null;
		}		
		
	}			
	
	@Override
	public boolean add(Chat chat) 
	{
		try
		{
			// persist this category in the database
			sessionFactory.getCurrentSession().persist(chat);
			return true;	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}		
	}	
	
	@Override
	public boolean update(Chat chat) 
	{
		try
		{
			// update this category in the database
			sessionFactory.getCurrentSession().update(chat);
			return true;	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}		
	}
	
	@Override
	public boolean delete(Chat chat) 
	{	
		try
		{
			// delete this category from the database
			sessionFactory.getCurrentSession().delete(chat);
			return true;	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}				
	}		
	
	@Override
	public boolean WriteChat(Chat chat) 
	{
		//first check if the file exists for this pair of users. 
		//Filename format is Chat_loweruserid_higheruserid.txt
		String filename = "";
		
		System.out.println("Inside WriteChat");
		
		filename = System.getProperty("user.dir") + "\\";
		
		if(chat.getSenderuserid() < chat.getReceiveruserid())
			filename += "Chat_" + chat.getSenderuserid() + "_" + chat.getReceiveruserid() + ".txt";
		else
			filename += "Chat_" + chat.getReceiveruserid() + "_" + chat.getSenderuserid() + ".txt";
		
		System.out.println("Chat filename is: " + filename);
		
		// if the chat entry is not there in the database. add it.
		
		String filenameFromDB = "";
		if(chat.getSenderuserid() < chat.getReceiveruserid())
			filenameFromDB = getChatFilename(chat.getSenderuserid(), chat.getReceiveruserid());
		else
			filenameFromDB = getChatFilename(chat.getReceiveruserid(),chat.getSenderuserid());
		
		System.out.println("Chat filename from DB is: " + filenameFromDB);
		
		if(filenameFromDB == null)
		{
			// seems there is not entry for this pair of users in the database. Add an entry
			chat.setFilename(filename);
			chat.setChatid(getLastInsertedID());
			add(chat);
		}
		
		String tmp = chat.getSenderfname() + " " + chat.getSendersname() + "-" + chat.getReceiverfname() + " " + chat.getReceiversname() + " :";
		
		chat.setChatData(tmp + chat.getChatData());
		
		WriteToFile(filename, chat.getChatData());
		
		return true;
	}
	
	@Override
	public String ReadChat(Chat chat) 
	{
		/*
		 * Currently not using the database to read the filename as it is auto-generated using the sender and receiver's
		 * ids. Going forward it might be needed to read the filename from the database and that is when
		 * code can be inserted here.
		 */
		
		System.out.println("Inside ReadChat");
		
		//first check if the file exists for this pair of users. 
		//Filename format is Chat_loweruserid_higheruserid.txt
		String filename = "";
		
		filename = System.getProperty("user.dir") + "\\";
		
		if(chat.getSenderuserid() < chat.getReceiveruserid())
			filename += "Chat_" + chat.getSenderuserid() + "_" + chat.getReceiveruserid() + ".txt";
		else
			filename += "Chat_" + chat.getReceiveruserid() + "_" + chat.getSenderuserid() + ".txt";
		
		System.out.println("Filename (to read): " + filename);
		
		return ReadFromFile(filename);
		
	}
	
	public boolean WriteToFile(String filename, String strChatTxt)
	{
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		System.out.println("Inside WriteToFile");
		System.out.println("Data to write: " + strChatTxt);

		try {

			File file = new File(filename);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			else
			{
				System.out.println("Adding newline character...");
				strChatTxt = "\n" + strChatTxt;
			}

			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);

			bw.write(strChatTxt);

			System.out.println("File Writing Done....");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}

	
		return true;
	}
	
	public String ReadFromFile(String filename)
	{
		String strChatText = "";
		
		System.out.println("Inside ReadFromFile");
		
		BufferedReader br = null;
		FileReader fr = null;

		try {
			

			File file = new File(filename);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				return "";
			}

			fr = new FileReader(filename);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(filename));

			while ((sCurrentLine = br.readLine()) != null) 
			{
				System.out.println(sCurrentLine);
				strChatText += sCurrentLine + "\r\n";
			}

		} catch (IOException e) 
		{

			e.printStackTrace();

		} finally 
		{

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}		
	
		return strChatText;
	}

	public int getLastInsertedID() 
	{
		BigDecimal lastBIseqid = (BigDecimal) sessionFactory.getCurrentSession().createSQLQuery("select CHAT_ID_SEQ.nextval from dual").uniqueResult();
		System.out.println("Chat - Next Chat ID = " + lastBIseqid.toString());
		Integer lastId = lastBIseqid.intValue();
		return lastId.intValue();
	}		
	
}
