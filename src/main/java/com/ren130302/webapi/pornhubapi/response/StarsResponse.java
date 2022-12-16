
package com.ren130302.webapi.pornhubapi.response;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"stars"
})
@Generated("jsonschema2pojo")
@Data
public class StarsResponse {

	@JsonProperty("stars")
	private List<Star> stars = new ArrayList<>();

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({
			"star"
	})
	@Generated("jsonschema2pojo")
	@Data
	public static class Star {

		@JsonProperty("star")
		private Star__1 star;

		@JsonInclude(JsonInclude.Include.NON_NULL)
		@JsonPropertyOrder({
				"star_name"
		})
		@Generated("jsonschema2pojo")
		@Data
		public static class Star__1 {

			@JsonProperty("star_name")
			private String starName;

		}

	}
}
