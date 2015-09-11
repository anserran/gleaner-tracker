package es.eucm.gleaner.tracker.converter;

import es.eucm.gleaner.tracker.model.traces.Trace;
import es.eucm.gleaner.tracker.model.traces.xapi.XAPIObject;

import java.util.List;

/**
 * Transforms {@link Trace}s into other type of objects, depending on the
 * implementation. For instance {@see XAPITraTracesConverter}.
 */
public interface TracesConverter {

	List<XAPIObject> convert(List<Trace> traces, String playerName);
}
