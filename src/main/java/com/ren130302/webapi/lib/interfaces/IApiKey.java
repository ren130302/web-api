package com.ren130302.webapi.lib.interfaces;

public interface IApiKey {
	public interface No
		extends IApiKey {}

	public interface Require<
		QUERY extends IQueryMap.Require<?, ?>>
		extends IApiKey {

		String apiLabel();

		String apiKey();

		default void putApi(QUERY query) {
			query.queryMap().put(this.apiLabel(), this.apiKey());
		}
	}
}
