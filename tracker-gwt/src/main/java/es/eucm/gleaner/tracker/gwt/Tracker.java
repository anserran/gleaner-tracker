package es.eucm.gleaner.tracker.gwt;

import es.eucm.gleaner.gwt.network.GwtRequestHelper;
import es.eucm.gleaner.tracker.BaseTracker;
import es.eucm.gleaner.tracker.conversors.TrackDataConversor;
import es.eucm.gleaner.tracker.model.TrackData;

public class Tracker extends BaseTracker {

	public Tracker() {
		super(new GwtRequestHelper());
		GwtRequestHelper rh = (GwtRequestHelper) super.requestHelper;
		rh.addConverter(TrackData.class, new TrackDataConversor());
	}
}
