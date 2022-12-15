package com.ren130302.webapi.lib;

import com.ren130302.webapi.lib.interfaces.IApiKey;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

public final class ApiKey {

	public static ApiKey.No no() {
		return ApiKey.No.of();
	}

	public static ApiKey.Require require(String apiLabel, String apiKey) {
		return ApiKey.Require.of(apiLabel, apiKey);
	}

	@Value(staticConstructor = "of")
	@Accessors(fluent = true)
	public static class No
		implements IApiKey.No {}

	@Value(staticConstructor = "of")
	@Accessors(fluent = true)
	public static class Require
		implements IApiKey.Require<QueryMap.Require<?, ?>> {

		private final @NonNull String apiLabel;
		private final @NonNull String apiKey;
	}
}
