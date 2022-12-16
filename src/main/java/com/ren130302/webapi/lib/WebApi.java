package com.ren130302.webapi.lib;

import com.ren130302.webapi.lib.interfaces.IApiService;
import com.ren130302.webapi.lib.interfaces.IWebApi;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

@Value(staticConstructor = "set")
@Accessors(fluent = true)
public class WebApi<
	SERVICE extends IApiService,
	RESPONSE,
	BUILDER extends ApiBuilder>
	implements
	IWebApi<ApiClient, ApiRequest<SERVICE, RESPONSE, BUILDER>, SERVICE, RESPONSE, BUILDER> {

	private final @NonNull ApiClient client;
	private final @NonNull ApiRequest<SERVICE, RESPONSE, BUILDER> request;
}
