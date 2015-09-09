package es.eucm.gleaner.tracker.model.traces.xapi;

import es.eucm.gleaner.tracker.converter.Identifiers;

public class Account {

	private String homePage = Identifiers.BASE_ID;
	private String name;

	public Account(String name) {
		this.name = name;
	}

	public String getHomePage() {
		return homePage;
	}

	public String getName() {
		return name;
	}
}
