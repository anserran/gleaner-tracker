package es.eucm.gleaner.model.traces;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
/**
 * Represents a logic trace
 */
public class LogicTrace extends Trace {

	public static final String TYPE = "logic";

	private String type;

	private String event;

	private String target;

	private Object value1;

	private Object value2;

	private Object value3;

	public LogicTrace() {
		this.type = TYPE;
	}

	public LogicTrace(String event, String target, Object value1, Object value2, Object value3) {
		this();
		this.event = event;
		this.target = target;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
	}

	public String getType() {
		return type;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Object getValue1() {
		return value1;
	}

	public void setValue1(Object value1) {
		this.value1 = value1;
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
