package es.eucm.gleaner.network;

public interface Header {
	String CONTENT_TYPE = "Content-type";
	String ACCEPT = "Accept";
	/**
	 * Used as a first layer of authorization and authentication by the
	 * users-module. Used to send the JWT token. This is needed in case the
	 * tracker has logged into the system and received the JWT token. In order
	 * for the tracker to see its profile data it must be identified (logged in)
	 * when sending data to the collector server.
	 */
	String AUTHORIZATION = "Authorization";

	/**
	 * Used for the second layer of authentication (gleaner). In order to send
	 * traces to the collector server (gleaner) you must send an authorization
	 * token in every request. This authentication token is sent by the server
	 * when we issue a 'collector/start' request.
	 */
	String AUTHORIZATION2 = "Authorization2";
}
