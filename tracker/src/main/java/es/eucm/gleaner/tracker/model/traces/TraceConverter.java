package es.eucm.gleaner.tracker.model.traces;

/**
 * Converts a {@link Trace} into other format.
 */
public interface TraceConverter<T> {
	T convert(Trace trace);
}
