package com.ren130302.webapi.lib.interfaces;

import java.util.Map;

public interface IBuilder {

	public default IBuilder getBuilder() {
		return this;
	}

	public Map<String, String> getQueryMap();
}
