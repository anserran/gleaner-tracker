package es.eucm.gleaner.network.requests;

/**
 * Request callback that handles an object as response
 * 
 * @param <T>
 *            the type of the response object
 */
public interface ResourceCallback<T> {

	/**
	 * Triggered when the request produces an error
	 * 
	 * @param e
	 *            the error
	 */
	void error(Throwable e);

	/**
	 * Triggered when the request success
	 * 
	 * @param data
	 *            the data retrieved
	 */
	void success(T data);

}
