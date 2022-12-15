package com.ren130302.webapi.lib.interfaces;

import java.util.Optional;
import java.util.function.Function;

import lombok.NonNull;
import retrofit2.Retrofit;

public interface IApiClient {

	@NonNull
	Function<Retrofit.Builder, Retrofit.Builder> retrofitBuilderFunction();

	Optional<String> apiLabelOptional();

	default Retrofit retrofit() {
		return RetrofitInstance.singleton(this.retrofitBuilderFunction());
	}

	public static class RetrofitInstance {
		private static Retrofit mRetrofit;

		private static Retrofit singleton(Function<Retrofit.Builder, Retrofit.Builder> builderFunction) {
			if (mRetrofit == null) {
				mRetrofit = build(builderFunction);
			}

			return mRetrofit;
		}

		private static Retrofit build(Function<Retrofit.Builder, Retrofit.Builder> builderFunction) {
			return builderFunction.apply(new Retrofit.Builder()).build();
		}
	}
}
