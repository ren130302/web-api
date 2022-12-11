package com.ren130302.webapi.lib.interfaces;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import retrofit2.Call;
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
		BUILDER builder = request.builder();
		Retrofit retrofit = RetrofitInstance.singleton(client.baseUrl());
		SERVICE service = retrofit.create(request.serviceClass());

		Callback<RESPONSE> callback = callbackSupplier.get();
		Map<String, String> queryMap = builder.initQuery(paramsConsumer);
		System.out.print(this.print(retrofit, client, queryMap));

		queryMap.put(client.apiLabel(), apiKeySupplier.get());
		Call<RESPONSE> call = request.urlMethod().apply(service, queryMap);
		call.enqueue(callback);

		// Ticks.tick(1000L, call, c -> call.timeout().hasDeadline(), c -> {
		// System.out.println("out");
		// });

	}

	default String print(Retrofit retrofit, CLIENT client, Map<String, String> queryMap) {
		StringBuffer buf = new StringBuffer();
		buf.append(String.format("baseUrl : %s \n", retrofit.baseUrl()));
		buf.append(String.format("%s : %s \n", client.apiLabel(), "***"));
		queryMap.forEach((k, v) -> buf.append(String.format("%s : %s \n", k, v)));
		return buf.toString();
	}

	public static class RetrofitInstance {
		private static Retrofit mRetrofit;

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
