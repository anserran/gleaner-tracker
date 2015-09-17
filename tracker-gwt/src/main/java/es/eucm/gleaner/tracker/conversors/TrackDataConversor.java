package es.eucm.gleaner.tracker.conversors;

import es.eucm.gleaner.gwt.network.converters.JsonConverter;
import es.eucm.gleaner.tracker.model.TrackData;
import es.eucm.gleaner.tracker.data.TrackDataJS;

public class TrackDataConversor extends JsonConverter<TrackData, TrackDataJS> {

	@Override
	public String getJson(TrackData object) {
		return null;
	}

	@Override
	public TrackData getObject(TrackDataJS jsObject) {
		return jsObject.getTrackData();
	}

}
