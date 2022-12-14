package com.ren130302.webapi.newsapi.response;

import java.util.List;

import lombok.Data;

@Data
public class Source {
	private String id;
	private String name;
	private String description;
	private String url;
	private String category;
	private String language;
	private String country;

	@Data
	public static class Response {
		private String status;
		private List<Source> sources;
	}
}
