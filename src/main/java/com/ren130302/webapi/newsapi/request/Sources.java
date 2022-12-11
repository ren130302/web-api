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
import com.ren130302.webapi.newsapi.response.Source;
import com.ren130302.webapi.newsapi.response.Source.Response;

import lombok.Data;
import lombok.Value;

public class Sources {

	public static ApiRequest<NewsApiService, Source.Response, Sources.Builder> create() {
		return ApiRequest.create(NewsApiService.class, Builder::new, Response::new, NewsApiService::getSources);
	}

	@Value
	public static class Builder
		implements IBuilder {
		private final Map<String, String> queryMap = new HashMap<>();

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
	public static class Form
		implements IForm<Sources.Builder> {
		@Override
		public Consumer<Sources.Builder> wrap() {
			return b -> b.categories(this.getCategories()).countries(this.getCountries()).languages(this.getLanguages());
		}

		private NewsCategory[] categories = null;

		private NewsLanguage[] languages = null;

		private NewsCountry[] countries = null;
	}
}
