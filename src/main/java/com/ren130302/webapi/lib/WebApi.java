package com.ren130302.webapi.lib;

import java.util.function.Function;
import java.util.function.Supplier;

import com.ren130302.webapi.lib.interfaces.IApiService;
import com.ren130302.webapi.lib.interfaces.IBuilder;
import com.ren130302.webapi.lib.interfaces.IResponse;
import com.ren130302.webapi.lib.interfaces.IWebApi;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// @Value(staticConstructor = "set")
@Getter
@Accessors(fluent = true)
public final class WebApi<
	SERVICE extends IApiService,
	RESPONSE extends IResponse,
	BUILDER extends IBuilder>
	implements
	IWebApi<ApiClient, SERVICE, ApiRequest<SERVICE, RESPONSE, BUILDER>, RESPONSE, BUILDER> {

	private final @NonNull Function<String, ApiClient> clientFunction;
	private final @NonNull Supplier<ApiRequest<SERVICE, RESPONSE, BUILDER>> requestSupplier;
	private final @NonNull ParamSupplier<RESPONSE, BUILDER> paramSupplier;

	private final @NonNull ApiClient client;
	private final @NonNull SERVICE service;

	private WebApi(Function<String, ApiClient> clientFunction,
			Supplier<ApiRequest<SERVICE, RESPONSE, BUILDER>> requestSupplier,
			ParamSupplier<RESPONSE, BUILDER> executor) {
		this.clientFunction = clientFunction;
		this.requestSupplier = requestSupplier;
		this.paramSupplier = executor;
		this.client = this.clientFunction().apply(this.paramSupplier().apiKeySupplier().get());
		this.service = WebApi.getInstance(this);
	}

	public static <
		SERVICE extends IApiService,
		RESPONSE extends IResponse,
		BUILDER extends IBuilder> WebApi<SERVICE, RESPONSE, BUILDER> set(Function<String, ApiClient> clientFunction, Supplier<ApiRequest<SERVICE, RESPONSE, BUILDER>> requestFunction, ParamSupplier<RESPONSE, BUILDER> executor) {
		return new WebApi<>(clientFunction, requestFunction, executor);
	}

	public static <
		SERVICE extends IApiService,
		RESPONSE extends IResponse,
		BUILDER extends IBuilder> void executeQuery(Function<ParamSupplier<RESPONSE, BUILDER>, WebApi<SERVICE, RESPONSE, BUILDER>> function, ParamSupplier<RESPONSE, BUILDER> paramSupplier) {
		function.apply(paramSupplier).executeQuery();
	}

	private static Retrofit mRetrofit;

	private static <
		SERVICE extends IApiService> SERVICE getInstance(WebApi<SERVICE, ?, ?> webApi) {
		return getInstance(webApi.client().baseUrl(), webApi.request().serviceClass());
	}

	private static <
		SERVICE extends IApiService> SERVICE getInstance(String baseUrl, Class<SERVICE> serviceClass) {
		return singleton(baseUrl).create(serviceClass);
	}

	private static Retrofit singleton(String baseUrl) {
		if (mRetrofit == null) {
			mRetrofit = build(baseUrl);
		}

		return mRetrofit;
	}

	private static Retrofit build(String baseUrl) {
		return new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
	}
}
