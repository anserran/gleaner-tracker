package es.eucm.gleaner.network.test;

import es.eucm.gleaner.java.network.JavaRequestHelper;
import es.eucm.gleaner.network.requests.RequestHelper;

public class JavaRequestBuilderTest extends RequestHelperTest {

	@Override
	public RequestHelper getRequestHelper() {
		return new JavaRequestHelper( );
	}

}
