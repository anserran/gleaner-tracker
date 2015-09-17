package es.eucm.gleaner.tracker.model.traces.xapi;

import java.util.Map;

public class Verb {

	private String id;

	private Map<String, String> display;

	public Verb(String id, Map<String, String> display) {
		this.id = id;
		this.display = display;
	}

	public String getId() {
		return id;
	}

	public Map<String, String> getDisplay() {
		return display;
	}
}
