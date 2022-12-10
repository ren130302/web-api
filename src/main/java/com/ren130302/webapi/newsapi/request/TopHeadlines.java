package com.ren130302.webapi.newsapi.request;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.ren130302.webapi.lib.ApiRequest;
import com.ren130302.webapi.lib.QueryUtils;
import com.ren130302.webapi.lib.interfaces.IBuilder;
import com.ren130302.webapi.lib.interfaces.IForm;
import com.ren130302.webapi.newsapi.NewsApiService;
import com.ren130302.webapi.newsapi.enums.NewsCategory;
import com.ren130302.webapi.newsapi.enums.NewsCountry;
import com.ren130302.webapi.newsapi.enums.NewsLanguage;
import com.ren130302.webapi.newsapi.response.Article;
import com.ren130302.webapi.newsapi.response.Article.Response;

import lombok.Data;
import lombok.Value;

public class TopHeadlines {

	public static ApiRequest<NewsApiService, Article.Response, TopHeadlines.Builder> create() {
		return ApiRequest.create(NewsApiService.class, Builder::new, Response::new, NewsApiService::getTopHeadlines);
	}

	@Value
	public static class Builder
		implements IBuilder {
		private final Map<String, String> queryMap = new HashMap<>();

		/**
		 * Keywords or phrases to search for in the article title and body.<br>
		 * <br>
		 * Advanced search is supported here:<br>
		 * Surround phrases with quotes (") for exact match. Prepend words or phrases
		 * that must appear with a + symbol. <br>
		 * Eg: +bitcoin Prepend words that must not appear with a - symbol. <br>
		 * Eg: -bitcoin Alternatively you can use the AND / OR / NOT keywords, and
		 * optionally group these with parenthesis.<br>
		 * Eg: crypto AND (ethereum OR litecoin) NOT bitcoin. The complete value for q
		 * must be URL-encoded. <br>
		 * <br>
		 * Max length: 500 chars.
		 */
		public Builder q(String q) {
			return this.put("q", q);
		}

		/**
		 * The category you want to get headlines for. <br>
		 * <br>
		 * <strong> Note: you can't mix this param with the sources param.<br>
		 * </strong>
		 *
		 * @see NewsCategory
		 */
		public Builder category(NewsCategory... category) {
			return this.put("category", QueryUtils.enums(category, NewsCategory.values()));
		}

		/**
		 * A comma-seperated string of identifiers (maximum 20) for the news sources or
		 * blogs you want headlines from. Use the /sources endpoint to locate these
		 * programmatically or look at the sources index.<br>
		 * <br>
		 * <strong> Note: you can't mix this param with the country or category
		 * params.</strong><br>
		 */
		public Builder sources(String... sources) {
			return this.put("sources", QueryUtils.strings(sources));
		}

		/**
		 * The 2-letter ISO 3166-1 code of the country you want to get headlines for.
		 * <strong> Note: you can't mix this param with the sources param. </strong>
		 * Default: all countries.<br>
		 *
		 * @see NewsCountry
		 */
		public Builder country(NewsCountry... country) {
			return this.put("country", QueryUtils.enums(country, NewsCountry.values()));
		}

		/**
		 * The number of results to return per page.<br>
		 * <br>
		 * Default: 100. Max: 100.<br>
		 */
		public Builder pageSize(int pageSize) {
			return this.put("pageSize", String.valueOf(pageSize));
		}

		/**
		 * Use this to page through the results.<br>
		 * <br>
		 * Default: 1.Min:1<br>
		 */
		public Builder page(int page) {
			return this.put("page", String.valueOf(page));
		}

		/**
		 * Find sources that display news in a specific language.<br>
		 * Default: all languages.<br>
		 * <br>
		 *
		 * @see NewsLanguage
		 */
		public Builder language(NewsLanguage... language) {
			return this.put("language", QueryUtils.enums(language, NewsLanguage.values()));
		}
	}

	@Data
	public class Form
		implements IForm<TopHeadlines.Builder> {
		@Override
		public Consumer<TopHeadlines.Builder> wrap() {
			return b -> b.q(this.getQ()).category(this.getCategories()).country(this.getCountries()).sources(this.getSources()).pageSize(this.getPageSize()).page(this.getPage());
		}

		private String q;

		private NewsCountry[] countries = NewsCountry.values();

		private NewsCategory[] categories = NewsCategory.values();

		private NewsLanguage[] languages = NewsLanguage.values();

		private String[] sources;

		private int pageSize = 100;

		private int page = 1;
	}
}
