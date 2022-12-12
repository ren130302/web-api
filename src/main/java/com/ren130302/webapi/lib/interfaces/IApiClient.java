package com.ren130302.webapi.lib.interfaces;

import java.util.function.Function;

import retrofit2.Retrofit;

public interface IApiClient {
	String apiLabel();

	Function<Retrofit.Builder, Retrofit.Builder> retrofitBuilderFunction();
}
