package com.ren130302.webapi.newsapi.response;

import java.util.List;

import lombok.Data;

@Data
public class Article {
	private Source source;
	private String author;
	private String title;
	private String description;
	private String url;
	private String urlToImage;
	private String publishedAt;
	private String content;

	@Data
	public static class Response {
		private String status;
		private int totalResults;
		private List<Article> articles;
	}
}
