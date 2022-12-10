package com.ren130302.webapi.lib.interfaces;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface IWebApi<
	CLIENT extends IApiClient,
	SERVICE extends IApiService,
	REQUEST extends IApiRequest<SERVICE, RESPONSE, BUILDER>,
	RESPONSE extends IResponse,
	BUILDER extends IBuilder> {

	Supplier<CLIENT> clientSupplier();

	Supplier<REQUEST> requestSupplier();

	default void executeQuery(Supplier<String> apiKeySupplier, Consumer<BUILDER> paramsConsumer, Supplier<Callback<RESPONSE>> callbackSupplier) {
		CLIENT client = this.clientSupplier().get();
		REQUEST request = this.requestSupplier().get();
		SERVICE service = Instance.getInstance(client.baseUrl(), request.serviceClass());
		BUILDER builder = request.builder();

		Callback<RESPONSE> callback = callbackSupplier.get();
		Map<String, String> queryMap = builder.initQuery(paramsConsumer);
		System.out.print(this.print(client, queryMap));

		queryMap.put(client.apiLabel(), apiKeySupplier.get());
		request.urlMethod().apply(service, queryMap).enqueue(callback);

	}

	default String print(CLIENT client, Map<String, String> queryMap) {
		StringBuffer buf = new StringBuffer();
		buf.append(String.format("baseUrl : %s \n", client.baseUrl()));
		buf.append(String.format("%s : %s \n", client.apiLabel(), "***"));
		queryMap.forEach((k, v) -> buf.append(String.format("%s : %s \n", k, v)));
		return buf.toString();
	}

	public static class Instance {
		private static Retrofit mRetrofit;

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
}
