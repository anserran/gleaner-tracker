package es.eucm.gleaner.gwt.network.converters;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;

/**
 * Converts and object into a JavaScriptObject
 *
 * @param <S>
 *            the class of the original object
 * @param <T>
 *            the class of the JavaScriptObject
 */
public abstract class JsonConverter<S, T extends JavaScriptObject> {

	/**
	 * @return a javascript object from the JSON string
	 */
	public T getJSObject(String object) {
		return JsonUtils.safeEval(object).cast();
	}

	/**
	 * Converts a javascript object into a more specific javascript object
	 *
	 * @param object
	 *            the javascript object
	 * @return a new javascript object with the desired class
	 */
	public T getJSObject(JavaScriptObject object) {
		return object.cast();
	}

	/**
	 * Converts the object into json
	 *
	 * @param object
	 *            the object
	 * @return the JSON
	 */
	public abstract String getJson(S object);

	/**
	 * Converts the javascript object into the original object
	 *
	 * @param jsObject
	 *            the javascript object
	 * @return the original object
	 */
	public S getObject(T jsObject) {
		return null;
	}

}
