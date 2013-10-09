package es.eucm.gleaner.test.model;

import junit.framework.TestCase;
import es.eucm.gleaner.model.queries.QueryResult;
import es.eucm.gleaner.model.queries.QueryRow;

public class ModelTest extends TestCase {

	public void testQueryColumn() {
		QueryRow qc1 = new QueryRow();
		assertEquals(qc1, qc1);
		qc1.put("test", "value");
		assertEquals(qc1, qc1);

		QueryRow qc2 = new QueryRow();
		qc2.put("test", "value1");
		assertFalse(qc1.equals(qc2));
		qc2.put("test", "value");
		assertEquals(qc1, qc2);

	}

	public void testQueryResult() {
		QueryRow qc1 = new QueryRow();
		qc1.put("test", "value");
		qc1.put("test2", 1);
		qc1.put("test3", 2.0f);

		QueryRow qc2 = new QueryRow();
		qc2.put("test4", "value");
		qc2.put("test5", 1);
		qc2.put("test6", 2.0f);

		QueryResult qr1 = new QueryResult();
		qr1.setRows(new QueryRow[] { qc1, qc2 });

		QueryResult qr2 = new QueryResult();
		qr2.setRows(new QueryRow[] { qc1, qc2 });

		assertEquals(qr1, qr1);
		assertEquals(qr1, qr2);
		qr2.setRows(new QueryRow[] { qc1, qc2, qc1 });
		assertFalse(qr1.equals(qr2));
	}

}
