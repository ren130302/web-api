package com.ren130302.webapi.newsapi.request;

import java.util.HashMap;
import java.util.Map;

import com.ren130302.webapi.lib.ApiRequest;
import com.ren130302.webapi.lib.interfaces.IBuilder;
import com.ren130302.webapi.newsapi.NewsApiService;
import com.ren130302.webapi.newsapi.response.Article;
import com.ren130302.webapi.newsapi.response.Article.Response;

import lombok.Value;

public class Everything {

	public static ApiRequest<NewsApiService, Article.Response, Everything.Builder> create() {
		return ApiRequest.create(NewsApiService.class, Builder::new, Response::new, NewsApiService::getEverything);
	}

	@Value
	public static class Builder
		implements IBuilder {
		private final Map<String, String> queryMap = new HashMap<>();

		public Builder q(String q) {
			return this.put("q", q);
		}

		public Builder sources(String sources) {
			return this.put("sources", sources);
		}

		public Builder includeDomains(String domains) {
			return this.put("domains", domains);
		}

		public Builder excludeDomains(String domains) {
			return this.put("excludeDomains", domains);
		}

		public Builder from(String from) {
			return this.put("from", from);
		}

		public Builder to(String to) {
			return this.put("to", to);
		}

		public Builder language(String language) {
			return this.put("language", language);
		}

		public Builder sortBy(String sortBy) {
			return this.put("sortBy", sortBy);
		}

		public Builder pageSize(int pageSize) {
			return this.put("pageSize", String.valueOf(pageSize));
		}

		public Builder page(int page) {
			return this.put("page", String.valueOf(page));
		}

		public Builder searchIn(String searchIn) {
			return this.put("searchIn", searchIn);
		}
	}
}