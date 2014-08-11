package es.eucm.gleaner.tracker;

import es.eucm.gleaner.gwt.network.GwtRequestHelper;
import es.eucm.gleaner.model.TrackData;
import es.eucm.gleaner.model.traces.InputTrace;
import es.eucm.gleaner.model.traces.LogicTrace;
import es.eucm.gleaner.tracker.conversors.InputTraceConversor;
import es.eucm.gleaner.tracker.conversors.LogicTraceConversor;
import es.eucm.gleaner.tracker.conversors.TrackDataConversor;

public class GwtTracker extends Tracker {

	public GwtTracker(boolean crossDomain) {
		super(new GwtRequestHelper(crossDomain));
		GwtRequestHelper rh = (GwtRequestHelper) super.requestHelper;
		rh.addConverter(TrackData.class, new TrackDataConversor());
		rh.addConverter(InputTrace.class, new InputTraceConversor());
		rh.addConverter(LogicTrace.class, new LogicTraceConversor());
	}
}
