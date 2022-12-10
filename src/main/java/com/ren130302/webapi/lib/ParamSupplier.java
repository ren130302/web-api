package com.ren130302.webapi.lib;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.ren130302.webapi.lib.interfaces.IBuilder;
import com.ren130302.webapi.lib.interfaces.IParamSupplier;
import com.ren130302.webapi.lib.interfaces.IResponse;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;
import retrofit2.Callback;

@Value(staticConstructor = "of")
@Accessors(fluent = true)
public class ParamSupplier<
	RESPONSE extends IResponse,
	BUILDER extends IBuilder>
	implements IParamSupplier<RESPONSE, BUILDER> {
	private final @NonNull Supplier<String> apiKeySupplier;
	private final @NonNull Consumer<BUILDER> paramsConsumer;
	private final @NonNull Supplier<Callback<RESPONSE>> callbackSupplier;

	public static <
		RESPONSE extends IResponse,
		BUILDER extends IBuilder> ParamSupplier<RESPONSE, BUILDER> of(Supplier<String> apiKeySupplier, Consumer<BUILDER> paramsConsumer) {
		return of(apiKeySupplier, paramsConsumer, CallbackImpl::empty);
	}
}
