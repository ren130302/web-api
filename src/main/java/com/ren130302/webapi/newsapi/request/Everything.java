package com.ren130302.webapi.newsapi.request;

import java.time.LocalDateTime;

import com.ren130302.webapi.lib.ApiBuilder;
import com.ren130302.webapi.lib.QueryUtils;
import com.ren130302.webapi.newsapi.enums.NewsLanguage;
import com.ren130302.webapi.newsapi.enums.NewsSearchIn;
import com.ren130302.webapi.newsapi.enums.NewsSort;

import lombok.Data;
import lombok.experimental.Accessors;

public final class Everything {

	public static class Builder
		extends ApiBuilder {

		public static Builder ofQ(String q) {
			return new Builder().q(q);
		}

		public static Builder ofSources(String[] sources) {
			return new Builder().sources(sources);
		}

		public static Builder ofIncludeDomains(String[] includeDomains) {
			return new Builder().includeDomains(includeDomains);
		}

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
		 * A comma-seperated string of identifiers (maximum 20) for the news sources or
		 * blogs you want headlines from. <br>
		 * <br>
		 * Use the /sources endpoint to locate these programmatically or look at the
		 * sources index.<br>
		 */
		public Builder sources(String[] sources) {
			return this.put("sources", QueryUtils.strings(sources));
		}

		/**
		 * A comma-seperated string of domains to restrict the search to.<br>
		 * <br>
		 * Eg: bbc.co.uk, techcrunch.com, engadget.com<br>
		 */
		public Builder includeDomains(String[] domains) {
			return this.put("domains", QueryUtils.strings(domains));
		}

		/**
		 * A comma-seperated string of domains to remove from the results.<br>
		 * <br>
		 * Eg: bbc.co.uk, techcrunch.com, engadget.com<br>
		 */
		public Builder excludeDomains(String[] domains) {
			return this.put("excludeDomains", QueryUtils.strings(domains));
		}

		/**
		 * A date and optional time for the oldest article allowed. <br>
		 * <br>
		 * This should be in ISO 8601 format.<br>
		 * <br>
		 * Eg: "2022-12-05" or "2022-12-05T04:45:42"<br>
		 * Default: the oldest according to your plan.<br>
		 */
		public Builder from(String from) {
			return this.put("from", from);
		}

		/**
		 * A date and optional time for the newest article allowed. <br>
		 * <br>
		 * This should be in ISO 8601 format.<br>
		 * <br>
		 * Eg: "2022-12-05" or "2022-12-05T04:45:42"<br>
		 * Default: the newest according to your plan.<br>
		 */
		public Builder to(String to) {
			return this.put("to", to);
		}

		/**
		 * The 2-letter ISO-639-1 code of the language you want to get headlines
		 * for.<br>
		 * <br>
		 * Default: all languages returned.<br>
		 */
		public Builder languages(NewsLanguage... language) {
			return this.put("language", QueryUtils.enumValue(language, NewsLanguage.values()));
		}

		/**
		 * The order to sort the articles in. <br>
		 * <br>
		 * Default: publishedAt.<br>
		 */
		public Builder sortBy(NewsSort sortBy) {
			return this.put("sortBy", QueryUtils.enumValue(sortBy, NewsSort.PUBLISHED_AT));
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
		 * The fields to restrict your q search to.<br>
		 * <br>
		 * The possible options are:<br>
		 * title description content Multiple options can be specified by separating
		 * them with a comma, for example: title,content.<br>
		 * This parameter is useful if you have an edge case where searching all the
		 * fields is not giving the desired outcome, but generally you should not need
		 * to set this. <br>
		 * <br>
		 * Default: all fields are searched.
		 */
		public Builder searchIn(NewsSearchIn... searchIn) {
			return this.put("searchIn", QueryUtils.enumValue(searchIn, NewsSearchIn.values()));
		}
	}

	@Data
	@Accessors(fluent = true)
	public static class Form {

		private String q = null;

		private NewsSearchIn[] searchIns = null;

		private String[] sources = null;

		private String[] includeDomains = null;

		private String[] excludeDoamins = null;

		private LocalDateTime from = null;

		private LocalDateTime to = null;

		private NewsLanguage[] languages = null;

		private NewsSort sortBy = null;

		private Integer pageSize = null;

		private Integer page = null;
	}
}