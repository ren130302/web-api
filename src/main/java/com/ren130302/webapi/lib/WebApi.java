package com.ren130302.webapi.lib;

import java.util.function.Supplier;

import com.ren130302.webapi.lib.ApiClient.NoApiKey;
import com.ren130302.webapi.lib.ApiRequest.NoBuilder;
import com.ren130302.webapi.lib.interfaces.IApiClient;
import com.ren130302.webapi.lib.interfaces.IApiKey;
import com.ren130302.webapi.lib.interfaces.IApiRequest;
import com.ren130302.webapi.lib.interfaces.IApiService;
import com.ren130302.webapi.lib.interfaces.IQueryMap;
import com.ren130302.webapi.lib.interfaces.IWebApi;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;
import retrofit2.Call;
import retrofit2.Retrofit;

@Data
@Accessors(fluent = true)
public class WebApi<
	APIKEY extends IApiKey,
	QUERY extends IQueryMap,
	CLIENT extends IApiClient<APIKEY>,
	REQUEST extends IApiRequest<SERVICE>,
	SERVICE extends IApiService,
	RESPONSE>
	implements IWebApi<APIKEY, QUERY, CLIENT, REQUEST, SERVICE, RESPONSE> {

	private final @NonNull Supplier<CLIENT> clientSupplier;
	private final @NonNull Supplier<REQUEST> requestSupplier;
	private final @NonNull Supplier<QUERY> querySupplier;
	private Retrofit retrofit;
	private SERVICE service;
	private Call<RESPONSE> call;

	public static class NN<
		SERVICE,
		RESPONSE>
		extends WebApi<ApiKey.<SERVICE, RESPONSE>, NoBuilder<SERVICE, RESPONSE>, SERVICE, RESPONSE>
		implements IWebApi.NN<NoApiKey<SERVICE, RESPONSE>, NoBuilder<SERVICE, RESPONSE>, SERVICE, RESPONSE> {

		private NN(@NonNull Supplier<NoApiKey<SERVICE, RESPONSE>> clientSupplier,
				@NonNull Supplier<NoBuilder<SERVICE, RESPONSE>> requestSupplier) {
			super(clientSupplier, requestSupplier);
		}

		public static <
			SERVICE,
			RESPONSE> WebApi.NN<SERVICE, RESPONSE> of(Supplier<NoApiKey<SERVICE, RESPONSE>> clientSupplier, Supplier<NoBuilder<SERVICE, RESPONSE>> requestSupplier) {
			return new WebApi.NN<>(clientSupplier, requestSupplier);
		}

	}
}
