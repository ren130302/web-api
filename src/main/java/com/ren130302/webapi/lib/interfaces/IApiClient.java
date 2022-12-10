package com.ren130302.webapi.lib.interfaces;

import java.util.Map;

public interface IApiClient {
	String apiLabel();

	String apiKey();

	String baseUrl();

	default void putApi(Map<String, String> map) {
		map.put(this.apiLabel(), this.apiKey());
	}
}
