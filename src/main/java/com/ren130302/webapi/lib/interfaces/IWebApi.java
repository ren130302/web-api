package com.ren130302.webapi.lib.interfaces;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.ren130302.utils.Ticks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public interface IWebApi<
	CLIENT extends IApiClient,
	SERVICE,
	REQUEST extends IApiRequest<SERVICE>,
	RESPONSE,
	BUILDER extends IApiBuilder> {

	Supplier<CLIENT> clientSupplier();

	Supplier<REQUEST> requestSupplier();

	default void executeQuery(Supplier<String> apiKeySupplier, Consumer<BUILDER> paramsConsumer, Supplier<Callback<RESPONSE>> callbackSupplier) {
		CLIENT client = this.clientSupplier().get();
		REQUEST request = this.requestSupplier().get();
		Retrofit retrofit = client.retrofit();
		SERVICE service = retrofit.create(request.serviceClass());
		Map<String, String> queryMap = new HashMap<>();
		Callback<RESPONSE> callback = callbackSupplier.get();

		Optional<BUILDER> builderOptional = request.builderOptional();
		if (request instanceof IApiRequest.NoParams<SERVICE, RESPONSE>) {

		}
		else if (request instanceof IApiRequest.NeedParams<SERVICE, RESPONSE, BUILDER> t1) {

		}
		if (builderOptional.isPresent()) {
			BUILDER builder = builderOptional.get();
			queryMap.putAll(builder.initQuery(paramsConsumer));
			System.out.print(this.print(retrofit, client, queryMap));

		}

		if (Objects.nonNull(client.apiLabel())) {
			queryMap.put(client.apiLabel(), apiKeySupplier.get());
		}

		Call<RESPONSE> call = request.urlMethod().apply(service, queryMap);
		call.enqueue(callback);

		Ticks.tick(100L, call, c -> call.timeout().hasDeadline(), c -> {
			System.out.println("wait for seconds...");
		});
	}

	default String print(Retrofit retrofit, CLIENT client, Map<String, String> queryMap) {
		StringBuffer buf = new StringBuffer();

		buf.append(String.format("baseUrl : %s \n", retrofit.baseUrl()));
		buf.append(String.format("%s : %s \n", client.apiLabel(), "***"));
		queryMap.forEach((k, v) -> buf.append(String.format("%s : %s \n", k, v)));

		return buf.toString();
	}
}
