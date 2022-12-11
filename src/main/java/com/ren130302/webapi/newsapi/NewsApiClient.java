package com.ren130302.webapi.newsapi;

import java.util.function.Supplier;

import com.ren130302.webapi.lib.ApiClient;
import com.ren130302.webapi.lib.ExtendsCallback;
import com.ren130302.webapi.lib.WebApi;
import com.ren130302.webapi.newsapi.enums.NewsCountry;
import com.ren130302.webapi.newsapi.enums.NewsLanguage;
import com.ren130302.webapi.newsapi.request.Everything;
import com.ren130302.webapi.newsapi.request.Sources;
import com.ren130302.webapi.newsapi.request.TopHeadlines;
import com.ren130302.webapi.newsapi.response.Article;
import com.ren130302.webapi.newsapi.response.Source;

public class NewsApiClient {

	public static final Supplier<WebApi<NewsApiService, Article.Response, Everything.Builder>> EVERYTHING = () -> WebApi.set(NewsApiClient::set, Everything::create);
	public static final Supplier<WebApi<NewsApiService, Article.Response, TopHeadlines.Builder>> TOP_HEADLINES = () -> WebApi.set(NewsApiClient::set, TopHeadlines::create);
	public static final Supplier<WebApi<NewsApiService, Source.Response, Sources.Builder>> SOURCES = () -> WebApi.set(NewsApiClient::set, Sources::create);

	private static final String BASE_URL = "https://newsapi.org/";
	private static final String API_LABEL = "apiKey";
	private static final String API_KEY = "bb9f2ecdbb9a4882b51cf5a4b98b86a6";

	public static ApiClient set() {
		return ApiClient.set(BASE_URL, API_LABEL);
	}

	public static void main(String[] args) {
		WebApi.executeQuery(
				EVERYTHING, 
				() -> API_KEY, b -> b.q("trump"), 
				() -> ExtendsCallback.onSuccess(
						(c, r) -> System.out.println(r.getArticles().get(0).getTitle()), 
						(c, t) -> System.out.println(t.getMessage())
						)
				);

		WebApi.executeQuery(
				TOP_HEADLINES, 
				() -> API_KEY, 
				b -> b.q("bitcoin").languages(NewsLanguage.EN), 
				() -> ExtendsCallback.onSuccess(
						(c, r) -> System.out.println(r.getArticles().get(0).getTitle()), 
						(c, t) -> System.out.println(t.getMessage())
						)
				);

		WebApi.executeQuery(
				SOURCES, 
				() -> API_KEY, 
				b -> b.languages(NewsLanguage.EN).countries(NewsCountry.US), 
				() -> ExtendsCallback.onSuccess(
						(c, r) -> System.out.println(r.getSources().get(0).getName()), 
						(c, t) -> System.out.println(t.getMessage())
						)
				);
	}
}