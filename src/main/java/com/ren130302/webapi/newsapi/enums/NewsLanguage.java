package com.ren130302.webapi.newsapi.enums;

import com.ren130302.webapi.lib.interfaces.IEnumItem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NewsLanguage
		implements IEnumItem<Byte> {
	AR,
	DE,
	EN,
	ES,
	FR,
	HE,
	IT,
	NL,
	NO,
	PT,
	RU,
	SV,
	UD,
	ZH;

	private final Byte id = (byte) this.ordinal();
	private final String name = this.name().toLowerCase();
}
