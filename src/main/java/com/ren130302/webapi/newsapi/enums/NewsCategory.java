package com.ren130302.webapi.newsapi.enums;

import com.ren130302.webapi.lib.interfaces.IEnumItem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NewsCategory
		implements IEnumItem<Byte> {
	BUSINESS((byte) 1, "business"),
	ENTERTAINMENT((byte) 2, "entertainment"),
	GENERAL((byte) 3, "general"),
	HEALTH((byte) 4, "health"),
	SCIENCE((byte) 5, "science"),
	SPORTS((byte) 6, "sports"),
	TECHNOLOGY((byte) 7, "technology");

	private final Byte id;
	private final String name;
}
