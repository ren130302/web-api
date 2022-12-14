package com.ren130302.webapi.lib;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.ren130302.webapi.lib.interfaces.IApiRequest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;
import retrofit2.Call;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiRequest {

	public static <
		SERVICE,
		RESPONSE> NoParams<SERVICE, RESPONSE> noParams(Class<SERVICE> serviceClass, Function<SERVICE, Call<RESPONSE>> urlMethod) {
		return NoParams.of(serviceClass, urlMethod);
	}

	public static <
		SERVICE,
		RESPONSE,
		BUILDER extends ApiBuilder> NeedParams<SERVICE, RESPONSE, BUILDER> needParams(Class<SERVICE> serviceClass, BiFunction<SERVICE, Map<String, String>, Call<RESPONSE>> urlMethod, Optional<BUILDER> builderOptional) {
		return NeedParams.of(serviceClass, urlMethod, builderOptional);
	}

	@Value(staticConstructor = "of")
	@Accessors(fluent = true)
	private static class NoParams<
		SERVICE,
		RESPONSE>
		implements IApiRequest.NoParams<SERVICE, RESPONSE> {

		private final @NonNull Class<SERVICE> serviceClass;
		private final @NonNull Function<SERVICE, Call<RESPONSE>> urlMethod;

	}

	@Value(staticConstructor = "of")
	@Accessors(fluent = true)
	private static class NeedParams<
		SERVICE,
		RESPONSE,
		BUILDER extends ApiBuilder>
		implements IApiRequest.NeedParams<SERVICE, RESPONSE, BUILDER> {

		private final @NonNull Class<SERVICE> serviceClass;
		private final @NonNull BiFunction<SERVICE, Map<String, String>, Call<RESPONSE>> urlMethod;
		private final @NonNull Optional<BUILDER> builderOptional;

	}
}
