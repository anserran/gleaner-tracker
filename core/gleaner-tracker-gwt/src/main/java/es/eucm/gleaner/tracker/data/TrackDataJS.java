package es.eucm.gleaner.tracker.data;

import com.google.gwt.core.client.JavaScriptObject;

import es.eucm.gleaner.model.TrackData;

public class TrackDataJS extends JavaScriptObject {

	protected TrackDataJS() {

	}

	public final native String getSessionKey() /*-{
												return this.sessionKey;
												}-*/;

	public final TrackData getTrackData() {
		return new TrackData(getSessionKey());
	}

}
