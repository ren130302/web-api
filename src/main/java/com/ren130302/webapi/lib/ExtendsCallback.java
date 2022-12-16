package com.ren130302.webapi.lib;

import java.net.HttpURLConnection;
import java.util.Objects;
import java.util.function.BiConsumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExtendsCallback {

	public static <
		RESPONSE> Callback<RESPONSE> onResponse(BiConsumer<Call<RESPONSE>, Response<RESPONSE>> responseConsumer, BiConsumer<Call<RESPONSE>, Throwable> failureConsumer) {
		return new Callback<>() {
			@Override
			public void onResponse(Call<RESPONSE> call, Response<RESPONSE> response) {
				responseConsumer.accept(call, response);
			}

			@Override
			public void onFailure(Call<RESPONSE> call, Throwable throwable) {
				failureConsumer.accept(call, throwable);
			}
		};
	}

	public static <
		RESPONSE> Callback<RESPONSE> onSuccess(BiConsumer<Call<RESPONSE>, RESPONSE> successConsumer, BiConsumer<Call<RESPONSE>, Throwable> failureConsumer) {
		return new Callback<>() {
			@Override
			public void onResponse(Call<RESPONSE> call, Response<RESPONSE> response) {
				System.out.println("RequestUrl:" + response.raw().request().url().toString());
				final RESPONSE responseBody = response.code() == HttpURLConnection.HTTP_OK ? response.body() : null;

				if (Objects.nonNull(responseBody)) {
					successConsumer.accept(call, responseBody);
				}
				else {
					try {
						throw new Throwable(response.errorBody().string());
					}
					catch (Throwable throwable) {
						failureConsumer.accept(call, throwable);
						throwable.printStackTrace();
					}
				}
			}

			@Override
			public void onFailure(Call<RESPONSE> call, Throwable throwable) {
				failureConsumer.accept(call, throwable);
			}
		};
	}
}
