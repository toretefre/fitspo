package tdt4140.gr1806.app.core;

import java.sql.Date;

/**
 * 
 * @author Magnus
 * Class for handling messages
 */

public class Message {
	private int id;
	private Date date;
	private int cusID;
	private String message;

	public Message(Date date, int cusID, String message) {
		this.date = date;
		this.cusID = cusID;
		this.message= message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCusID() {
		return cusID;
	}

	public void setCus(int cus) {
		this.cusID = cus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
