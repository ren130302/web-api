package com.ren130302.webapi.newsapi;

import java.util.Map;

import com.ren130302.webapi.lib.interfaces.IApiService;
import com.ren130302.webapi.newsapi.response.Article;
import com.ren130302.webapi.newsapi.response.Source;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface NewsApiService
	extends IApiService {
	@GET("/v2/sources")
	Call<Source.Response> getSources(@QueryMap Map<String, String> query);

	@GET("/v2/top-headlines")
	Call<Article.Response> getTopHeadlines(@QueryMap Map<String, String> query);

	@GET("/v2/everything")
	Call<Article.Response> getEverything(@QueryMap Map<String, String> query);
}
