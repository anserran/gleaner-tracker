package es.eucm.gleaner.tracker.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TrackData {

	private String authToken;

	private String playerName;

	public TrackData() {
	}

	public TrackData(String authToken) {
		this.authToken = authToken;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
}
