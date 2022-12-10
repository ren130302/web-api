package com.ren130302.webapi.lib.interfaces;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import retrofit2.Call;

public interface IApiRequest<
	SERVICE extends IApiService,
	RESPONSE extends IResponse,
	BUILDER extends IBuilder> {

	default RESPONSE response() {
		return this.responseSupplier().get();
	}

	default BUILDER builder() {
		return this.builderSupplier().get();
	}

	Class<SERVICE> serviceClass();

	Supplier<RESPONSE> responseSupplier();

	Supplier<BUILDER> builderSupplier();

	BiFunction<SERVICE, Map<String, String>, Call<RESPONSE>> urlMethod();

}