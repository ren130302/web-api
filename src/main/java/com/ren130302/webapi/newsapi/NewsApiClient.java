package com.ren130302.webapi.newsapi;

import java.util.function.Function;

import com.ren130302.webapi.lib.ApiClient;
import com.ren130302.webapi.lib.CallbackImpl;
import com.ren130302.webapi.lib.ParamSupplier;
import com.ren130302.webapi.lib.WebApi;
import com.ren130302.webapi.newsapi.request.Everything;
import com.ren130302.webapi.newsapi.request.Sources;
import com.ren130302.webapi.newsapi.request.TopHeadlines;
import com.ren130302.webapi.newsapi.response.Article;
import com.ren130302.webapi.newsapi.response.Source;

public class NewsApiClient {

	public static final Function<ParamSupplier<Article.Response, Everything.Builder>, WebApi<NewsApiService, Article.Response, Everything.Builder>> EVERYTHING = e -> WebApi.set(NewsApiClient::set, Everything::create, e);
	public static final Function<ParamSupplier<Source.Response, Sources.Builder>, WebApi<NewsApiService, Source.Response, Sources.Builder>> SOURCES = e -> WebApi.set(NewsApiClient::set, Sources::create, e);
	public static final Function<ParamSupplier<Article.Response, TopHeadlines.Builder>, WebApi<NewsApiService, Article.Response, TopHeadlines.Builder>> TOP_HEADLINES = e -> WebApi.set(NewsApiClient::set, TopHeadlines::create, e);
	private static final String API_LABEL = "apiKey";
	private static final String API_KEY = "bb9f2ecdbb9a4882b51cf5a4b98b86a6";
	private static final String BASE_URL = "https://newsapi.org/";

	public static ApiClient set(String apiKey) {
		return ApiClient.set(API_LABEL, apiKey, BASE_URL);
	}

	public static void main(String[] args) {
		WebApi.executeQuery(EVERYTHING, ParamSupplier.of(() -> API_KEY, b -> b.q("trump"), () -> CallbackImpl.of(r -> System.out.println(r.getArticles().get(0).getTitle()), t -> System.out.println(t.getMessage()))));
	}
}