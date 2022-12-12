package com.ren130302.utils;

import java.time.LocalDateTime;

public class DateUtils {
	public static void main(String[] args) {
		String d = "2022-11-22T23:13:38Z";

		LocalDateTime dt = LocalDateTime.parse(d);
		LocalDateTime dn = LocalDateTime.now();
	}
}
