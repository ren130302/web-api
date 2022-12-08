package com.ren130302.webapi.lib.interfaces;

public interface IResponse {

	public default IResponse getResponse() {
		return this;
	}

	void setStatus(String status);

	String getStatus();
}
