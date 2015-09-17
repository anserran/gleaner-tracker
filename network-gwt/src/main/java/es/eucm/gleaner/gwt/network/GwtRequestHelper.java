package es.eucm.gleaner.gwt.network;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.user.client.rpc.AsyncCallback;
import es.eucm.gleaner.gwt.network.converters.JsonConverter;
import es.eucm.gleaner.network.ContentType;
import es.eucm.gleaner.network.Header;
import es.eucm.gleaner.network.Method;
import es.eucm.gleaner.network.requests.Request;
import es.eucm.gleaner.network.requests.RequestCallback;
import es.eucm.gleaner.network.requests.RequestHelper;
import es.eucm.gleaner.network.requests.ResourceCallback;
import es.eucm.gleaner.network.requests.Response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Implementation of an request helper for the gwt backend
 */
public class GwtRequestHelper extends RequestHelper {

	/**
	 * Map with the json converters
	 */
	private final Map<Class<?>, JsonConverter<?, ?>> converters = new HashMap<Class<?>, JsonConverter<?, ?>>();

	/**
	 * Adds a json converter
	 * 
	 * @param clazz
	 *            the clazz of the original object
	 * @param converter
	 *            the converter for the given class
	 */
	public void addConverter(Class<?> clazz, JsonConverter<?, ?> converter) {
		converters.put(clazz, converter);
	}

	@Override
	public void send(Request request, String uriWithParameters,
			RequestCallback callback) {
		send(request, uriWithParameters, new GeneralRequestCallback(request,
				callback));
	}

	@Override
	public <S, T> void getResource(Request request, String uriWithParameters,
			ResourceCallback<T> callback, Class<S> clazz, boolean isCollection) {
		request.setHeader(Header.ACCEPT, ContentType.APPLICATION_JSON);
		send(request, uriWithParameters, new ResourceRequestCallback<S, T>(
				callback, clazz, isCollection));
	}

	/**
	 * Sends the request (no cross domain)
	 * 
	 * @param request
	 *            the request
	 * @param uriWithParameters
	 *            the uri
	 * @param requestCallback
	 *            the callback
	 */
	private void send(Request request, String uriWithParameters,
			com.google.gwt.http.client.RequestCallback requestCallback) {
		RequestBuilder builder = new RequestBuilder(
				getMethod(request.getMethod()), uriWithParameters);

		for (Entry<String, String> header : request.getHeaders().entrySet()) {
			builder.setHeader(header.getKey(), header.getValue());
		}

		String data = request.getEntity();

		try {
			builder.sendRequest(data, requestCallback);
		} catch (RequestException e) {
			requestCallback.onError(null, e);
		}
	}

	@Override
	public String encode(String string, String charset) {
		return URL.encodeQueryString(string);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String toJson(Object element) {
		if (element instanceof Collection) {
			String data = "[";
			Collection c = (Collection) element;
			if (c.size() > 0) {
				for (Object o : c) {
					JsonConverter converter = converters.get(o.getClass());
					if (converter != null) {
						data += converter.getJson(o) + ",";
					}
				}
				data = data.substring(0, data.length() - 1);
			}
			return data + "]";

		} else {
			JsonConverter converter = converters.get(element.getClass());
			if (converter != null) {
				return converter.getJson(element);
			}
		}
		return element.toString();
	}

	private RequestBuilder.Method getMethod(String method) {
		if (method.equals(Method.GET)) {
			return RequestBuilder.GET;
		} else if (method.equals(Method.POST)) {
			return RequestBuilder.POST;
		} else if (method.equals(Method.PUT)) {
			return RequestBuilder.PUT;
		} else if (method.equals(Method.DELETE)) {
			return RequestBuilder.DELETE;
		} else if (method.equals(Method.HEAD)) {
			return RequestBuilder.HEAD;
		}
		return null;
	}

	public static class GeneralRequestCallback implements
			com.google.gwt.http.client.RequestCallback {

		private Request request;

		private RequestCallback requestCallback;

		public GeneralRequestCallback(Request request,
				RequestCallback requestCallback) {
			this.request = request;
			this.requestCallback = requestCallback;
		}

		@Override
		public void onResponseReceived(
				com.google.gwt.http.client.Request request,
				com.google.gwt.http.client.Response response) {
			if (requestCallback != null) {
				Response r = new Response();
				r.setStatusCode(response.getStatusCode());
				for (com.google.gwt.http.client.Header h : response
						.getHeaders()) {
					r.setHeader(h.getName(), h.getValue());
				}
				r.setContent(response.getText());
				requestCallback.success(this.request, r);
			}
		}

		@Override
		public void onError(com.google.gwt.http.client.Request request,
				Throwable exception) {
			if (requestCallback != null) {
				requestCallback.error(this.request, exception);
			}

		}
	}

	public class ResourceRequestCallback<S, T> implements
			com.google.gwt.http.client.RequestCallback {

		private ResourceCallback<T> callback;

		private Class<?> clazz;

		private boolean isCollection;

		public ResourceRequestCallback(ResourceCallback<T> callback,
				Class<S> clazz, boolean isCollection) {
			this.callback = callback;
			this.clazz = clazz;
			this.isCollection = isCollection;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public void onResponseReceived(
				com.google.gwt.http.client.Request request,
				com.google.gwt.http.client.Response response) {

			if (callback != null) {
				JsonConverter converter = converters.get(clazz);
				if (response.getStatusCode() == Response.OK
						&& converter != null) {
					Object o;
					if (isCollection) {
						Collection<S> collection = new ArrayList<S>();
						JavaScriptObject jsObject = JsonUtils.safeEval(response
								.getText());
						JSONArray array = new JSONArray(jsObject);

						for (int i = 0; i < array.size(); i++) {
							S element = (S) converter.getObject(converter
									.getJSObject(array.get(i).toString()));
							if (element != null) {
								collection.add(element);
							}
						}
						o = collection;
					} else {
						o = converter.getObject(converter.getJSObject(response
								.getText()));
					}
					if (o != null) {
						callback.success((T) o);
					}
				}
			}

		}

		@Override
		public void onError(com.google.gwt.http.client.Request request,
				Throwable exception) {
			if (callback != null) {
				callback.error(exception);
			}
		}

	}

	public class RequestAsyncCallback<S, T> implements
			AsyncCallback<JavaScriptObject> {

		private Class<S> clazz;

		private boolean isCollection;

		private ResourceCallback<T> callback;

		public RequestAsyncCallback(ResourceCallback<T> callback,
				Class<S> clazz, boolean isCollection) {
			super();
			this.clazz = clazz;
			this.isCollection = isCollection;
			this.callback = callback;
		}

		@Override
		public void onFailure(Throwable caught) {
			if (callback != null) {
				callback.error(caught);
			}
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public void onSuccess(JavaScriptObject result) {
			Object o = null;
			JsonConverter converter = converters.get(clazz);
			if (converter != null) {
				if (isCollection) {
					JSONArray array = new JSONArray(result);
					Collection<S> c = new ArrayList<S>();
					for (int i = 0; i < array.size(); i++) {
						JavaScriptObject jsObject = JsonUtils.safeEval(array
								.get(i).toString());
						S jo = (S) converter.getObject(jsObject);
						if (jo != null) {
							c.add(jo);
						}
					}
					o = c;
				} else {
					o = converter.getObject(result.cast());
				}
			}
			try {
				callback.success((T) o);
			} catch (ClassCastException e) {
				callback.error(e);
			}

		}
	}
}
