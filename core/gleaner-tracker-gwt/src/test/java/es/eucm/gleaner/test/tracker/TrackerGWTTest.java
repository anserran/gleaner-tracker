package es.eucm.gleaner.test.tracker;

import com.google.gwt.junit.client.GWTTestCase;

import es.eucm.gleaner.tracker.GwtTracker;

public class TrackerGWTTest extends GWTTestCase {

	private Tracker tracker;

	@Override
	protected void gwtSetUp() throws Exception {
		tracker = new GwtTracker(true);
		super.gwtSetUp();
	}

	@Override
	public String getModuleName() {
		return "es.eucm.gleaner.TrackerTest";
	}

	public void testTracker() {
		tracker = new GwtTracker(true);
		tracker.setServerURL("http://localhost:8080/gleaner-server-java/");
		tracker.startTracking("000");
	}

	public void testGet() {
		/*tracker.getRequestHelper()
				.url("http://localhost:8080/gleaner-server-java/"
						+ RestAPI.getLogicTracesUri(2))
				.get(new ResourceCallback<Collection<LogicTrace>>() {

					@Override
					public void error(Throwable e) {
						fail(e + "");

					}

					@Override
					public void success(Collection<LogicTrace> data) {
						assertNotNull(data);
						assertTrue(data.size() > 0);
					}
				}, LogicTrace.class, true);*/
	}

	public void testGetJson() {
		/*LogicTrace trace = new LogicTrace();
		trace.setType("test");
		trace.setValue(1.0f + "");
		trace.setTimeStamp(14);
		trace.setId(5);
		trace.setType("type");

		LogicTraceJS traceJs = LogicTraceJS.createObject().cast();
		traceJs.set(trace);

		Collection<LogicTrace> traces = new ArrayList<LogicTrace>();

		RequestHelper helper = tracker.getRequestHelper();

		assertEquals("[]", helper.getJsonData(traces));

		JSONArray array = new JSONArray();
		for (int i = 0; i < 10; i++) {
			array.set(i, new JSONObject(traceJs));
			traces.add(trace);
			assertEquals(array.toString(), helper.getJsonData(traces));
		}*/

	}

}
