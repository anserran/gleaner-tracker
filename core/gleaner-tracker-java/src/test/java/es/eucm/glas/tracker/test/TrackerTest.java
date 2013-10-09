package es.eucm.glas.tracker.test;

import es.eucm.gleaner.tracker.JerseyTracker;
import es.eucm.gleaner.tracker.Tracker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.URL;

import static junit.framework.Assert.assertNotNull;
import static org.powermock.api.easymock.PowerMock.createMock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(JerseyTracker.class)
public class TrackerTest {
	Tracker tracker;
	@Before
	public void setUp() {
		URL url = createMock(URL.class);
		tracker = new JerseyTracker();
	}

	@Test
	public void testStart(){

		tracker.setServerURL("gleaner");
		tracker.startTracking("000");

		assertNotNull(tracker.getTrackData());
	}
}
