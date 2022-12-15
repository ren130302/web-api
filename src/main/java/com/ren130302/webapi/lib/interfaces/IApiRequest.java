package com.ren130302.webapi.lib.interfaces;

import retrofit2.Retrofit;

public interface IApiRequest<
	SERVICE> {

	Class<SERVICE> serviceClass();

	default SERVICE service(Retrofit retrofit) {
		return retrofit.create(this.serviceClass());
	}

	public interface NoBuilder<
		SERVICE extends IApiService>
		extends IApiRequest<SERVICE> {

	}

	public interface RequireBuilder<
		SERVICE extends IApiService,
		BUILDER extends IApiBuilder<BUILDER>>
		extends IApiRequest<SERVICE> {

		BUILDER builder();
	}
}
