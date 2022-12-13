package com.ren130302.webapi.lib;

import java.util.function.Function;

import com.ren130302.webapi.lib.interfaces.IApiClient;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;
import retrofit2.Retrofit;

@Value(staticConstructor = "set")
@Accessors(fluent = true)
public final class ApiClient
	implements IApiClient {

	private final String apiLabel;
	private final @NonNull Function<Retrofit.Builder, Retrofit.Builder> retrofitBuilderFunction;

	public static ApiClient set(Function<Retrofit.Builder, Retrofit.Builder> retrofitBuilderFunction) {
		return set(null, retrofitBuilderFunction);
	}
}