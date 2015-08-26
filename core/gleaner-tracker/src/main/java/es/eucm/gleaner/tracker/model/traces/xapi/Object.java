package es.eucm.gleaner.tracker.model.traces.xapi;

public class Object {

    private String objectType = "Activity";
    private Definition definition;
    private String id;

    public Object(String id, Definition definition) {
        this.id = id;
        this.definition = definition;
    }
}
