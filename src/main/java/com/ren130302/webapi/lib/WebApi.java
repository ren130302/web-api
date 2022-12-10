package com.ren130302.webapi.lib;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.ren130302.webapi.lib.interfaces.IApiService;
import com.ren130302.webapi.lib.interfaces.IBuilder;
import com.ren130302.webapi.lib.interfaces.IResponse;
import com.ren130302.webapi.lib.interfaces.IWebApi;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;
import retrofit2.Callback;

@Value(staticConstructor = "set")
@Accessors(fluent = true)
public final class WebApi<
	SERVICE extends IApiService,
	RESPONSE extends IResponse,
	BUILDER extends IBuilder>
	implements
	IWebApi<ApiClient, SERVICE, ApiRequest<SERVICE, RESPONSE, BUILDER>, RESPONSE, BUILDER> {

	private final @NonNull Supplier<ApiClient> clientSupplier;
	private final @NonNull Supplier<ApiRequest<SERVICE, RESPONSE, BUILDER>> requestSupplier;

	private WebApi(Supplier<ApiClient> clientSupplier,
			Supplier<ApiRequest<SERVICE, RESPONSE, BUILDER>> requestSupplier) {
		this.clientSupplier = clientSupplier;
		this.requestSupplier = requestSupplier;
	}

	public static <
		SERVICE extends IApiService,
		RESPONSE extends IResponse,
		BUILDER extends IBuilder> void executeQuery(Supplier<WebApi<SERVICE, RESPONSE, BUILDER>> function, Supplier<String> apiKeySupplier, Consumer<BUILDER> paramsConsumer, Supplier<Callback<RESPONSE>> callbackSupplier) {
		function.get().executeQuery(apiKeySupplier, paramsConsumer, callbackSupplier);
	}
}
