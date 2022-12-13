
package com.ren130302.webapi.pornhubapi.response;

import java.util.List;

import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"duration",
		"views",
		"video_id",
		"rating",
		"ratings",
		"title",
		"url",
		"default_thumb",
		"thumb",
		"publish_date",
		"thumbs",
		"tags",
		"pornstars",
		"categories",
		"segment",
		"is_active"
})
@Generated("jsonschema2pojo")
public class VideoResponse {

	@JsonProperty("duration")
	public String duration;
	@JsonProperty("views")
	public int views;
	@JsonProperty("video_id")
	public String videoId;
	@JsonProperty("rating")
	public String rating;
	@JsonProperty("ratings")
	public int ratings;
	@JsonProperty("title")
	public String title;
	@JsonProperty("url")
	public String url;
	@JsonProperty("default_thumb")
	public String defaultThumb;
	@JsonProperty("thumb")
	public String thumb;
	@JsonProperty("publish_date")
	public String publishDate;
	@JsonProperty("thumbs")
	public List<Thumb> thumbs = null;
	@JsonProperty("tags")
	public List<Tag> tags = null;
	@JsonProperty("pornstars")
	public List<Object> pornstars = null;
	@JsonProperty("categories")
	public List<Category> categories = null;
	@JsonProperty("segment")
	public String segment;
	@JsonProperty("is_active")
	public String isActive;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({
			"tag_name"
	})
	@Generated("jsonschema2pojo")
	public class Tag {

		@JsonProperty("tag_name")
		public String tagName;

	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({
			"tag_name"
	})
	@Generated("jsonschema2pojo")
	public class Category {

		@JsonProperty("category_name")
		public String categoryName;

	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({
			"size",
			"width",
			"height",
			"src"
	})
	@Generated("jsonschema2pojo")
	public class Thumb {

		@JsonProperty("size")
		public String size;
		@JsonProperty("width")
		public String width;
		@JsonProperty("height")
		public String height;
		@JsonProperty("src")
		public String src;

	}

}
