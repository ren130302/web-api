package com.ren130302.webapi.lib.interfaces;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public interface IWebApi<
	APIKEY extends IApiKey,
	QUERY extends IQueryMap,
	CLIENT extends IApiClient<APIKEY>,
	REQUEST extends IApiRequest<SERVICE>,
	SERVICE extends IApiService,
	RESPONSE> {

	Supplier<CLIENT> clientSupplier();

	Supplier<REQUEST> requestSupplier();

	Retrofit retrofit();

	SERVICE service();

	Call<RESPONSE> call();

	Supplier<QUERY> querySupplier();

	public interface NN<
		APIKEY extends IApiKey.No,
		QUERY extends IQueryMap.No<SERVICE, RESPONSE>,
		CLIENT extends IApiClient<APIKEY>,
		REQUEST extends IApiRequest.NoBuilder<SERVICE>,
		SERVICE extends IApiService,
		RESPONSE>
		extends IWebApi<APIKEY, QUERY, CLIENT, REQUEST, SERVICE, RESPONSE> {

		default void executeQuery(Callback<RESPONSE> callback) {
			CLIENT client = this.clientSupplier().get();
			REQUEST request = this.requestSupplier().get();
			QUERY query = this.querySupplier().get();

			Retrofit retrofit = client.retrofit();
			SERVICE service = request.service(retrofit);
			Call<RESPONSE> call = query.urlMethod().apply(service);

			call.enqueue(callback);
		}
	}

	public interface RN<
		APIKEY extends IApiKey.Require<QUERY>,
		QUERY extends IQueryMap.Require<SERVICE, RESPONSE>,
		CLIENT extends IApiClient<APIKEY>,
		REQUEST extends IApiRequest.NoBuilder<SERVICE>,
		SERVICE extends IApiService,
		RESPONSE>
		extends IWebApi<APIKEY, QUERY, CLIENT, REQUEST, SERVICE, RESPONSE> {

		default void executeQuery(Callback<RESPONSE> callback) {
			CLIENT client = this.clientSupplier().get();
			REQUEST request = this.requestSupplier().get();
			APIKEY apiKey = client.apiKeySupplier().get();
			QUERY query = this.querySupplier().get();

			Retrofit retrofit = client.retrofit();
			SERVICE service = request.service(retrofit);

			Map<String, String> queryMap = query.queryMap();
			apiKey.putApi(query);

			Call<RESPONSE> call = query.urlMethod().apply(service, queryMap);

			call.enqueue(callback);
		}
	}

	public interface NR<
		APIKEY extends IApiKey.No,
		QUERY extends IQueryMap.Require<SERVICE, RESPONSE>,
		CLIENT extends IApiClient<APIKEY>,
		REQUEST extends IApiRequest.RequireBuilder<SERVICE, BUILDER>,
		SERVICE extends IApiService,
		RESPONSE,
		BUILDER extends IApiBuilder<BUILDER>>
		extends IWebApi<APIKEY, QUERY, CLIENT, REQUEST, SERVICE, RESPONSE> {

		default void executeQuery(Consumer<BUILDER> builderConsumer, Callback<RESPONSE> callback) {
			CLIENT client = this.clientSupplier().get();
			REQUEST request = this.requestSupplier().get();
			QUERY query = this.querySupplier().get();

			Retrofit retrofit = client.retrofit();
			SERVICE service = request.service(retrofit);
			BUILDER builder = request.builder();

			// initialize queryMap
			Map<String, String> queryMap = query.queryMap();

			builder.initQueryMap(builderConsumer);

			// ready to call service
			Call<RESPONSE> call = query.urlMethod().apply(service, queryMap);

			// call service
			call.enqueue(callback);
		}
	}

	public interface RR<
		APIKEY extends IApiKey.Require<QUERY>,
		QUERY extends IQueryMap.Require<SERVICE, RESPONSE>,
		CLIENT extends IApiClient<APIKEY>,
		REQUEST extends IApiRequest.RequireBuilder<SERVICE, BUILDER>,
		SERVICE extends IApiService,
		RESPONSE,
		BUILDER extends IApiBuilder<BUILDER>>
		extends IWebApi<APIKEY, QUERY, CLIENT, REQUEST, SERVICE, RESPONSE> {

		BUILDER builder();

		default void executeQuery(Consumer<BUILDER> builderConsumer, Callback<RESPONSE> callback) {
			CLIENT client = this.clientSupplier().get();
			REQUEST request = this.requestSupplier().get();
			APIKEY apiKey = client.apiKeySupplier().get();
			QUERY query = this.querySupplier().get();

			Retrofit retrofit = client.retrofit();
			SERVICE service = request.service(retrofit);
			BUILDER builder = request.builder();

			// initialize queryMap
			Map<String, String> queryMap = query.queryMap();
			apiKey.putApi(query);
			builder.initQueryMap(builderConsumer);

			// ready to call service
			Call<RESPONSE> call = query.urlMethod().apply(service, queryMap);

			// call service
			call.enqueue(callback);
		}
	}
}
