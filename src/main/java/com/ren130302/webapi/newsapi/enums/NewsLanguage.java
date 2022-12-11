package com.ren130302.webapi.newsapi.enums;

import com.ren130302.webapi.lib.interfaces.IEnumItem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NewsLanguage
		implements IEnumItem<Byte> {
	AR((byte) 1, "ar"),
	DE((byte) 2, "de"),
	EN((byte) 3, "en"),
	ES((byte) 4, "es"),
	FR((byte) 5, "fr"),
	HE((byte) 6, "he"),
	IT((byte) 7, "it"),
	NL((byte) 8, "nl"),
	NO((byte) 9, "no"),
	PT((byte) 10, "pt"),
	RU((byte) 11, "ru"),
	SV((byte) 12, "sv"),
	UD((byte) 13, "ud"),
	ZH((byte) 14, "zh");

	private final Byte id;
	private final String name;
}
