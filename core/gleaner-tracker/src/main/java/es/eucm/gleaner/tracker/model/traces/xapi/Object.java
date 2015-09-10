package es.eucm.gleaner.tracker.model.traces.xapi;

import es.eucm.gleaner.tracker.converter.Identifiers;

public class Object {

	private String objectType = Identifiers.ACTIVITY_OBJECT_TYPE;
	private Definition definition;
	private String id;

	public Object(String id, Definition definition) {
		this.id = id;
		this.definition = definition;
	}

	public String getObjectType() {
		return objectType;
	}

	public Definition getDefinition() {
		return definition;
	}

	public String getId() {
		return id;
	}
}
