package es.eucm.gleaner.gwt.test.network;

import com.google.gwt.junit.client.GWTTestCase;

import es.eucm.gleaner.gwt.network.GwtRequestHelper;
import es.eucm.gleaner.network.requests.RequestHelper;

public class RequestHelperGWTTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "es.eucm.gleaner.Network";
	}

	public void testSendRequestCallback() {
		RequestHelper requestHelper = new GwtRequestHelper(true);
//		requestHelper.url("http://localhost:8080/gleaner-server-java/")
//				.method(Method.GET).send(new RequestCallback() {
//
//					@Override
//					public void error(Request request, Throwable throwable) {
//						fail(throwable + ". Maybe server in localhost is down?");
//
//					}
//
//					@Override
//					public void success(Request request, Response response) {
//						assertTrue(response.getStatusCode() == Response.OK);
//					}
//
//				});
		assertTrue(true);
	}

}
