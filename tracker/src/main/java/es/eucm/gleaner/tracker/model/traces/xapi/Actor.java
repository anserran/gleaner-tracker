package es.eucm.gleaner.tracker.model.traces.xapi;

public class Actor {

	private String objectType = "Agent";
	private Account account;
	private String name;

	public Actor(Account account, String name) {
		this.account = account;
		this.name = name;
	}

	public String getObjectType() {
		return objectType;
	}

	public Account getAccount() {
		return account;
	}

	public String getName() {
		return name;
	}
}
