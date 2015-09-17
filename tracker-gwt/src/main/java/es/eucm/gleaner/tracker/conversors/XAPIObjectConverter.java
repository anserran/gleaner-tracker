package es.eucm.gleaner.tracker.conversors;

import com.google.gwt.json.client.JSONObject;
import es.eucm.gleaner.gwt.network.converters.JsonConverter;
import es.eucm.gleaner.tracker.data.XAPIObjectJS;
import es.eucm.gleaner.tracker.model.traces.LogicTrace;
import es.eucm.gleaner.tracker.model.traces.xapi.XAPIObject;

public class XAPIObjectConverter extends
		JsonConverter<XAPIObject, XAPIObjectJS> {

	@Override
	public String getJson(XAPIObject object) {
		XAPIObjectJS statement = (XAPIObjectJS) XAPIObjectJS.createObject();
		statement.set(object);
		return new JSONObject(statement).toString();
	}

	@Override
	public XAPIObject getObject(XAPIObjectJS jsObject) {
		return jsObject.getXAPIObject();
	}

}
