package es.eucm.gleaner.tracker.data;

import com.google.gwt.core.client.JavaScriptObject;

import es.eucm.gleaner.tracker.model.TrackData;

public class TrackDataJS extends JavaScriptObject {

	protected TrackDataJS() {

	}

	public final native String getAuthToken() /*-{
												return this.authToken;
												}-*/;

	public final TrackData getTrackData() {
		return new TrackData(getAuthToken());
	}

}
