package es.eucm.gleaner.network.test;

import es.eucm.gleaner.network.requests.RequestHelper;
import junit.framework.TestCase;

public abstract class RequestHelperTest extends TestCase {

	private RequestHelper requestHelper;

	public abstract RequestHelper getRequestHelper();

	public void setUp() {
		requestHelper = getRequestHelper();
	}

	public void testSendRequestCallback() {
		/*requestHelper.url("http://localhost:8080/gleaner-server-java/")
				.method(Method.GET).send(new RequestCallback() {

					@Override
					public void error(Request request, Throwable throwable) {
						fail(throwable + ". Maybe server in localhost is down?");

					}

					@Override
					public void success(Request request, Response response) {
						assertTrue(response.getStatusCode() == Response.OK);
					}

				});*/
	}
}
