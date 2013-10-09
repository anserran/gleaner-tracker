package es.eucm.gleaner.tracker.conversors;

import com.google.gwt.json.client.JSONObject;

import es.eucm.gleaner.gwt.network.converters.AbstractJsonConverter;
import es.eucm.gleaner.model.traces.InputTrace;
import es.eucm.gleaner.tracker.data.InputTraceJS;

public class InputTraceConversor extends
		AbstractJsonConverter<InputTrace, InputTraceJS> {

	@Override
	public String getJson(InputTrace object) {
		InputTraceJS trace = (InputTraceJS) InputTraceJS.createObject();
		trace.set(object);
		return new JSONObject(trace).toString();
	}

	@Override
	public InputTrace getObject(InputTraceJS jsObject) {
		return null;
	}

}
