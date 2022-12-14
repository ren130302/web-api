package com.ren130302.webapi.pornhubapi;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.ren130302.webapi.lib.ApiBuilder;
import com.ren130302.webapi.lib.ApiClient;
import com.ren130302.webapi.lib.ApiRequest;
import com.ren130302.webapi.lib.WebApi;
import com.ren130302.webapi.newsapi.NewsApiClient;
import com.ren130302.webapi.pornhubapi.request.Search;
import com.ren130302.webapi.pornhubapi.request.VideoActive;
import com.ren130302.webapi.pornhubapi.response.SearchResponse;

import retrofit2.Callback;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PornhubApiClient {

	public static final Supplier<ApiRequest> VIDEO_BY_ID = () -> ApiRequest.create(PornhubApiService.class, PornhubApiService::getVideoById, new Search.Builder());
	public static final Supplier<ApiRequest> IS_VIDEO_ACTIVE = () -> ApiRequest.create(PornhubApiService.class, PornhubApiService::isVideoActive, new VideoActive.Builder());
	public static final Supplier<ApiRequest> CATEGORIES = () -> ApiRequest.create(PornhubApiService.class, PornhubApiService::getCategories);
	public static final Supplier<ApiRequest> TAGS = () -> ApiRequest.create(PornhubApiService.class, PornhubApiService::getTags);
	public static final Supplier<ApiRequest> STARS = () -> ApiRequest.create(PornhubApiService.class, PornhubApiService::getStars);
	public static final Supplier<ApiRequest> STARS_DETAILED = () -> ApiRequest.create(PornhubApiService.class, PornhubApiService::getStarsDetailed);
	public static final Supplier<ApiRequest<PornhubApiService, SearchResponse, Search.Builder>> SEARCH = () -> ApiRequest.create(PornhubApiService.class, PornhubApiService::getSearch, new Search.Builder());

	private static final String BASE_URL = "https://www.pornhub.com/webmasters";

	public static ApiClient set() {
		return ApiClient.set(b -> b.baseUrl(BASE_URL).addConverterFactory(JacksonConverterFactory.create()));
	}

	public static <
		SERVICE,
		RESPONSE,
		BUILDER extends ApiBuilder> void executeQuery(Supplier<ApiRequest<SERVICE, RESPONSE, BUILDER>> requestSupplier, Supplier<String> apiKeySupplier, Consumer<BUILDER> paramsConsumer, Supplier<Callback<RESPONSE>> callbackSupplier) {
		WebApi.executeQuery(() -> WebApi.set(PornhubApiClient::set, requestSupplier), apiKeySupplier, paramsConsumer, callbackSupplier);
	}

	public
}
