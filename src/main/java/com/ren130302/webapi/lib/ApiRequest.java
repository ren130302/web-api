package com.ren130302.webapi.lib;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.ren130302.webapi.lib.interfaces.IApiRequest;
import com.ren130302.webapi.lib.interfaces.IApiService;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;
import retrofit2.Call;

@Value(staticConstructor = "of")
@Accessors(fluent = true)
public class ApiRequest<
	SERVICE extends IApiService,
	RESPONSE,
	BUILDER extends ApiBuilder>
	implements IApiRequest<SERVICE, RESPONSE, BUILDER> {

	private final @NonNull Class<SERVICE> serviceClass;
	private final @NonNull BiFunction<SERVICE, Map<String, String>, Call<RESPONSE>> urlMethod;
	private final @NonNull Optional<BUILDER> builderOptional;

	private final Map<String, String> queryMap = new HashMap<>();

	public static <
		SERVICE extends IApiService,
		RESPONSE,
		BUILDER extends ApiBuilder> ApiRequest<SERVICE, RESPONSE, BUILDER> of(@NonNull Class<SERVICE> serviceClass, @NonNull BiFunction<SERVICE, Map<String, String>, Call<RESPONSE>> urlMethod, BUILDER builder) {
		return of(serviceClass, urlMethod, Optional.ofNullable(builder));
	}

	public static <
		SERVICE extends IApiService,
		RESPONSE,
		BUILDER extends ApiBuilder> ApiRequest<SERVICE, RESPONSE, BUILDER> of(@NonNull Class<SERVICE> serviceClass, @NonNull Function<SERVICE, Call<RESPONSE>> urlMethod) {
		return of(serviceClass, (s, q) -> urlMethod.apply(s), Optional.empty());
	}
}
