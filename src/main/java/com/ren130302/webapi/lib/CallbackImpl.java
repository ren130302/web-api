package com.ren130302.webapi.lib;

import java.util.function.Consumer;

import com.ren130302.webapi.lib.interfaces.ExtendsCallback;
import com.ren130302.webapi.lib.interfaces.IResponse;

import lombok.Value;
import lombok.experimental.Accessors;

@Value(staticConstructor = "of")
@Accessors(fluent = true)
public class CallbackImpl<
	RESPONSE extends IResponse>
	implements ExtendsCallback<RESPONSE> {

	private final Consumer<RESPONSE> onSuccess;
	private final Consumer<Throwable> onFailure;

	public static <
		RESPONSE extends IResponse> CallbackImpl<RESPONSE> empty() {
		return of(r -> {}, t -> {});
	}

	public static <
		RESPONSE extends IResponse> CallbackImpl<RESPONSE> of(Consumer<RESPONSE> onSuccess) {
		return of(onSuccess, t -> {});
	}
}
