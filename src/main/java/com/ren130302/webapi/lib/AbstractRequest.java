package com.ren130302.webapi.lib;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.ren130302.webapi.lib.interfaces.IApiClient;
import com.ren130302.webapi.lib.interfaces.IApiService;
import com.ren130302.webapi.lib.interfaces.IBuilder;
import com.ren130302.webapi.lib.interfaces.IRequest;
import com.ren130302.webapi.lib.interfaces.IResponse;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import retrofit2.Callback;

@Data
@Accessors(chain = true)

@RequiredArgsConstructor
public abstract class AbstractRequest<
		CLIENT extends IApiClient,
		SERVICE extends IApiService,
		RESPONSE extends IResponse,
		BUILDER extends IBuilder> implements IRequest<CLIENT, SERVICE, RESPONSE, BUILDER> {

	private final @NonNull Supplier<CLIENT> clientSupplier;
	private @NonNull Consumer<BUILDER> params = builder -> {
	};
	private @NonNull Callback<RESPONSE> callback = CallbackImpl.empty();

	@Override
	public IRequest<CLIENT, SERVICE, RESPONSE, BUILDER> callback(Callback<RESPONSE> callback) {
		this.callback = callback;
		return this;
	}

	@Override
	public IRequest<CLIENT, SERVICE, RESPONSE, BUILDER> params(Consumer<BUILDER> params) {
		this.params = params;
		return this;
	}
}
