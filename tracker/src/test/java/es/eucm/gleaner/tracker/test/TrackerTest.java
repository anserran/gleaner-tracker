package es.eucm.gleaner.tracker.test;

import es.eucm.gleaner.tracker.model.TrackData;
import es.eucm.gleaner.network.requests.Response;
import es.eucm.gleaner.tracker.BaseTracker;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TrackerTest {

	int maxTraces = 20;

	private BaseTracker tracker;

	private FakeRequestHelper requestHelper;

	@Before
	public void setUp() {
		requestHelper = new FakeRequestHelper();
		tracker = new BaseTracker(requestHelper);
	}

	@Test
	public void testStart() {
		tracker.setServerURL("http://testserver");

		requestHelper.setState(FakeRequestHelper.NORMAL);
		requestHelper.setResource(new TrackData("abc"));
		tracker.startTracking("000");

		assertEquals(tracker.getTrackData().getAuthToken(), "abc");

		requestHelper.setState(FakeRequestHelper.BUSY);
		tracker.startTracking("000");
		assertNull(tracker.getTrackData());
	}

	@Test
	public void testSendTraces() {
		tracker.setServerURL("http://testserver");

		Response r = new Response();
		r.setStatusCode(200);
		requestHelper.setState(FakeRequestHelper.NORMAL);
		requestHelper.setResponse(r);
		requestHelper.setResource(new TrackData("abc"));

		tracker.startTracking("000");
		assertEquals(tracker.getTrackData().getAuthToken(), "abc");

		// Send no trace
		tracker.flush();
		assertEquals(requestHelper.getTraces(), 0);
		assertEquals(requestHelper.getPosts(), 0);

		// Send one trace
		tracker.gameStart();
		tracker.flush();
		assertTrue(tracker.isDone());
		assertEquals(requestHelper.getTraces(), 1);
		assertEquals(requestHelper.getPosts(), 1);
		assertEquals(tracker.getTracesQueue().size(), 0);

		int totalTraces = 0;
		requestHelper.reset();

		for (int i = 0; i < maxTraces; i++) {
			tracker.gameStart();
			totalTraces++;
		}
		assertEquals(tracker.getTracesQueue().size(), maxTraces);

		tracker.gameStart();
		totalTraces++;

		assertEquals(tracker.getTracesQueue().size(), maxTraces + 1);
		assertEquals(requestHelper.getTraces(), 0);
		assertEquals(requestHelper.getPosts(), 0);
		tracker.flush();
		assertEquals(tracker.getTracesQueue().size(), 0);
		assertEquals(requestHelper.getTraces(), totalTraces);

		tracker.setMaxTracesPerQueue(maxTraces);
		for (int i = 0; i < maxTraces - 1; i++) {
			tracker.trace("", "", new int[]{0, 1});
			totalTraces++;
		}
		assertEquals(tracker.getTracesQueue().size(), maxTraces - 1);
		tracker.trace("", "", new int[]{0, 1});
		totalTraces++;
		assertEquals(tracker.getTracesQueue().size(), 0);

		assertEquals(requestHelper.getTraces(), totalTraces);
		assertEquals(requestHelper.getPosts(), 2);
		assertTrue(tracker.isDone());
	}

	@Test
	public void testSendTracesError() {
		tracker.setServerURL("http://testserver");

		Response r = new Response();
		r.setStatusCode(200);
		requestHelper.reset();
		requestHelper.setState(FakeRequestHelper.BUSY);
		requestHelper.setResponse(r);
		requestHelper.setResource(new TrackData("abc"));

		tracker.startTracking("000");
		int totalTraces = 0;

		for (int i = 0; i < maxTraces * 25; i++) {
			tracker.gameStart();
			totalTraces++;
		}
		assertEquals(tracker.getTracesQueue().size(), maxTraces * 25);
		tracker.gameStart();
		totalTraces++;
		tracker.flush();
		assertEquals(requestHelper.getPosts(), 0);
		assertEquals(requestHelper.getTraces(), 0);
		requestHelper.setState(FakeRequestHelper.NORMAL);
		tracker.flush();
		assertEquals(requestHelper.getPosts(), 1);
		assertEquals(requestHelper.getTraces(), totalTraces);
		assertEquals(tracker.getTrackData().getAuthToken(), "abc");
		assertEquals(tracker.getTracesQueue().size(), 0);

		tracker.setMaxTracesPerQueue(10);

		for (int i = 0; i < 1000; i++) {
			tracker.gameStart();
			requestHelper
					.setState(Math.random() > 0.5f ? FakeRequestHelper.BUSY
							: FakeRequestHelper.NORMAL);
			totalTraces++;
		}
		requestHelper.setState(FakeRequestHelper.NORMAL);
		tracker.flush();

		assertEquals(requestHelper.getTraces(), totalTraces);
	}
}
