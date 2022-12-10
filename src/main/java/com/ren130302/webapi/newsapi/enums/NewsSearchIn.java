package com.ren130302.webapi.newsapi.enums;

import com.ren130302.webapi.lib.interfaces.IEnumItem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NewsSearchIn
		implements IEnumItem<Byte> {
	TITLE((byte) 1, "title"),
	DESCRIPTION((byte) 2, "description"),
	CONTENT((byte) 3, "content");

	private final Byte id;
	private final String name;

	private NewsSearchIn() {
		this.id = (byte) this.ordinal();
		this.name = this.name().toLowerCase();
	}
}
