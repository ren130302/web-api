package com.ren130302.webapi.lib;

import java.util.Optional;

import com.ren130302.webapi.lib.interfaces.IApiRequest;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

public final class ApiRequest {

	public static <
		SERVICE,
		RESPONSE> NoBuilder<SERVICE, RESPONSE> noParams(Class<SERVICE> serviceClass) {
		return NoBuilder.of(serviceClass);
	}

	public static <
		SERVICE,
		RESPONSE,
		BUILDER extends ApiBuilder> RequireBuilder<SERVICE, RESPONSE, BUILDER> needParams(Class<SERVICE> serviceClass, Optional<BUILDER> builderOptional) {
		return RequireBuilder.of(serviceClass, builderOptional);
	}

	@Value(staticConstructor = "of")
	@Accessors(fluent = true)
	public static class NoBuilder<
		SERVICE,
		RESPONSE>
		implements IApiRequest.NoBuilder<SERVICE,> {

		private final @NonNull Class<SERVICE> serviceClass;

	}

	@Value(staticConstructor = "of")
	@Accessors(fluent = true)
	public static class RequireBuilder<
		SERVICE,
		RESPONSE,
		BUILDER extends ApiBuilder>
		implements IApiRequest.RequireBuilder<SERVICE, RESPONSE, BUILDER> {

		private final @NonNull Class<SERVICE> serviceClass;
		private final @NonNull BUILDER builder;

	}
}
