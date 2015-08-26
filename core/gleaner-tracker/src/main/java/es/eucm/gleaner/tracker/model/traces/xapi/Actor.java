package es.eucm.gleaner.tracker.model.traces.xapi;

public class Actor {

    private String objectType = "Agent";
    private Account account;
    private String name;

    public Actor(Account account, String name) {
        this.account = account;
        this.name = name;
    }
}
