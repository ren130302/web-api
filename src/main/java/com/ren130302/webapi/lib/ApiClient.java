package com.ren130302.webapi.lib;

import java.util.function.Function;
import java.util.function.Supplier;

import com.ren130302.webapi.lib.interfaces.IApiClient;
import com.ren130302.webapi.lib.interfaces.IApiKey;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;
import retrofit2.Retrofit;

@Value(staticConstructor = "of")
@Accessors(fluent = true)
public final class ApiClient<
	APIKEY extends IApiKey>
	implements IApiClient<APIKEY> {

	private final @NonNull Function<Retrofit.Builder, Retrofit.Builder> retrofitBuilderFunction;
	private final @NonNull Supplier<APIKEY> apiKeySupplier;
}