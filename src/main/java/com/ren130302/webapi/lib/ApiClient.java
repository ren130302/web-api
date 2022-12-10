package com.ren130302.webapi.lib;

import com.ren130302.webapi.lib.interfaces.IApiClient;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data(staticConstructor = "set")
@Accessors(fluent = true)
public final class ApiClient
	implements IApiClient {

	private final @NonNull String baseUrl;
	private final @NonNull String apiLabel;

}