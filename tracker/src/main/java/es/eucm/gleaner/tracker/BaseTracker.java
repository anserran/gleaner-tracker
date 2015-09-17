package es.eucm.gleaner.tracker;

import es.eucm.gleaner.network.Header;
import es.eucm.gleaner.network.requests.Request;
import es.eucm.gleaner.network.requests.RequestCallback;
import es.eucm.gleaner.network.requests.RequestHelper;
import es.eucm.gleaner.network.requests.ResourceCallback;
import es.eucm.gleaner.network.requests.Response;
import es.eucm.gleaner.tracker.converter.TracesConverter;
import es.eucm.gleaner.tracker.converter.XAPITracesConverter;
import es.eucm.gleaner.tracker.model.RestAPI;
import es.eucm.gleaner.tracker.model.TrackData;
import es.eucm.gleaner.tracker.model.traces.Events;
import es.eucm.gleaner.tracker.model.traces.Trace;

import java.util.ArrayList;

public class BaseTracker implements ResourceCallback<TrackData>, RequestCallback {

	public static final int RETRIES = 10;

	/**
	 * Request helper
	 */
	protected final RequestHelper requestHelper;

	/**
	 * Traces
	 */
	private final TracesQueue traces;

	/**
	 * Current server uri
	 */
	private String serverUri;

	private String trackingCode;

	/**
	 * Track data
	 */
	private TrackData trackData;

	/**
	 * If the tracker currently tracking
	 */
	private boolean tracking;

	/**
	 * Number of erroneous communications with the server
	 */
	private int errors;

	/**
	 * Bearer token
	 */
	private String authorization;

	/**
	 * Number of retries
	 */
	private int retry;

	/**
	 * If the we are waiting to a response from the server
	 */
	private boolean connecting;

	private ArrayList<ConnectionListener> connectionListeners;

	/**
	 * Creates a Tracker with an {@link XAPITracesConverter} that sends xAPI
	 * statements.
	 * 
	 * @param requestHelper
	 */
	public BaseTracker(RequestHelper requestHelper) {
		this(requestHelper, new XAPITracesConverter());
	}

	/**
	 * 
	 * @param requestHelper
	 * @param converter
	 *            if null traces will be sent in raw format.
	 */
	public BaseTracker(RequestHelper requestHelper, TracesConverter converter) {
		this.requestHelper = requestHelper;
		this.traces = new TracesQueue(requestHelper, this, converter);
		this.connectionListeners = new ArrayList<ConnectionListener>();
		this.errors = 0;
		this.tracking = true;
		this.connecting = false;
	}

	public void addConnectionListener(ConnectionListener listener) {
		connectionListeners.add(listener);
	}

	/**
	 * Set the URL where Gleaner is listening
	 * 
	 * @param url
	 */
	public void setServerURL(String url) {
		this.serverUri = url;
	}

	/**
	 * @return Returns the URL from the Gleaner server
	 */
	public String getServerURL() {
		return serverUri;
	}

	/**
	 * Returns a request helper. It can be used to query traces already sent to
	 * the server
	 * 
	 * @return
	 */
	public RequestHelper getRequestHelper() {
		return requestHelper;
	}

	/**
	 * Returns track data
	 * 
	 * @return track data
	 */
	public TrackData getTrackData() {
		return trackData;
	}

	public String getAuthorization() {
		return authorization;
	}

	/**
	 * Sets the authorization header to be sent when the tracker starts the
	 * tracking
	 * 
	 * @param authorization
	 *            the auth token
	 */
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	/**
	 * @return Returns the input traces queue
	 */
	public TracesQueue getTracesQueue() {
		return traces;
	}

	/**
	 * Asks the server to track the game given by tracking code
	 */
	public void startTracking(String trackingCode) {
		this.trackData = null;
		this.trackingCode = trackingCode;
		traces.clear();
		connect();
	}

	/**
	 * Connects to the server
	 */
	private void connect() {
		if (!connecting) {
			connecting = true;
			String trackUrl = serverUri + RestAPI.START + trackingCode;
			RequestHelper.Builder reqBuilder = requestHelper.url(trackUrl);
			String auth = getAuthorization();
			if (auth != null && !auth.isEmpty()) {
				reqBuilder.header(Header.AUTHORIZATION, auth);
			}
			reqBuilder.post(this, TrackData.class, false);
		}
	}

