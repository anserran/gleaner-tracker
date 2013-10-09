package es.eucm.gleaner.tracker.data;

import com.google.gwt.core.client.JavaScriptObject;

import es.eucm.gleaner.model.traces.Trace;

public class AbstractTraceJS extends JavaScriptObject {

	protected AbstractTraceJS() {

	}

	public final void setRoot(Trace trace) {
		setTimeStamp((int) trace.getTimeStamp());
	}

	public native final void setTimeStamp(int timeStamp) /*-{
		this.timeStamp = timeStamp;
	}-*/;

	public native final int getTimeStamp() /*-{
		return this.timeStamp;
	}-*/;

}
