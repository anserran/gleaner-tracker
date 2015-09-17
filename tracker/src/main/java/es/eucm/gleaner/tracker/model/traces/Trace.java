package es.eucm.gleaner.tracker.model.traces;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Basic trace
 */
@XmlRootElement
public class Trace {

	/**
	 * The category of the trace
	 */
	protected String event;

	private String target;

	private Object value;

	public Trace() {
	}

	public Trace(String event, String target, Object value) {
		this.event = event;
		this.target = target;
		this.value = value;
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

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
