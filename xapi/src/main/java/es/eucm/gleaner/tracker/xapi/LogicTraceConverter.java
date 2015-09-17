package es.eucm.gleaner.tracker.xapi;

import es.eucm.gleaner.tracker.model.traces.Events;
import es.eucm.gleaner.tracker.model.traces.LogicTrace;
import es.eucm.gleaner.tracker.model.traces.xapi.*;

import java.lang.Object;
import java.util.HashMap;
import java.util.Map;

/**
 * Transforms {@link LogicTrace}s into {@link XAPIObject}s.
 * 
 */
public class LogicTraceConverter implements
		XAPITracesConverter.XAPIConverter<LogicTrace> {

	@Override
	public XAPIObject convert(LogicTrace trace, String playerName) {
		String event = trace.getEvent();
		String verbString = Events.toVerb.get(event);

		if (verbString == null) {
			verbString = event;
		}

		String objectId = trace.getTarget();
		if (objectId == null) {
			objectId = Identifiers.OBJECT_NONE;
		}

		String verbId = verbString, definitionType = objectId;

		Map<String, Object> extensions = new HashMap<String, Object>();
		extensions.put("event", event);
		if (trace.getTarget() != null) {
			extensions.put("target", trace.getTarget());
		}
		if (trace.getValue() != null) {
			extensions.put("value", trace.getValue());
		}
		if (trace.getValue2() != null) {
			extensions.put("value2", trace.getValue2());
		}
		if (trace.getValue3() != null) {
			extensions.put("value3", trace.getValue3());
		}

		// Actor
		Actor actor = new Actor(new Account(playerName), playerName);

		// Verb
		Map<String, String> display = new HashMap<String, String>();
		display.put("en-US", verbId);
		display.put("es-ES", verbId);
		Verb verb = new Verb(Identifiers.BASE_ID + verbId, display);

		// Object
		Definition definition;
		es.eucm.gleaner.tracker.model.traces.xapi.Object object;
		definition = new Definition(Identifiers.OBJECTS_ID + definitionType,
				extensions);

		object = new es.eucm.gleaner.tracker.model.traces.xapi.Object(
				Identifiers.GAMES_ID + objectId, definition);

		return new XAPIObject(actor, verb, object);
	}
}
