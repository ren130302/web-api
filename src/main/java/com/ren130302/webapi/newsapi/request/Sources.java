package com.ren130302.webapi.newsapi.request;

import java.util.HashMap;
import java.util.Map;

import com.ren130302.webapi.lib.ApiRequest;
import com.ren130302.webapi.lib.interfaces.IBuilder;
import com.ren130302.webapi.newsapi.NewsApiService;
import com.ren130302.webapi.newsapi.response.Source;
import com.ren130302.webapi.newsapi.response.Source.Response;

import lombok.Value;

public class Sources {

	public static ApiRequest<NewsApiService, Source.Response, Sources.Builder> create() {
		return ApiRequest.create(NewsApiService.class, Builder::new, Response::new, NewsApiService::getSources);
	}

	@Value
	public static class Builder
		implements IBuilder {
		private final Map<String, String> queryMap = new HashMap<>();

		public Builder category(String category) {
			return this.put("category", category);
		}

		public Builder language(String language) {
			return this.put("language", language);
		}

		public Builder country(String country) {
			return this.put("country", country);
		}
	}
}
