package com.ren130302.webapi.newsapi;

import java.util.function.Supplier;

import com.ren130302.webapi.lib.ApiClient;
import com.ren130302.webapi.lib.CallbackImpl;
import com.ren130302.webapi.lib.WebApi;
import com.ren130302.webapi.newsapi.request.Everything;
import com.ren130302.webapi.newsapi.request.Sources;
import com.ren130302.webapi.newsapi.request.TopHeadlines;
import com.ren130302.webapi.newsapi.response.Article;
import com.ren130302.webapi.newsapi.response.Source;

public class NewsApiClient {

	public static final Supplier<WebApi<NewsApiService, Article.Response, Everything.Builder>> EVERYTHING = () -> WebApi.set(NewsApiClient::set, Everything::create);
	public static final Supplier<WebApi<NewsApiService, Source.Response, Sources.Builder>> SOURCES = () -> WebApi.set(NewsApiClient::set, Sources::create);
	public static final Supplier<WebApi<NewsApiService, Article.Response, TopHeadlines.Builder>> TOP_HEADLINES = () -> WebApi.set(NewsApiClient::set, TopHeadlines::create);

	private static final String API_LABEL = "apiKey";
	private static final String API_KEY = "bb9f2ecdbb9a4882b51cf5a4b98b86a6";
	private static final String BASE_URL = "https://newsapi.org/";

	public static ApiClient set() {
		return ApiClient.set(BASE_URL, API_LABEL);
	}

	public static void main(String[] args) {
		WebApi.executeQuery(EVERYTHING, () -> API_KEY, b -> b.q("trump"), () -> CallbackImpl.of(r -> System.out.println(r.getArticles().get(0).getTitle()), t -> System.out.println(t.getMessage())));
	}
}