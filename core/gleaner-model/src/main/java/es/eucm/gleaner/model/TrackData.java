package es.eucm.gleaner.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TrackData {

	private String sessionKey;

	public TrackData() {
	}

	public TrackData(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
}
