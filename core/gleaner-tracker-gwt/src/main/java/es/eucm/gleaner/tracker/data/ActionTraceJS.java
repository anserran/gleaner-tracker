package es.eucm.gleaner.tracker.data;

import es.eucm.gleaner.model.traces.InputTrace;

public class ActionTraceJS extends AbstractTraceJS {

	protected ActionTraceJS() {

	}

	public final void set(InputTrace trace) {
		super.setRoot(trace);
		setDevice(trace.getDevice());
		setAction(trace.getAction());
		setButton(trace.getButton());
		setValue1(trace.getValue1());
		setValue2(trace.getValue2());
		setExtra(trace.getExtra());
		setTarget(trace.getTarget());
	}

	public native final void setDevice(int device) /*-{
		this.device = device;
	}-*/;

	public native final void setAction(int action) /*-{
		this.action = action;
	}-*/;

	public native final void setButton(int button) /*-{
		this.button = button;
	}-*/;

	public native final void setValue1(int value1) /*-{
		this.value1 = value1;
	}-*/;

	public native final void setValue2(int value2) /*-{
		this.value2 = value2;
	}-*/;

	public native final void setExtra(String extra) /*-{
		this.extra = extra;
	}-*/;

	public native final void setTarget(String target) /*-{
		this.target = target;
	}-*/;

}
