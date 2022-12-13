package com.ren130302.webapi.pornhubapi.response;

import java.util.List;

import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"warning",
		"tagsCount",
		"tags"
})
@Generated("jsonschema2pojo")
public class TagsResponse {

	@JsonProperty("warning")
	public String warning;
	@JsonProperty("tagsCount")
	public int tagsCount;
	@JsonProperty("tags")
	public List<String> tags = null;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({
			"tag_name"
	})
	@Generated("jsonschema2pojo")
	public class Tag {

		@JsonProperty("tag_name")
		public String tagName;

	}

}