package com.ren130302.webapi.newsapi;

import com.ren130302.webapi.lib.ApiClient;
import com.ren130302.webapi.lib.ApiRequest;
import com.ren130302.webapi.lib.ExtendsCallback;
import com.ren130302.webapi.lib.WebApi;
import com.ren130302.webapi.newsapi.enums.NewsCountry;
import com.ren130302.webapi.newsapi.enums.NewsLanguage;
import com.ren130302.webapi.newsapi.request.Everything;
import com.ren130302.webapi.newsapi.request.Sources;
import com.ren130302.webapi.newsapi.request.TopHeadlines;
import com.ren130302.webapi.newsapi.response.Article;
import com.ren130302.webapi.newsapi.response.Source;

import retrofit2.converter.jackson.JacksonConverterFactory;

public class NewsApiClient {

	public static final WebApi<NewsApiService, Article.Response, Everything.Builder> EVERYTHING = WebApi.set(NewsApiClient.set(), ApiRequest.of(NewsApiService.class, NewsApiService::getEverything, new Everything.Builder()));
	public static final WebApi<NewsApiService, Article.Response, TopHeadlines.Builder> TOP_HEADLINES = WebApi.set(NewsApiClient.set(), ApiRequest.of(NewsApiService.class, NewsApiService::getTopHeadlines, new TopHeadlines.Builder()));
	public static final WebApi<NewsApiService, Source.Response, Sources.Builder> SOURCES = WebApi.set(NewsApiClient.set(), ApiRequest.of(NewsApiService.class, NewsApiService::getSources, new Sources.Builder()));

	private static final String BASE_URL = "https://newsapi.org/";
	private static final String API_LABEL = "apiKey";
	private static final String API_KEY = "{your api key}";

	public static ApiClient set() {
		return ApiClient.set(API_LABEL, b -> b.baseUrl(BASE_URL).addConverterFactory(JacksonConverterFactory.create()));
	}

	public static void main(String[] args) {
		NewsApiClient.EVERYTHING.executeQuery(API_KEY, b -> b.q("trump"), ExtendsCallback.onSuccess((c, r) -> {
			Article article = r.getArticles().get(0);
			System.out.println(article.toString());
		}, (c, t) -> System.out.println(t.getMessage())));

		NewsApiClient.TOP_HEADLINES.executeQuery(API_KEY, b -> b.q("world"), ExtendsCallback.onSuccess((c, r) -> {
			Article article = r.getArticles().get(0);
			System.out.println(article.toString());
		}, (c, t) -> System.out.println(t.getMessage())));

		NewsApiClient.SOURCES.executeQuery(API_KEY, b -> b.languages(NewsLanguage.EN).countries(NewsCountry.US), ExtendsCallback.onSuccess((c, r) -> System.out.println(r.getSources().get(0).getName()), (c, t) -> System.out.println(t.getMessage())));
	}
}
