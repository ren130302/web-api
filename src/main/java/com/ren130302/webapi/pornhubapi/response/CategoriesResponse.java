package com.ren130302.webapi.pornhubapi.response;

import java.util.List;

import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"categorie"
})
@Generated("jsonschema2pojo")
@Data
public class CategoriesResponse {
	@JsonProperty("categorie")
	public List<Category> categorie = null;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({
			"category",
			"id"
	})
	@Generated("jsonschema2pojo")
	@Data
	public static class Category {

		@JsonProperty("category")
		public String category;

		@JsonProperty("id")
		public String id;

	}
}
