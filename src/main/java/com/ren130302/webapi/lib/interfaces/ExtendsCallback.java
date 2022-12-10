package com.ren130302.webapi.lib.interfaces;

import java.net.HttpURLConnection;
import java.util.Objects;
import java.util.function.Consumer;

import lombok.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface ExtendsCallback<
	RESPONSE extends IResponse>
	extends Callback<RESPONSE> {

	/**
	 * Executed only for HTTP Status-Code 200.
	 */
	@NonNull
	Consumer<RESPONSE> onSuccess();

	/**
	 * Executed only for non HTTP Status-Code 200.
	 */
	@NonNull
	Consumer<Throwable> onFailure();

	@Override
	default void onResponse(Call<RESPONSE> call, Response<RESPONSE> response) {
		final RESPONSE responseBody = response.code() == HttpURLConnection.HTTP_OK ? response.body() : null;

		if (Objects.nonNull(responseBody)) {
			this.onSuccess().accept(responseBody);
		}
		else {
			try {
				throw new Throwable(response.errorBody().string());
			}
			catch (Throwable e) {
				this.onFailure(call, e);
				e.printStackTrace();
			}
		}
	}

	@Override
	default void onFailure(Call<RESPONSE> call, Throwable t) {
		this.onFailure().accept(t);
	}
}
