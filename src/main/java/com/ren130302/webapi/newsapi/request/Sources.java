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
		public Builder category(NewsCategory... category) {
			return this.put("category", QueryUtils.enums(category, NewsCategory.values()));
		}

		/**
		 * Find sources that display news in a specific language.<br>
		 * Default: all languages.<br>
		 *
		 * @see NewsLanguage
		 */
		public Builder language(NewsLanguage... language) {
			return this.put("language", QueryUtils.enums(language, NewsLanguage.values()));
		}

		/**
		 * Find sources that display news in a specific country.<br>
		 * <br>
		 * Default: all countries.<br>
		 *
		 * @see NewsCountry
		 */
		public Builder country(NewsCountry... country) {
			return this.put("country", QueryUtils.enums(country, NewsCountry.values()));
		}
	}

	@Data
	public static class Form
		implements IForm<Sources.Builder> {
		@Override
		public Consumer<Sources.Builder> wrap() {
			return b -> b.category(this.getCategories()).country(this.getCountries()).language(this.getLanguages());
		}

		private NewsCategory[] categories = NewsCategory.values();

		private NewsLanguage[] languages = NewsLanguage.values();

		private NewsCountry[] countries = NewsCountry.values();
	}
}
