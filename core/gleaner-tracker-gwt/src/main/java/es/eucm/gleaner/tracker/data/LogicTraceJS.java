package es.eucm.gleaner.tracker.data;

import es.eucm.gleaner.model.traces.LogicTrace;

public class LogicTraceJS extends AbstractTraceJS {

	protected LogicTraceJS() {

	}

	public final void set(LogicTrace trace) {
		if (trace.getEvent() != null) {
			setEvent(trace.getEvent());
		}
		if (trace.getTarget() != null) {
			setTarget(trace.getTarget());
		}
		if (trace.getValue1() != null) {
			if (trace.getValue1() instanceof Integer) {
				setValue1Int((Integer) trace.getValue1());
			} else if (trace.getValue1() instanceof Float) {
				setValue1Float((Float) trace.getValue1());
			} else {
				setValue1(trace.getValue1());
			}
		}
		if (trace.getValue2() != null) {
			if (trace.getValue2() instanceof Integer) {
				setValue2Int((Integer) trace.getValue2());
			} else if (trace.getValue2() instanceof Float) {
				setValue2Float((Float) trace.getValue2());
			} else {
				setValue2(trace.getValue2());
			}
		}
		if (trace.getValue3() != null) {
			if (trace.getValue3() instanceof Integer) {
				setValue3Int((Integer) trace.getValue3());
			} else if (trace.getValue3() instanceof Float) {
				setValue3Float((Float) trace.getValue3());
			} else {
				setValue3(trace.getValue3());
			}
		}
	}

	public final native void setType(String type) /*-{
													this.type = type;
													}-*/;

	public final native void setEvent(String event) /*-{
													this.event = event;
													}-*/;

	public final native void setTarget(String target) /*-{
														this.target = target;
														}-*/;

	public final native void setValue1(Object value1) /*-{
														this.value1 = value1;
														}-*/;

	public final native void setValue1Int(int value1) /*-{
														this.value1 = value1;
														}-*/;

	public final native void setValue1Float(float value1) /*-{
															this.value1 = value1;
															}-*/;

	public final native void setValue2(Object value2) /*-{
														this.value2 = value2;
														}-*/;

	public final native void setValue2Int(int value2) /*-{
														this.value2 = value2;
														}-*/;

	public final native void setValue2Float(float value2) /*-{
															this.value2 = value2;
															}-*/;

	public final native void setValue3(Object value3) /*-{
														this.value3 = value3;
														}-*/;

	public final native void setValue3Int(int value3) /*-{
														this.value3 = value3;
														}-*/;

	public final native void setValue3Float(float value3) /*-{
															this.value3 = value3;
															}-*/;

	public final LogicTrace getLogicTrace() {
		return new LogicTrace();
	}
}
