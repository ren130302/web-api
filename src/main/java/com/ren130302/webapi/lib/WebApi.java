package com.ren130302.webapi.lib;

import java.util.function.Supplier;

import com.ren130302.webapi.lib.interfaces.IApiClient;
import com.ren130302.webapi.lib.interfaces.IRequest;

import lombok.Data;
import lombok.NonNull;

@Data
public class WebApi implements IApiClient {

	private final @NonNull String apiKeyLabel;
	private final @NonNull String apiKeyValue;
	private final @NonNull String baseUrl;

	public static <REQUEST extends IRequest<?, ?, ?, ?>> REQUEST request(Supplier<REQUEST> supplier) {
		return supplier.get();
	}
}