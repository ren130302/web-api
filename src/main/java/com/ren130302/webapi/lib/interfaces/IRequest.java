package com.ren130302.webapi.lib.interfaces;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface IRequest<
		CLIENT extends IApiClient,
		SERVICE extends IApiService,
		RESPONSE extends IResponse,
		BUILDER extends IBuilder<IRequest<CLIENT, SERVICE, RESPONSE, BUILDER>>> {

	public IRequest<CLIENT, SERVICE, RESPONSE, BUILDER> params(Consumer<BUILDER> params);

	public IRequest<CLIENT, SERVICE, RESPONSE, BUILDER> callback(Callback<RESPONSE> callback);

	public Consumer<BUILDER> getParams();

	public BiFunction<SERVICE, Map<String, String>, Call<RESPONSE>> getUrlMethod();

	public RESPONSE getResponse();

	public BUILDER getBuilder();

	public Class<SERVICE> getServiceClass();

	public Supplier<CLIENT> getClientSupplier();

	public default CLIENT getClient() {
		return this.getClientSupplier().get();
	}

	public default SERVICE getService() {
		return Instance.getInstance(this);
	}

	public Callback<RESPONSE> getCallback();

	public default Map<String, String> getQueryMap() {
		return this.getBuilder().initQuery().getQueryMap();
	}

	public default void execute() {
		this.getUrlMethod().apply(this.getService(), this.getQueryMap()).enqueue(this.getCallback());
	}

	public static class Instance {
		private static Retrofit mRetrofit = null;

		private static <REQUEST extends IRequest<?, SERVICE, ?, ?>, SERVICE extends IApiService> SERVICE getInstance(
				REQUEST request) {
			return singleton(request.getClient().getBaseUrl()).create(request.getServiceClass());
		}

		private static Retrofit singleton(String baseUrl) {
			if (mRetrofit == null) {
				mRetrofit = build(baseUrl);
			}

			return mRetrofit;
		}

		private static Retrofit build(String baseUrl) {
			return new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
					.build();
		}
	}
}