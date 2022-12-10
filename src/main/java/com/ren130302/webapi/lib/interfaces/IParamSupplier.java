package com.ren130302.webapi.lib.interfaces;

import java.util.function.Consumer;
import java.util.function.Supplier;

import retrofit2.Callback;

public interface IParamSupplier<
	RESPONSE extends IResponse,
	BUILDER extends IBuilder> {

	Supplier<String> apiKeySupplier();

	Consumer<BUILDER> paramsConsumer();

	Supplier<Callback<RESPONSE>> callbackSupplier();
}
