package es.eucm.gleaner.tracker.model.traces.xapi;

import java.lang.*;
import java.lang.Object;
import java.util.Map;

public class Definition {

	private String type;
	private Map<String, java.lang.Object> extensions;

	public Definition(String type, Map<String, Object> extensions) {
		this.type = type;
		this.extensions = extensions;
	}

	public String getType() {
		return type;
	}

	public Map<String, Object> getExtensions() {
		return extensions;
	}
}
