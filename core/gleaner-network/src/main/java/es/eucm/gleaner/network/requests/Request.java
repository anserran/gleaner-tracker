package es.eucm.gleaner.network.requests;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a platform-independent http request
 */
public class Request {

	/**
	 * Uri for the request
	 */
	private String uri;

	/**
	 * Method for the request
	 */
	private String method;

	/**
	 * Headers of the request
	 */
	private Map<String, String> headers;

	/**
	 * Uri parameters of the request
	 */
	private Map<String, String> parameters;

	/**
	 * Data of the request
	 */
	private String entity;

	public Request() {
		headers = new HashMap<String, String>();
		parameters = new HashMap<String, String>();
	}

	public void setParameter(String name, String value) {
		parameters.put(name, value);
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public void setHeader(String name, String value) {
		this.headers.put(name, value);
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

}
