package com.ren130302.webapi.lib.interfaces;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

import retrofit2.Call;
import retrofit2.Retrofit;

public interface IApiRequest<
	SERVICE extends IApiService,
	RESPONSE,
	BUILDER extends IApiBuilder> {

	Class<SERVICE> serviceClass();

	BiFunction<SERVICE, Map<String, String>, Call<RESPONSE>> urlMethod();

	Optional<BUILDER> builderOptional();

	Map<String, String> queryMap();

	default SERVICE service(Retrofit retrofit) {
		return retrofit.create(this.serviceClass());
	}
}
