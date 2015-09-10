package es.eucm.gleaner.tracker;

import es.eucm.gleaner.gwt.network.GwtRequestHelper;
import es.eucm.gleaner.tracker.conversors.XAPIObjectConverter;
import es.eucm.gleaner.tracker.model.TrackData;
import es.eucm.gleaner.tracker.conversors.TrackDataConversor;
import es.eucm.gleaner.tracker.model.traces.xapi.XAPIObject;

public class GwtTracker extends Tracker {

	public GwtTracker(boolean crossDomain) {
		super(new GwtRequestHelper(crossDomain));
		GwtRequestHelper rh = (GwtRequestHelper) super.requestHelper;
		rh.addConverter(TrackData.class, new TrackDataConversor());
		rh.addConverter(XAPIObject.class, new XAPIObjectConverter());
	}
}
