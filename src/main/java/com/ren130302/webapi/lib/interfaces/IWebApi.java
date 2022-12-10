package com.ren130302.webapi.lib.interfaces;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import lombok.NonNull;
import retrofit2.Callback;

public interface IWebApi<
	CLIENT extends IApiClient,
	SERVICE extends IApiService,
	REQUEST extends IApiRequest<SERVICE, RESPONSE, BUILDER>,
	RESPONSE extends IResponse,
	BUILDER extends IBuilder> {

	@NonNull
	Function<String, CLIENT> clientFunction();

	@NonNull
	Supplier<REQUEST> requestSupplier();

	@NonNull
	default REQUEST request() {
		return this.requestSupplier().get();
	}

	@NonNull
	CLIENT client();

	@NonNull
	SERVICE service();

	@NonNull
	IParamSupplier<RESPONSE, BUILDER> paramSupplier();

	default void executeQuery() {
		CLIENT client = this.client();
		REQUEST request = this.request();
		BUILDER builder = request.builder();

		Consumer<BUILDER> params = this.paramSupplier().paramsConsumer();
		Callback<RESPONSE> callback = this.paramSupplier().callbackSupplier().get();

		Map<String, String> queryMap = builder.initQuery(params);
		System.out.print(this.print(client, queryMap));

		client.putApi(queryMap);
		request.urlMethod().apply(this.service(), queryMap).enqueue(callback);

	}

	default String print(CLIENT client, Map<String, String> queryMap) {
		StringBuffer buf = new StringBuffer();
		buf.append(String.format("baseUrl : %s \n", client.baseUrl()));
		buf.append(String.format("%s : %s \n", client.apiLabel(), "***"));
		queryMap.forEach((k, v) -> buf.append(String.format("%s : %s \n", k, v)));
		return buf.toString();
	}
}
