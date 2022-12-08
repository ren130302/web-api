package com.ren130302.webapi.lib.interfaces;

import java.util.Collections;
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
		BUILDER extends IBuilder> {

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
		return Instance.getInstance(this.getClient().getBaseUrl(), this.getServiceClass());
	}

	public Callback<RESPONSE> getCallback();

	public default Map<String, String> getQueryMap() {
		return this.getBuilder().getQueryMap();
	}

	public default IRequest<CLIENT, SERVICE, RESPONSE, BUILDER> putApi() {
		this.getQueryMap().put(this.getClient().getApiKeyLabel(), this.getClient().getApiKeyValue());
		return this;
	}

	public default IRequest<CLIENT, SERVICE, RESPONSE, BUILDER> putItem() {
		this.getParams().accept(this.getBuilder());
		return this;
	}

	public default IRequest<CLIENT, SERVICE, RESPONSE, BUILDER> removeItem() {
		this.getQueryMap().values().removeAll(Collections.singleton(null));
		this.getQueryMap().values().removeAll(Collections.singleton("null"));
		return this;
	}

	public default IRequest<CLIENT, SERVICE, RESPONSE, BUILDER> queryMap() {
		this.putApi().putItem().removeItem();
		return this;
	}

	public default void execute() {
		this.getUrlMethod().apply(this.getService(), this.getQueryMap()).enqueue(this.getCallback());
	}

	public default void queryExecute() {
		this.queryMap().execute();
	}

	public static class Instance {
		private static Retrofit mRetrofit = null;

		private static <T extends IApiService> T getInstance(String baseUrl, Class<T> clazz) {
			return singleton(baseUrl).create(clazz);
		}

		private static Retrofit singleton(String baseUrl) {
			if (mRetrofit == null) {
				mRetrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
						.build();
			}

			return mRetrofit;
		}
	}
}