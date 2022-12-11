package com.ren130302.webapi.newsapi.enums;

import com.ren130302.webapi.lib.interfaces.IEnumItem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NewsCountry
		implements IEnumItem<Byte> {
	AE((byte) 1, "ae"),
	AR((byte) 2, "ar"),
	AT((byte) 3, "at"),
	AU((byte) 4, "au"),
	BE((byte) 5, "be"),
	BG((byte) 6, "bg"),
	BR((byte) 7, "br"),
	CA((byte) 8, "ca"),
	CH((byte) 9, "ch"),
	CN((byte) 10, "cn"),
	CO((byte) 11, "co"),
	CU((byte) 12, "cu"),
	CZ((byte) 13, "cz"),
	DE((byte) 14, "de"),
	EG((byte) 15, "eg"),
	FR((byte) 16, "fr"),
	GB((byte) 17, "gb"),
	GR((byte) 18, "gr"),
	HK((byte) 19, "hk"),
	HU((byte) 20, "hu"),
	ID((byte) 21, "id"),
	IE((byte) 22, "ie"),
	IL((byte) 23, "il"),
	IN((byte) 24, "in"),
	IT((byte) 25, "it"),
	JP((byte) 26, "jp"),
	KR((byte) 27, "kr"),
	LT((byte) 28, "lt"),
	LV((byte) 29, "lv"),
	MA((byte) 30, "ma"),
	MX((byte) 31, "mx"),
	MY((byte) 32, "my"),
	NG((byte) 33, "ng"),
	NL((byte) 34, "nl"),
	NO((byte) 35, "no"),
	NZ((byte) 36, "nz"),
	PH((byte) 37, "ph"),
	PL((byte) 38, "pl"),
	PT((byte) 39, "pt"),
	RO((byte) 40, "ro"),
	RS((byte) 41, "rs"),
	RU((byte) 42, "ru"),
	SA((byte) 43, "sa"),
	SE((byte) 44, "se"),
	SG((byte) 45, "sg"),
	SI((byte) 46, "si"),
	SK((byte) 47, "sk"),
	TH((byte) 48, "th"),
	TR((byte) 49, "tr"),
	TW((byte) 50, "tw"),
	UA((byte) 51, "ua"),
	US((byte) 52, "us"),
	VE((byte) 53, "ve"),
	ZA((byte) 54, "za");

	private final Byte id;
	private final String name;
}
