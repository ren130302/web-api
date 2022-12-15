package com.ren130302.webapi.lib;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.ren130302.webapi.lib.interfaces.IApiService;
import com.ren130302.webapi.lib.interfaces.IQueryMap;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;
import retrofit2.Call;

public final class QueryMap {

	@Value(staticConstructor = "create")
	@Accessors(fluent = true)
	public static class No<
		SERVICE extends IApiService,
		RESPONSE>
		implements IQueryMap.No<SERVICE, RESPONSE> {

		private final @NonNull Function<SERVICE, Call<RESPONSE>> urlMethod;
	}

	@Value(staticConstructor = "create")
	@Accessors(fluent = true)
	public static class Require<
		SERVICE extends IApiService,
		RESPONSE>
		implements IQueryMap.Require<SERVICE, RESPONSE> {

		private final @NonNull BiFunction<SERVICE, Map<String, String>, Call<RESPONSE>> urlMethod;
		private final Map<String, String> queryMap = new HashMap<>();
	}
}
