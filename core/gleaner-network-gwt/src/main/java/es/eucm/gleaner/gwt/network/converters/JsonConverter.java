package es.eucm.gleaner.gwt.network.converters;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Converts and object into a JavaScriptObject
 * 
 * @param <S>
 *            the class of the original object
 * @param <T>
 *            the class of the JavaScriptObject
 */
public interface JsonConverter<S, T extends JavaScriptObject> {

	/**
	 * Converts the object into json
	 * 
	 * @param object
	 *            the object
	 * @return the JSON
	 */
	String getJson(S object);

	/**
	 * Converts the javascript object into the original object
	 * 
	 * @param jsObject
	 *            the javascript object
	 * @return the original object
	 */
	S getObject(T jsObject);

	/**
	 * Converts a JSON string into a javascript object
	 * 
	 * @param string
	 *            the JSON strig
	 * @return
	 */
	T getJSObject(String string);

	/**
	 * Converts a javascript object into a more specific javascript object
	 * 
	 * @param object
	 *            the javascript object
	 * @return a new javascript object with the desired class
	 */
	T getJSObject(JavaScriptObject object);

}
