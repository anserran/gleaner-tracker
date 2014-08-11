package es.eucm.gleaner.tracker;

import es.eucm.gleaner.java.network.JavaRequestHelper;
import es.eucm.gleaner.tracker.Tracker;

public class JerseyTracker extends Tracker {

	public JerseyTracker() {
		super(new JavaRequestHelper());
	}

}