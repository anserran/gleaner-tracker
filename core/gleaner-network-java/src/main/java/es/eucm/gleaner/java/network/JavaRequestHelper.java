package es.eucm.gleaner.java.network;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import es.eucm.gleaner.network.*;
import es.eucm.gleaner.network.requests.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

public class JavaRequestHelper extends RequestHelper {

	private static Client client = Client.create();

	private static Gson gson = new Gson();

	private static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	public JavaRequestHelper() {
		super();
	}

	@Override
	public void send(Request request, String uriWithParameters,
			RequestCallback callback) {
		HttpURLConnection conn = null;
		BufferedReader stream = null;

		try {
			// Setting the request
			URL url = new URL(uriWithParameters);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(request.getMethod());
			for (Entry<String, String> header : request.getHeaders().entrySet()) {
				conn.setRequestProperty(header.getKey(), header.getValue());
			}

			if (request.getEntity() != null) {
				String data = request.getEntity();
				conn.getOutputStream().write(data.getBytes(CharSet.UTF8));
			}

			// Processing the response
			Response response = new Response();
			response.setStatusCode(conn.getResponseCode());

			for (Entry<String, List<String>> header : conn.getHeaderFields()
					.entrySet()) {
				response.setHeader(header.getKey(), header.getValue().get(0));
			}

			String contentType = conn.getHeaderField(Header.CONTENT_TYPE);
			String charset = null;
			if (contentType != null) {
				for (String param : contentType.replace(" ", "").split(";")) {
					if (param.startsWith("charset=")) {
						charset = param.split("=", 2)[1];
						break;
					}
				}
			}

			if (charset == null) {
				charset = CharSet.UTF8;
			}

			stream = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), charset));

			if (stream != null) {
				String data = null;
				for (String line; (line = stream.readLine()) != null;) {
					data += line + LINE_SEPARATOR;
				}
				response.setContent(data);
			}
			if (callback != null)
				callback.success(request, response);

		} catch (Exception e) {
			if (callback != null)
				callback.error(request, e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}

			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public <S, T> void get(Request request, String uriWithParameters,
			ResourceCallback<T> callback, Class<S> clazz, boolean isCollection) {
		WebResource r = client.resource(uriWithParameters);
		WebResource.Builder b = r.accept(ContentType.APPLICATION_JSON);
		for (Entry<String, String> h : request.getHeaders().entrySet()) {
			b.header(h.getKey(), h.getValue());
		}
		try {
			Object data = null;
			if (isCollection) {
				Collection<S> list = new ArrayList<S>();
				String s = b.get(String.class);
				Collection<LinkedHashMap<String, Object>> strings = new ArrayList<LinkedHashMap<String, Object>>();
				strings = gson.fromJson(s, strings.getClass());
				for (LinkedHashMap<String, Object> a : strings) {
					S object = gson.fromJson(a.toString(), clazz);
					if (object != null) {
						list.add(object);
					}
				}
				data = list;
			} else {
				data = b.get(clazz);
			}

			callback.success((T) data);
		} catch (Exception e) {
			callback.error(e);
		}

	}

	@Override
	public String encode(String string, String charset) {
		try {
			return URLEncoder.encode(string, charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return string;
		}
	}

	public String getJsonData(Object element) {
		return gson.toJson(element);
	}

}
