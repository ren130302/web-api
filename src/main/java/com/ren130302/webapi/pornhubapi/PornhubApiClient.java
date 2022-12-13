package com.ren130302.webapi.pornhubapi;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.ren130302.webapi.lib.ApiClient;
import com.ren130302.webapi.lib.ApiRequest;
import com.ren130302.webapi.lib.WebApi;
import com.ren130302.webapi.lib.interfaces.IApiService;
import com.ren130302.webapi.lib.interfaces.IBuilder;
import com.ren130302.webapi.lib.interfaces.IResponse;
import com.ren130302.webapi.newsapi.NewsApiClient;
import com.ren130302.webapi.newsapi.NewsApiService;
import com.ren130302.webapi.newsapi.request.Sources;
import com.ren130302.webapi.newsapi.response.Source;

import retrofit2.Callback;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PornhubApiClient {

	public static final Supplier<ApiRequest<NewsApiService, Source.Response, Sources.Builder>> SOURCES = Sources::create;

	private static final String BASE_URL = "https://www.pornhub.com/webmasters";

	public static ApiClient set() {
		return ApiClient.set(b -> b.baseUrl(BASE_URL).addConverterFactory(JacksonConverterFactory.create()));
	}

	public static <
		SERVICE extends IApiService,
		RESPONSE extends IResponse,
		BUILDER extends IBuilder> void executeQuery(Supplier<ApiRequest<SERVICE, RESPONSE, BUILDER>> requestSupplier, Supplier<String> apiKeySupplier, Consumer<BUILDER> paramsConsumer, Supplier<Callback<RESPONSE>> callbackSupplier) {
		WebApi.executeQuery(() -> WebApi.set(NewsApiClient::set, requestSupplier), apiKeySupplier, paramsConsumer, callbackSupplier);
	}
}
