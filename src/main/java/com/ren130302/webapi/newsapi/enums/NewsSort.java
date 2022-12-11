package com.ren130302.webapi.newsapi.enums;

import com.ren130302.webapi.lib.interfaces.IEnumItem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NewsSort
		implements IEnumItem<Byte> {
	/**
	 * Articles more closely related to q come first.
	 */
	RELEVANCY((byte) 1, "relevancy"),
	/**
	 * Articles from popular sources and publishers come first.
	 */
	POPULARITY((byte) 2, "popularity"),
	/**
	 * Newest articles come first.
	 */
	PUBLISHED_AT((byte) 3, "publishedAt");

	private final Byte id;
	private final String name;
}
