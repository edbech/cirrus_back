package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="users")
@SequenceGenerator(name="user_seq", sequenceName="users_seq", allocationSize=1)
public class User {

	
	@Id
	@Column(name="userid")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_seq")
	private int userId;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="aboutme")
	private String aboutMe;
	
	@Column(name="avatar")
	private byte Avatar;
	
	@Column(name="score")
	private int Score;
	
	public User() {
		super();
	}

	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User(String username, String password, String email, byte avatar) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		Avatar = avatar;
	}

	public User(int userId, String username, String password, String email, String aboutMe, byte avatar) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.aboutMe = aboutMe;
		Avatar = avatar;
	}

	public User(int userId, String username, String password, String email, String aboutMe, byte avatar, int score) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.aboutMe = aboutMe;
		Avatar = avatar;
		Score = score;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public byte getAvatar() {
		return Avatar;
	}

	public void setAvatar(byte avatar) {
		Avatar = avatar;
	}

	public int getScore() {
		return Score;
	}

	public void setScore(int score) {
		Score = score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Avatar;
		result = prime * result + Score;
		result = prime * result + ((aboutMe == null) ? 0 : aboutMe.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + userId;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (Avatar != other.Avatar)
			return false;
		if (Score != other.Score)
			return false;
		if (aboutMe == null) {
			if (other.aboutMe != null)
				return false;
		} else if (!aboutMe.equals(other.aboutMe))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", aboutMe=" + aboutMe + ", Avatar=" + Avatar + ", Score=" + Score + "]";
	}

}
