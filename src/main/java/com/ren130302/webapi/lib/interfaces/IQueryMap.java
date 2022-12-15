package com.ren130302.webapi.lib.interfaces;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import retrofit2.Call;

public interface IQueryMap {
	public interface No<
		SERVICE extends IApiService,
		RESPONSE>
		extends IQueryMap {

		Function<SERVICE, Call<RESPONSE>> urlMethod();
	}

	public interface Require<
		SERVICE extends IApiService,
		RESPONSE>
		extends IQueryMap {

		BiFunction<SERVICE, Map<String, String>, Call<RESPONSE>> urlMethod();

		Map<String, String> queryMap();
	}
}
