package com.ren130302.webapi.lib.interfaces;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import retrofit2.Call;

public interface IApiRequest<
	SERVICE> {

	Class<SERVICE> serviceClass();

	public interface NoParams<
		SERVICE,
		RESPONSE>
		extends IApiRequest<SERVICE> {

		Function<SERVICE, Call<RESPONSE>> urlMethod();
	}

	public interface NeedParams<
		SERVICE,
		RESPONSE,
		BUILDER extends IApiBuilder>
		extends IApiRequest<SERVICE> {

		BiFunction<SERVICE, Map<String, String>, Call<RESPONSE>> urlMethod();

		Optional<BUILDER> builderOptional();
	}
}
