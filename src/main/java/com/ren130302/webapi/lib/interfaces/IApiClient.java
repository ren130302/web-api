package com.ren130302.webapi.lib.interfaces;

import java.util.function.Function;

import lombok.NonNull;
import retrofit2.Retrofit;

public interface IApiClient {
	String apiLabel();

	@NonNull
	Function<Retrofit.Builder, Retrofit.Builder> retrofitBuilderFunction();
}
