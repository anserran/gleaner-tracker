package es.eucm.gleaner.tracker.java;

import es.eucm.gleaner.java.network.JavaRequestHelper;
import es.eucm.gleaner.tracker.BaseTracker;

public class Tracker extends BaseTracker {

	public Tracker() {
		super(new JavaRequestHelper());
	}

}