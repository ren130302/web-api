package com.ren130302.webapi.newsapi.request;

import com.ren130302.webapi.lib.ApiBuilder;
import com.ren130302.webapi.lib.QueryUtils;
import com.ren130302.webapi.newsapi.enums.NewsCategory;
import com.ren130302.webapi.newsapi.enums.NewsCountry;
import com.ren130302.webapi.newsapi.enums.NewsLanguage;

import lombok.Data;
import lombok.experimental.Accessors;

public final class TopHeadlines {

	public static class Builder
		extends ApiBuilder {

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
		public Builder categories(NewsCategory... categories) {
			return this.put("category", QueryUtils.enumValue(categories, NewsCategory.values()));
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
		public Builder countries(NewsCountry... countries) {
			return this.put("country", QueryUtils.enumValue(countries, NewsCountry.values()));
		}

		/**
		 * The number of results to return per page.<br>
		 * <br>
		 * Default: 100. Max: 100.<br>
		 */
		public Builder pageSize(Integer pageSize) {
			return this.put("pageSize", QueryUtils.numValue(pageSize, 100));
		}

		/**
		 * Use this to page through the results.<br>
		 * <br>
		 * Default: 1.Min:1<br>
		 */
		public Builder page(Integer page) {
			return this.put("page", QueryUtils.numValue(page, 1));
		}

		/**
		 * Find sources that display news in a specific language.<br>
		 * Default: all languages.<br>
		 * <br>
		 *
		 * @see NewsLanguage
		 */
		public Builder languages(NewsLanguage... languages) {
			return this.put("language", QueryUtils.enumValue(languages, NewsLanguage.values()));
		}
	}

	@Data
	@Accessors(fluent = true)
	public static class Form {

		private String q = null;

		private NewsCountry[] countries = null;

		private NewsCategory[] categories = null;

		private NewsLanguage[] languages = null;

		private String[] sources = null;

		private Integer pageSize = null;

		private Integer page = null;
	}
}
