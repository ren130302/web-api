package com.ren130302.webapi.newsapi.request;

import java.util.HashMap;
import java.util.Map;

import com.ren130302.webapi.lib.ApiRequest;
import com.ren130302.webapi.lib.interfaces.IBuilder;
import com.ren130302.webapi.newsapi.NewsApiService;
import com.ren130302.webapi.newsapi.response.Article;
import com.ren130302.webapi.newsapi.response.Article.Response;

import lombok.Value;

public class TopHeadlines {

	public static ApiRequest<NewsApiService, Article.Response, TopHeadlines.Builder> create() {
		return ApiRequest.create(NewsApiService.class, Builder::new, Response::new, NewsApiService::getTopHeadlines);
	}

	@Value
	public static class Builder
		implements IBuilder {
		private final Map<String, String> queryMap = new HashMap<>();

		public Builder q(String q) {
			return this.put("q", q);
		}

		public Builder category(String category) {
			return this.put("category", category);
		}

		public Builder sources(String sources) {
			return this.put("sources", sources);
		}

		public Builder country(String country) {
			return this.put("country", country);
		}

		public Builder pageSize(int pageSize) {
			return this.put("pageSize", String.valueOf(pageSize));
		}

		public Builder page(int page) {
			return this.put("page", String.valueOf(page));
		}

		public Builder language(String language) {

			return this.put("language", language);
		}
	}
}
