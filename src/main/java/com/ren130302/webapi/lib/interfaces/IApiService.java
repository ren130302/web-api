package com.ren130302.webapi.lib.interfaces;

public interface IApiService {
	public default IApiService getService() {
		return this;
	}
}
