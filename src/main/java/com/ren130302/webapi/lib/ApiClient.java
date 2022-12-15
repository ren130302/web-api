package com.ren130302.webapi.lib;

import java.util.Optional;
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

	private final @NonNull Optional<String> apiLabelOptional;
	private final @NonNull Function<Retrofit.Builder, Retrofit.Builder> retrofitBuilderFunction;

	public static ApiClient set(String apiLabel, Function<Retrofit.Builder, Retrofit.Builder> retrofitBuilderFunction) {
		return set(Optional.ofNullable(apiLabel), retrofitBuilderFunction);
	}

	public static ApiClient set(Function<Retrofit.Builder, Retrofit.Builder> retrofitBuilderFunction) {
		return set(Optional.empty(), retrofitBuilderFunction);
	}
}