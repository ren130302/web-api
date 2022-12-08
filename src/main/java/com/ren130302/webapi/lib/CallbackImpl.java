package com.ren130302.webapi.lib;

import java.net.HttpURLConnection;
import java.util.Objects;
import java.util.function.BiConsumer;

import org.json.JSONException;
import org.json.JSONObject;

import com.ren130302.webapi.lib.interfaces.IResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallbackImpl<RESPONSE extends IResponse> implements Callback<RESPONSE> {

	private RESPONSE response;

	private final BiConsumer<Call<RESPONSE>, RESPONSE> onSuccess;
	private BiConsumer<Call<RESPONSE>, Throwable> onFailure = (c, t) -> {
		// nothing
	};

	private CallbackImpl(BiConsumer<Call<RESPONSE>, RESPONSE> onSuccess) {
		this.onSuccess = onSuccess;
	}

	public static <RESPONSE extends IResponse> CallbackImpl<RESPONSE> empty() {
		return success((c, r) -> {
		});
	}

	public static <RESPONSE extends IResponse> CallbackImpl<RESPONSE> success(
			BiConsumer<Call<RESPONSE>, RESPONSE> onSuccess) {
		return new CallbackImpl<>(onSuccess);
	}

	public void failure(BiConsumer<Call<RESPONSE>, Throwable> onFailure) {
		this.onFailure = onFailure;
	}

	@Override
	public void onResponse(Call<RESPONSE> call, Response<RESPONSE> response) {
		if (response.code() == HttpURLConnection.HTTP_OK) {
			System.out.println("onSuccess");
			this.response = response.body();
			if (Objects.nonNull(this.onSuccess)) {
				this.onSuccess.accept(call, this.response);
			}
		} else {
			try {
				Throwable throwable = new Throwable("An error occured");

				try {
					throwable = new Throwable(new JSONObject(response.errorBody()).getString("message"));
				} catch (JSONException e) {
					e.printStackTrace();
				}

				throw throwable;
			} catch (Throwable e) {
				this.onFailure(call, e);
			}
		}
	}

	@Override
	public void onFailure(Call<RESPONSE> call, Throwable throwable) {
		System.out.println("onFailure");
		if (Objects.nonNull(this.onFailure)) {
			this.onFailure.accept(call, throwable);
		}
	}

	public RESPONSE getResponse() {
		return this.response;
	}
}
