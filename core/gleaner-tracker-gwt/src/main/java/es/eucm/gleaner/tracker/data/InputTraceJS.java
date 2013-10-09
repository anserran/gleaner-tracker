package es.eucm.gleaner.tracker.data;

import es.eucm.gleaner.model.traces.InputTrace;

public class InputTraceJS extends AbstractTraceJS {

	protected InputTraceJS() {

	}

	public final void set(InputTrace trace) {
		super.setRoot(trace);
		setType(trace.getType());
		if (trace.getContext() != null) setContext(trace.getContext());
		if (trace.getDevice() != null) setDevice(trace.getDevice());
		if (trace.getAction() != null) setAction(trace.getAction());
		if (trace.getButton() != null) setButton(trace.getButton());
		if (trace.getValue1() != null) setValue1(trace.getValue1());
		if (trace.getValue2() != null) setValue2(trace.getValue2());
		if (trace.getValue3() != null) setValue3(trace.getValue3());
		if (trace.getExtra() != null) setExtra(trace.getExtra());
		if (trace.getTarget() != null) setTarget(trace.getTarget());
	}

	public native final void setType(String type)/*-{
		this.type = type;
	}-*/;

	public native final void setContext(String context)/*-{
		this.context = context;
	}-*/;

	public native final void setDevice(String device) /*-{
		this.device = device;
	}-*/;

	public native final void setAction(String action) /*-{
		this.action = action;
	}-*/;

	public native final void setButton(int button) /*-{
		this.button = button;
	}-*/;

	public native final void setValue1(float value1) /*-{
		this.value1 = value1;
	}-*/;

	public native final void setValue2(float value2) /*-{
		this.value2 = value2;
	}-*/;

	public native final void setValue3(float value3) /*-{
		this.value3 = value3;
	}-*/;

	public native final void setExtra(String extra) /*-{
		this.extra = extra;
	}-*/;

	public native final void setTarget(String target) /*-{
		this.target = target;
	}-*/;

}
