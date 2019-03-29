package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="friend_list")
public class FriendList {
	
	@Id
	@Column(name="ownerid")
	private int ownerId;
	
	@Column(name="friendid")
	private int friendId;

	public FriendList(int ownerId, int friendId) {
		super();
		this.ownerId = ownerId;
		this.friendId = friendId;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + friendId;
		result = prime * result + ownerId;
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
		FriendList other = (FriendList) obj;
		if (friendId != other.friendId)
			return false;
		if (ownerId != other.ownerId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FriendList [ownerId=" + ownerId + ", friendId=" + friendId + "]";
	}
	

}
