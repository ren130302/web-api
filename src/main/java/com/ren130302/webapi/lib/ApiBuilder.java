package com.ren130302.webapi.lib;

import java.util.HashMap;
import java.util.Map;

import com.ren130302.webapi.lib.interfaces.IApiBuilder;

import lombok.Getter;
import lombok.experimental.Accessors;

public class ApiBuilder
	implements IApiBuilder {

	@Getter
	@Accessors(fluent = true)
	private final Map<String, String> queryMap = new HashMap<>();
}
