package es.eucm.gleaner.tracker.model.traces;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
/**
 * Represents a logic trace
 */
public class LogicTrace extends Trace {

	private String target;

	private Object value;

	private Object value2;

	private Object value3;

	public LogicTrace() {

	}

	public LogicTrace(String event, String target, Object value, Object value2,
			Object value3) {
		this.event = event;
		this.target = target;
		this.value = value;
		this.value2 = value2;
		this.value3 = value3;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getValue2() {
		return value2;
	}

	public void setValue2(Object value2) {
		this.value2 = value2;
	}

	public Object getValue3() {
		return value3;
	}

	public void setValue3(Object value3) {
		this.value3 = value3;
	}
}
