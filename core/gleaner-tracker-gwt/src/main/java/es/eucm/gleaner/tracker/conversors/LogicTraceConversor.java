package es.eucm.gleaner.tracker.conversors;

import com.google.gwt.json.client.JSONObject;

import es.eucm.gleaner.gwt.network.converters.AbstractJsonConverter;
import es.eucm.gleaner.model.traces.LogicTrace;
import es.eucm.gleaner.tracker.data.LogicTraceJS;

public class LogicTraceConversor extends
		AbstractJsonConverter<LogicTrace, LogicTraceJS> {

	@Override
	public String getJson(LogicTrace object) {
		LogicTraceJS trace = (LogicTraceJS) LogicTraceJS.createObject();
		trace.set(object);
		return new JSONObject(trace).toString();
	}

	@Override
	public LogicTrace getObject(LogicTraceJS jsObject) {
		return jsObject.getLogicTrace();
	}

}
