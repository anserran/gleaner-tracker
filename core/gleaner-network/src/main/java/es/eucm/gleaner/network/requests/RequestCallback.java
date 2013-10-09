package es.eucm.gleaner.network.requests;

/**
 * Interface for a request callback, with error and success methods
 */
public interface RequestCallback {

	/**
	 * Triggered when the request produces an error
	 *
	 * @param request   the original request
	 * @param throwable the error
	 */
	void error(Request request, Throwable throwable);

	/**
	 * Triggered when the request success
	 *
	 * @param request  the original request
	 * @param response the response to te request
	 */
	void success(Request request, Response response);

}
