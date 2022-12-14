
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
public class StarsDetailedResponse {

	@JsonProperty("star")
	public List<Star> stars = null;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({
			"star_name",
			"star_thumb",
			"star_url",
			"gender",
			"videos_count_all"
	})
	@Generated("jsonschema2pojo")
	public class Star {

		@JsonProperty("star_name")
		public String starName;
		@JsonProperty("star_thumb")
		public String starThumb;
		@JsonProperty("star_url")
		public String starUrl;
		@JsonProperty("gender")
		public String gender;
		@JsonProperty("videos_count_all")
		public String videosCountAll;
	}
}
