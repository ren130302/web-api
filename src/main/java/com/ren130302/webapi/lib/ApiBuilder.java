package com.ren130302.webapi.lib;

import java.util.HashMap;
import java.util.Map;

import com.ren130302.webapi.lib.interfaces.IApiBuilder;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class ApiBuilder
	implements IApiBuilder {

	private final Map<String, String> queryMap = new HashMap<>();
}
