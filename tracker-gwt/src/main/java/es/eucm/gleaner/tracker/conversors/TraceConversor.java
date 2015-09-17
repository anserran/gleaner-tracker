package es.eucm.gleaner.tracker.conversors;

import com.google.gwt.json.client.JSONObject;
import es.eucm.gleaner.gwt.network.converters.JsonConverter;
import es.eucm.gleaner.tracker.data.TraceJS;
import es.eucm.gleaner.tracker.model.traces.Trace;

public class TraceConversor extends JsonConverter<Trace, TraceJS> {

	@Override
	public String getJson(Trace object) {
		TraceJS trace = (TraceJS) TraceJS.createObject();
		trace.set(object);
		return new JSONObject(trace).toString();
	}

	@Override
	public Trace getObject(TraceJS jsObject) {
		// Shouldn't be invoked
		return null;
	}

}
