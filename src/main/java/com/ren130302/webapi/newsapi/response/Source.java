package com.ren130302.webapi.newsapi.response;

import java.util.List;

import com.ren130302.webapi.lib.interfaces.IResponse;

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
	public static class Response
		implements IResponse {
		private String status;
		private List<Source> sources;
	}
}
