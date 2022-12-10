package com.ren130302.webapi.lib;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import com.ren130302.webapi.lib.interfaces.IApiRequest;
import com.ren130302.webapi.lib.interfaces.IApiService;
import com.ren130302.webapi.lib.interfaces.IBuilder;
import com.ren130302.webapi.lib.interfaces.IResponse;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;
import retrofit2.Call;

@Value(staticConstructor = "create")
@Accessors(fluent = true)
public final class ApiRequest<
	SERVICE extends IApiService,
	RESPONSE extends IResponse,
	BUILDER extends IBuilder>
	implements IApiRequest<SERVICE, RESPONSE, BUILDER> {

	private final @NonNull Class<SERVICE> serviceClass;
	private final @NonNull Supplier<BUILDER> builderSupplier;
	private final @NonNull Supplier<RESPONSE> responseSupplier;
	private final @NonNull BiFunction<SERVICE, Map<String, String>, Call<RESPONSE>> urlMethod;
}
