package com.ren130302.webapi.pornhubapi;

import com.ren130302.webapi.lib.ApiClient;
import com.ren130302.webapi.lib.ApiRequest;
import com.ren130302.webapi.lib.ExtendsCallback;
import com.ren130302.webapi.lib.WebApi;
import com.ren130302.webapi.pornhubapi.request.Search;
import com.ren130302.webapi.pornhubapi.request.Search.Builder;
import com.ren130302.webapi.pornhubapi.request.VideoActive;
import com.ren130302.webapi.pornhubapi.response.CategoriesResponse;
import com.ren130302.webapi.pornhubapi.response.SearchResponse;
import com.ren130302.webapi.pornhubapi.response.StarsDetailedResponse;
import com.ren130302.webapi.pornhubapi.response.StarsResponse;
import com.ren130302.webapi.pornhubapi.response.StarsResponse.Star;
import com.ren130302.webapi.pornhubapi.response.TagsResponse;
import com.ren130302.webapi.pornhubapi.response.VideoResponse;

import retrofit2.converter.jackson.JacksonConverterFactory;

public class PornhubApiClient {

	public static final WebApi<PornhubApiService, VideoResponse, Builder> VIDEO_BY_ID = WebApi.set(PornhubApiClient.set(), ApiRequest.of(PornhubApiService.class, PornhubApiService::getVideoById, new Search.Builder()));
	public static final WebApi<PornhubApiService, VideoResponse, VideoActive.Builder> IS_VIDEO_ACTIVE = WebApi.set(PornhubApiClient.set(), ApiRequest.of(PornhubApiService.class, PornhubApiService::isVideoActive, new VideoActive.Builder()));
	public static final WebApi<PornhubApiService, CategoriesResponse, ?> CATEGORIES = WebApi.set(PornhubApiClient.set(), (ApiRequest) ApiRequest.of(PornhubApiService.class, PornhubApiService::getCategories));
	public static final WebApi<PornhubApiService, TagsResponse, ?> TAGS = WebApi.set(PornhubApiClient.set(), (ApiRequest) ApiRequest.of(PornhubApiService.class, PornhubApiService::getTags));
	public static final WebApi<PornhubApiService, StarsResponse, ?> STARS = WebApi.set(PornhubApiClient.set(), (ApiRequest) ApiRequest.of(PornhubApiService.class, PornhubApiService::getStars));
	public static final WebApi<PornhubApiService, StarsDetailedResponse, ?> STARS_DETAILED = WebApi.set(PornhubApiClient.set(), (ApiRequest) ApiRequest.of(PornhubApiService.class, PornhubApiService::getStarsDetailed));
	public static final WebApi<PornhubApiService, SearchResponse, Search.Builder> SEARCH = WebApi.set(PornhubApiClient.set(), ApiRequest.of(PornhubApiService.class, PornhubApiService::getSearch, new Search.Builder()));

	private static final String BASE_URL = "https://www.pornhub.com/webmasters/";

	public static ApiClient set() {
		return ApiClient.set(b -> b.baseUrl(BASE_URL).addConverterFactory(JacksonConverterFactory.create()));
	}

	public static void main(String[] args) {
		// PornhubApiClient.STARS.executeQuery(ExtendsCallback.onResponse((c, r) -> {
		// String category = r.toString();
		// System.out.println(category);
		// }, (c, t) -> {
		// String s = t.getMessage();
		// System.out.println(s);
		// }));
		PornhubApiClient.STARS.executeQuery(ExtendsCallback.onSuccess((c, r) -> {
			Star category = r.getStars().get(0);
			System.out.println(category.toString());
		}, (c, t) -> {
			String s = t.getMessage();
			System.out.println(s);
		}));
	}
}