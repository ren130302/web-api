package com.ren130302.webapi.pornhubapi.response;

import java.util.List;

import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"stars"
})
@Generated("jsonschema2pojo")
public class StarResponse {

	@JsonProperty("star_name")
	public List<Star> stars;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({
			"stars"
	})
	@Generated("jsonschema2pojo")
	public class Star {

		@JsonProperty("star_name")
		public String starName;

	}
}
