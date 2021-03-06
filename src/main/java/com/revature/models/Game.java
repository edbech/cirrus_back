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
@Table(name="game")
@SequenceGenerator(name="game_seq", sequenceName="game_seq", allocationSize=1)
public class Game {
	
	@Id
	@Column(name="gameid")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="game_seq")
	private int gameId;
	
	@Column(name="playerx")
	private String playerX;
	
	@Column(name="playero")
	private String playerO;
	
	@Column(name="gamestate")
	private String gamestate;
	
	@Column(name="result")
	private String result;
	
	@Column(name="started")
	private Timestamp started;
	
	@Column(name="finished")
	private Timestamp finished;
	
	@Column(name="ispublic")
	private int isPublic;
	
	public Game() {
		super();
	}

	public Game(String playerX, int isPublic) {
		super();
		this.playerX = playerX;
		this.isPublic = isPublic;
	}

	public Game(String playerX, String playerO, int isPublic) {
		super();
		this.playerX = playerX;
		this.playerO = playerO;
		this.isPublic = isPublic;
	}

	public Game(int gameId, String playerX, String playerO, String gamestate, String result, Timestamp started,
			Timestamp finished, int isPublic) {
		super();
		this.gameId = gameId;
		this.playerX = playerX;
		this.playerO = playerO;
		this.gamestate = gamestate;
		this.result = result;
		this.started = started;
		this.finished = finished;
		this.isPublic = isPublic;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getPlayerX() {
		return playerX;
	}

	public void setPlayerX(String playerX) {
		this.playerX = playerX;
	}

	public String getPlayerO() {
		return playerO;
	}

	public void setPlayerO(String playerO) {
		this.playerO = playerO;
	}

	public String getGamestate() {
		return gamestate;
	}

	public void setGamestate(String gamestate) {
		this.gamestate = gamestate;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Timestamp getStarted() {
		return started;
	}

	public void setStarted(Timestamp started) {
		this.started = started;
	}

	public Timestamp getFinished() {
		return finished;
	}

	public void setFinished(Timestamp finished) {
		this.finished = finished;
	}

	public int getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((finished == null) ? 0 : finished.hashCode());
		result = prime * result + gameId;
		result = prime * result + ((gamestate == null) ? 0 : gamestate.hashCode());
		result = prime * result + isPublic;
		result = prime * result + ((playerO == null) ? 0 : playerO.hashCode());
		result = prime * result + ((playerX == null) ? 0 : playerX.hashCode());
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result + ((started == null) ? 0 : started.hashCode());
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
		Game other = (Game) obj;
		if (finished == null) {
			if (other.finished != null)
				return false;
		} else if (!finished.equals(other.finished))
			return false;
		if (gameId != other.gameId)
			return false;
		if (gamestate == null) {
			if (other.gamestate != null)
				return false;
		} else if (!gamestate.equals(other.gamestate))
			return false;
		if (isPublic != other.isPublic)
			return false;
		if (playerO == null) {
			if (other.playerO != null)
				return false;
		} else if (!playerO.equals(other.playerO))
			return false;
		if (playerX == null) {
			if (other.playerX != null)
				return false;
		} else if (!playerX.equals(other.playerX))
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		if (started == null) {
			if (other.started != null)
				return false;
		} else if (!started.equals(other.started))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", playerX=" + playerX + ", playerO=" + playerO + ", gamestate=" + gamestate
				+ ", result=" + result + ", started=" + started + ", finished=" + finished + ", isPublic=" + isPublic
				+ "]";
	}

}
