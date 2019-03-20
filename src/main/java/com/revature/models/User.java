package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.type.BlobType;


@Entity
@Table(name="users")
@SequenceGenerator(name="users_seq", sequenceName="users_seq", allocationSize=1)
public class User {
	
	@Id
	@Column(name="userid")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="users_seq")
	private int userId;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="aboutme")
	private String aboutMe;
	
	// A little funky, not sure if BlobType is the correct type
	@Column(name="avatar")
	private BlobType avatar;
	
	@Column(name="score")
	private int score;
	
	@Column(name="retired")
	private int retired;
	
	@Column(name="securityquestion")
	private String securityquestion;
	
	@Column(name="securityanswer")
	private String securityanswer;
	
	public User() {
		super();
	}
	
	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User(int userId, String username, String password, String email) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User(int userId, String username, String password, String email, String aboutMe, BlobType avatar, int score,
			int retired, String securityquestion, String securityanswer) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.aboutMe = aboutMe;
		this.avatar = avatar;
		this.score = score;
		this.retired = retired;
		this.securityquestion = securityquestion;
		this.securityanswer = securityanswer;
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

	public BlobType getavatar() {
		return avatar;
	}

	public void setavatar(BlobType avatar) {
		this.avatar = avatar;
	}

	public int getscore() {
		return score;
	}

	public void setscore(int score) {
		this.score = score;
	}
	
	public int isRetired() {
		return retired;
	}

	public void setRetired(int retired) {
		this.retired = retired;
	}

	public String getSecurityquestion() {
		return securityquestion;
	}

	public void setSecurityquestion(String securityquestion) {
		this.securityquestion = securityquestion;
	}

	public String getSecurityanswer() {
		return securityanswer;
	}

	public void setSecurityanswer(String securityanswer) {
		this.securityanswer = securityanswer;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aboutMe == null) ? 0 : aboutMe.hashCode());
		result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + retired;
		result = prime * result + score;
		result = prime * result + ((securityanswer == null) ? 0 : securityanswer.hashCode());
		result = prime * result + ((securityquestion == null) ? 0 : securityquestion.hashCode());
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
		if (aboutMe == null) {
			if (other.aboutMe != null)
				return false;
		} else if (!aboutMe.equals(other.aboutMe))
			return false;
		if (avatar == null) {
			if (other.avatar != null)
				return false;
		} else if (!avatar.equals(other.avatar))
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
		if (retired != other.retired)
			return false;
		if (score != other.score)
			return false;
		if (securityanswer == null) {
			if (other.securityanswer != null)
				return false;
		} else if (!securityanswer.equals(other.securityanswer))
			return false;
		if (securityquestion == null) {
			if (other.securityquestion != null)
				return false;
		} else if (!securityquestion.equals(other.securityquestion))
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
				+ ", aboutMe=" + aboutMe + ", avatar=" + avatar + ", score=" + score + ", retired=" + retired
				+ ", securityquestion=" + securityquestion + ", securityanswer=" + securityanswer + "]";
	}

	



	

}
