package es.eucm.gleaner.tracker.test;

import com.google.gson.Gson;
import es.eucm.gleaner.network.requests.*;

import java.util.ArrayList;

public class FakeRequestHelper extends RequestHelper {

	private Gson gson;

	private int state;

	public static final int NORMAL = 1, BUSY = 0;

	private Response response;

	private Object resource;
	private int posts;
	private int traces;

	public FakeRequestHelper() {
		gson = new Gson();
	}

	@Override
	public void send(Request request, String uriWithParameters,
			RequestCallback callback) {
		switch (state) {
		case NORMAL:
			Object o = gson.fromJson(request.getEntity(), Object.class);
			if (o instanceof ArrayList) {
				traces += ((ArrayList) o).size();
			}
			callback.success(request, response);
			posts++;
			break;
		case BUSY:
			callback.error(request, new Error());
			break;
		}
	}

	@Override
	public <S, T> void get(Request request, String uriWithParameters,
			ResourceCallback<T> callback, Class<S> clazz, boolean isCollection) {
		switch (state) {
		case NORMAL:
			callback.success((T) resource);
			break;
		case BUSY:
			callback.error(new Error());
			break;
		}
	}

	@Override
	public String encode(String string, String charset) {
		return string;
	}

	@Override
	public String getJsonData(Object element) {
		return gson.toJson(element);
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public void setResource(Object resource) {
		this.resource = resource;
	}

	public int getPosts() {
		return posts;
	}

	public int getTraces() {
		return traces;
	}

	public void reset() {
		posts = 0;
		traces = 0;
	}
}
