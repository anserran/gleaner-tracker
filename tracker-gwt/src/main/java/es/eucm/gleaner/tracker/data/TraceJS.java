package es.eucm.gleaner.tracker.data;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayBoolean;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.core.client.JsArrayString;
import es.eucm.gleaner.tracker.model.traces.Trace;

public class TraceJS extends JavaScriptObject {

	protected TraceJS() {
	}

	public final void set(Trace trace) {
		if (trace.getEvent() != null) {
			setEvent(trace.getEvent());
		}
		if (trace.getTarget() != null) {
			setTarget(trace.getTarget());
		}

		Object value = trace.getValue();
		if (value != null) {
			if (value instanceof Integer) {
				setValueInt((Integer) value);
			} else if (value instanceof Float) {
				setValueFloat((Float) trace.getValue());
			} else if (value.getClass().isArray()) {
				setValue(getJsArray(value));
			} else {
				setValue(value);
			}
		}
	}

	private JavaScriptObject getJsArray(Object value) {
		if (value instanceof int[]) {
			JsArrayInteger jsArray = JsArrayInteger.createArray().cast();
			for (int i : (int[]) value) {
				jsArray.push(i);
			}
			return jsArray;
		} else if (value instanceof String[]) {
			JsArrayString jsArray = JsArrayString.createArray().cast();
			for (String s : (String[]) value) {
				jsArray.push(s);
			}
			return jsArray;
		} else if (value instanceof float[]) {
			JsArrayNumber jsArray = JsArrayNumber.createArray().cast();
			for (float f : (float[]) value) {
				jsArray.push(f);
			}
			return jsArray;
		} else if (value instanceof boolean[]) {
			JsArrayBoolean jsArray = JsArrayBoolean.createArray().cast();
			for (boolean b : (boolean[]) value) {
				jsArray.push(b);
			}
			return jsArray;
		} else {
			return null;
		}
	}

	public final native void setEvent(String event) /*-{
													this.event = event;
													}-*/;

	public final native void setTarget(String target) /*-{
														this.target = target;
														}-*/;

	public final native void setValue(Object value) /*-{
														this.value = value;
														}-*/;

	public final native void setValueInt(int value) /*-{
														this.value = value;
														}-*/;

	public final native void setValueFloat(float value) /*-{
															this.value = value;
															}-*/;
}
