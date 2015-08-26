package es.eucm.gleaner.tracker.model.traces.xapi;

public class XAPIObject {

    private Actor actor;
    private Verb verb;
    private es.eucm.gleaner.tracker.model.traces.xapi.Object object;

    public XAPIObject(Actor actor, Verb verb, es.eucm.gleaner.tracker.model.traces.xapi.Object object) {
        this.object = object;
        this.actor = actor;
        this.verb = verb;
    }
}
