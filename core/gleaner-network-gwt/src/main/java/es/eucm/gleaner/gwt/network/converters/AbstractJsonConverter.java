package es.eucm.gleaner.gwt.network.converters;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;

public abstract class AbstractJsonConverter<S, T extends JavaScriptObject>
		implements JsonConverter<S, T> {

	@Override
	public T getJSObject(String object) {
		return JsonUtils.safeEval(object).cast();
	}

	public T getJSObject(JavaScriptObject object) {
		return object.cast();
	}

}