	/**
	 * @return Returns true if the tracker is ready to send traces (connected)
	 */
	public boolean isReady() {
		return trackData != null;
	}

	/**
	 * Tracks a trace
	 * 
	 * @param trace
	 *            the trace
	 */
	public <T extends Trace> void track(T trace) {
		if (!isReady() && retry > 0) {
			retry--;
			if (retry == 0) {
				connect();
			}
		}
		if (tracking) {
			traces.add(trace);
		}
	}

	/**
	 * Tracks a logic event
	 * 
	 * @param event
	 *            the event identifier. You can use some from {@link Events}
	 * @param target
	 *            the target for the event (e.g. the phase in which happens)
	 * @param value
	 *            some data for the event
	 */
	public void trace(String event, String target, Object value) {
		track(new Trace(event, target, value));
	}

	/**
	 * Logs that the game starts
	 */
	public void gameStart() {
		trace(Events.GAME_START, null, null);
	}

	/**
	 * Logs that the game ends
	 */
	public void gameEnd() {
		trace(Events.GAME_END, null, null);
	}

	/**
	 * Logs that a phase starts
	 * 
	 * @param phaseName
	 */
	public void phaseStart(String phaseName) {
		trace(Events.PHASE_START, phaseName, null);
	}

	/**
	 * Logs a variable update
	 * 
	 * @param varName
	 *            variable name
	 * @param newValue
	 *            the new value for the variable
	 */
	public void varUpdate(String varName, Object newValue) {
		trace(Events.VAR_UPDATE, varName, newValue);
	}

	/**
	 * Logs a death
	 * 
	 * @param context
	 *            context of the death
	 * @param identifier
	 *            identifier of the element that "died"
	 * @param x
	 *            x coordinate of the death
	 * @param y
	 *            y coordinate of the death
	 */
	public void death(String context, String identifier, Float x, Float y) {
		trace(Events.DEATH, context, identifier);
	}

	public void score(String context, int score) {
		trace(Events.SCORE, context, score);
	}

	/**
	 * Player has entered in a new zone
	 * 
	 * @param zoneId
	 *            the zone id
	 */
	public void zone(String zoneId) {
		trace(Events.ZONE, null, zoneId);
	}

	public void var(String varName, Object value) {
		trace(Events.VAR_UPDATE, varName, value);
	}

	public void choice(String choiceId, String choiceOption) {
		trace(Events.CHOICE, choiceId, choiceOption);
	}

	public void startGameplay() {
		trace(Events.START, null, null);
	}

	public void interact(String target) {
		interact(target, null);
	}

	public void interact(String target, String value) {
		trace("interact", target, value);
	}

	/**
	 * Stops tracking
	 */
	public void stopTracking() {
		tracking = false;
		flush();
	}

	/**
	 * Force to send out the traces stored in the local cache
	 */
	public void flush() {
		if (!isReady()) {
			connect();
		}
		traces.flush();
	}

	/**
	 * @return Returns true whether there are traces pending to be sent to the
	 *         server
	 */
	public boolean isDone() {
		return traces.isDone();
	}

	// Handlers for start tracking
	@Override
	public void success(TrackData trackData) {
		connecting = false;
		this.trackData = trackData;
		this.traces.setTrackData(trackData);
		this.traces.setUrl(this.serverUri + RestAPI.TRACK);
		errors = 0;
		for (ConnectionListener connectionListener : connectionListeners) {
			connectionListener.connected(trackData);
		}
	}

	@Override
	public void error(Throwable errorMessage) {
		connecting = false;
		errors++;
		retry = RETRIES * errors;
	}

	// Handlers for queues requests

	@Override
	public void success(Request request, Response response) {
		if (response.getStatusCode() == Response.OK
				|| response.getStatusCode() == Response.NO_CONTENT) {
			errors = 0;
		} else {
			errors++;
		}
	}

	@Override
	public void error(Request request, Throwable throwable) {
		errors++;
	}

	public void setMaxTracesPerQueue(int maxTraces) {
		traces.setMaxSize(maxTraces);
	}

	public interface ConnectionListener {

		void connected(TrackData trackData);

	}

}
