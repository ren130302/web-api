package com.ren130302.webapi.newsapi;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.ren130302.webapi.lib.ApiBuilder;
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

import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsApiClient {

	public static final Supplier<ApiRequest<NewsApiService, Article.Response, Everything.Builder>> EVERYTHING = () -> ApiRequest.create(NewsApiService.class, NewsApiService::getEverything, new Everything.Builder());
	public static final Supplier<ApiRequest<NewsApiService, Article.Response, TopHeadlines.Builder>> TOP_HEADLINES = () -> ApiRequest.create(NewsApiService.class, NewsApiService::getTopHeadlines, new TopHeadlines.Builder());
	public static final Supplier<ApiRequest<NewsApiService, Source.Response, Sources.Builder>> SOURCES = () -> ApiRequest.create(NewsApiService.class, NewsApiService::getSources, new Sources.Builder());

	private static final String BASE_URL = "https://newsapi.org/";
	private static final String API_LABEL = "apiKey";
	private static final String API_KEY = "bb9f2ecdbb9a4882b51cf5a4b98b86a6";

	public static ApiClient set() {
		return ApiClient.set(API_LABEL, b -> b.baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()));
	}

	public static <
		SERVICE,
		RESPONSE,
		BUILDER extends ApiBuilder> void executeQuery(Supplier<ApiRequest<SERVICE, RESPONSE, BUILDER>> requestSupplier, Supplier<String> apiKeySupplier, Consumer<BUILDER> paramsConsumer, Supplier<Callback<RESPONSE>> callbackSupplier) {
		WebApi.executeQuery(() -> WebApi.set(NewsApiClient::set, requestSupplier), apiKeySupplier, paramsConsumer, callbackSupplier);
	}

	public static void main(String[] args) {
		NewsApiClient.executeQuery(
				EVERYTHING, 
				() -> API_KEY, 
				b -> b.q("trump"), 
				() -> ExtendsCallback.onSuccess(
						(c, r) -> {
							Article article = r.getArticles().get(0);
							System.out.println(article.toString());
						}, 
						(c, t) -> System.out.println(t.getMessage())
				));

		NewsApiClient.executeQuery(
				TOP_HEADLINES, 
				() -> API_KEY, 
				b -> b.q("world"), 
				() -> ExtendsCallback.onSuccess(
						(c, r) -> {
							Article article = r.getArticles().get(0);
							System.out.println(article.toString());
						},
						(c, t) -> System.out.println(t.getMessage())
				));

		NewsApiClient.executeQuery(
				SOURCES, 
				() -> API_KEY, 
				b -> b.languages(NewsLanguage.EN).countries(NewsCountry.US), 
				() -> ExtendsCallback.onSuccess(
						(c, r) -> System.out.println(r.getSources().get(0).getName()), 
						(c, t) -> System.out.println(t.getMessage())
				));
	}
}