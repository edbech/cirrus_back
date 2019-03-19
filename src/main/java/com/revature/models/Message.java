package com.revature.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="messages")
@SequenceGenerator(name="message_seq", sequenceName="messages_seq", allocationSize=1)
public class Message {
	
	@Id
	@Column(name="messageid")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="message_seq")
	private int messageId;
	
	@Column(name="sender")
	private int sender;
	
	@Column(name="reciever")
	private int reciever;
	
	@Column(name="gameid")
	private int gameId;
	
	@Column(name="message")
	private String message;
	
	@Column(name="timesent")
	private Timestamp timesent;

	public Message(int messageId, int sender, int reciever, String message, Timestamp timesent) {
		super();
		this.messageId = messageId;
		this.sender = sender;
		this.reciever = reciever;
		this.message = message;
		this.timesent = timesent;
	}

	public Message(int messageId, int sender, int reciever, int gameId, String message, Timestamp timesent) {
		super();
		this.messageId = messageId;
		this.sender = sender;
		this.reciever = reciever;
		this.gameId = gameId;
		this.message = message;
		this.timesent = timesent;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getReciever() {
		return reciever;
	}

	public void setReciever(int reciever) {
		this.reciever = reciever;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getTimesent() {
		return timesent;
	}

	public void setTimesent(Timestamp timesent) {
		this.timesent = timesent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + gameId;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + messageId;
		result = prime * result + reciever;
		result = prime * result + sender;
		result = prime * result + ((timesent == null) ? 0 : timesent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (gameId != other.gameId)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (messageId != other.messageId)
			return false;
		if (reciever != other.reciever)
			return false;
		if (sender != other.sender)
			return false;
		if (timesent == null) {
			if (other.timesent != null)
				return false;
		} else if (!timesent.equals(other.timesent))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", sender=" + sender + ", reciever=" + reciever + ", gameId="
				+ gameId + ", message=" + message + ", timesent=" + timesent + "]";
	}	
	
}
