package com.ren130302.webapi.newsapi.request;

import com.ren130302.webapi.lib.ApiBuilder;
import com.ren130302.webapi.lib.QueryUtils;
import com.ren130302.webapi.newsapi.enums.NewsCategory;
import com.ren130302.webapi.newsapi.enums.NewsCountry;
import com.ren130302.webapi.newsapi.enums.NewsLanguage;

import lombok.Data;
import lombok.experimental.Accessors;

public final class Sources {

	public static class Builder
		extends ApiBuilder {

		/**
		 * Find sources that display news of this category. <br>
		 * Default: all categories.<br>
		 *
		 * @see NewsCategory
		 */
		public Builder categories(NewsCategory... categories) {
			return this.put("category", QueryUtils.enumValue(categories, NewsCategory.values()));
		}

		/**
		 * Find sources that display news in a specific language.<br>
		 * Default: all languages.<br>
		 *
		 * @see NewsLanguage
		 */
		public Builder languages(NewsLanguage... languages) {
			return this.put("language", QueryUtils.enumValue(languages, NewsLanguage.values()));
		}

		/**
		 * Find sources that display news in a specific country.<br>
		 * <br>
		 * Default: all countries.<br>
		 *
		 * @see NewsCountry
		 */
		public Builder countries(NewsCountry... countries) {
			return this.put("country", QueryUtils.enumValue(countries, NewsCountry.values()));
		}
	}

	@Data
	@Accessors(fluent = true)
	public static class Form {

		private NewsCategory[] categories = null;

		private NewsLanguage[] languages = null;

		private NewsCountry[] countries = null;
	}
}
