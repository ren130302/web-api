package com.ren130302.webapi.lib;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.ren130302.webapi.lib.interfaces.IEnumItem;

public class QueryUtils {
	public static <
		T extends IEnumItem<?>> String enums(T[] enums) {
		return strings(Arrays.asList(enums).stream().map(IEnumItem::getName).collect(Collectors.toList()));
	}

	public static String strings(String[] arrays) {
		return strings(Arrays.asList(arrays));
	}

	public static String strings(List<String> list) {
		StringBuffer buf = new StringBuffer();

		Iterator<String> values = list.iterator();

		while (values.hasNext()) {
			buf.append(values.next() + (values.hasNext() ? "," : ""));
		}

		return buf.toString();
	}
}
