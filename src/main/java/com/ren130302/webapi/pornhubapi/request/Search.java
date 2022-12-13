package com.ren130302.webapi.pornhubapi.request;

import java.util.HashMap;
import java.util.Map;

import com.ren130302.webapi.lib.ApiRequest;
import com.ren130302.webapi.lib.interfaces.IBuilder;
import com.ren130302.webapi.newsapi.NewsApiService;
import com.ren130302.webapi.newsapi.response.Article;
import com.ren130302.webapi.pornhubapi.PornhubApiService;

import lombok.Value;

public final class Search {
	public static ApiRequest<PornhubApiService, Article.Response, Video.Builder> create() {
		return ApiRequest.create(PornhubApiService.class, Builder::new, Response::new, NewsApiService::getEverything);
	}

	@Value
	public static class Builder
		implements IBuilder {
		private final Map<String, String> queryMap = new HashMap<>();

		public Builder q(String q) {
			this.getQueryMap().put("search", q);
			return this;
		}

		public Builder thumbsize(String q) {
			this.getQueryMap().put("thumbsize", q);
			return this;
		}

		public Builder category(String q) {
			this.getQueryMap().put("category", q);
			return this;
		}

		public Builder page(String q) {
			this.getQueryMap().put("page", q);
			return this;
		}

		public Builder ordering(String q) {
			this.getQueryMap().put("ordering", q);
			return this;
		}

		public Builder phrase(String q) {
			this.getQueryMap().put("phrase", q);
			return this;
		}

		public Builder tags(String q) {
			this.getQueryMap().put("tags", q);
			return this;
		}

		public Builder period(String q) {
			this.getQueryMap().put("period", q);
			return this;
		}
	}
}
