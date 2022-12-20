package com.ren130302.webapi.lib.interfaces;

import java.util.Map;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Retrofit;

public interface IWebApi<
	CLIENT extends IApiClient,
	REQUEST extends IApiRequest<SERVICE, RESPONSE, BUILDER>,
	SERVICE extends IApiService,
	RESPONSE,
	BUILDER extends IApiBuilder> {

	CLIENT client();

	REQUEST request();

	default Call<RESPONSE> ready() {
		return this.ready(null, null);
	}

	default Call<RESPONSE> ready(Consumer<BUILDER> builderConsumer) {
		return this.ready(null, builderConsumer);
	}

	default Call<RESPONSE> executeQuery(String apiKey) {
		return this.ready(apiKey, null);
	}

	default Call<RESPONSE> ready(String apiKey, Consumer<BUILDER> builderConsumer) {
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
		return call;
	}
}
