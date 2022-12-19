package com.ren130302.webapi.lib.interfaces;

import java.util.Map;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public interface IWebApi<
	CLIENT extends IApiClient,
	REQUEST extends IApiRequest<SERVICE, RESPONSE, BUILDER>,
	SERVICE extends IApiService,
	RESPONSE,
	BUILDER extends IApiBuilder> {

	CLIENT client();

	REQUEST request();

	default void executeQuery(Callback<RESPONSE> callback) {
		this.executeQuery(null, null, callback);
	}

	default void executeQuery(Consumer<BUILDER> builderConsumer, Callback<RESPONSE> callback) {
		this.executeQuery(null, builderConsumer, callback);
	}

	default void executeQuery(String apiKey, Callback<RESPONSE> callback) {
		this.executeQuery(apiKey, null, callback);
	}

	default void executeQuery(String apiKey, Consumer<BUILDER> builderConsumer, Callback<RESPONSE> callback) {
		CLIENT client = this.client();
		REQUEST request = this.request();

		Retrofit retrofit = client.retrofit();
		SERVICE service = request.service(retrofit);

		// initialize queryMap
		Map<String, String> queryMap = request.queryMap();
		request.builderOptional().ifPresent(builder -> queryMap.putAll(builder.initQueryMap(builderConsumer)));
		client.apiLabelOptional().ifPresent(apiLabel -> queryMap.put(apiLabel, apiKey));

		// ready to call service
		Call<RESPONSE> call = request.urlMethod().apply(service, queryMap);
		call.enqueue(callback);
	}
}
