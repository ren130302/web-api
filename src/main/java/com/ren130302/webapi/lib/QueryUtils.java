package com.ren130302.webapi.lib;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ren130302.webapi.lib.interfaces.IEnumItem;

public class QueryUtils {
	public static <
		T extends IEnumItem<?>> String enumValue(T value, T defaultValue) {

		return valueOf(IEnumItem::getName, Objects.nonNull(value) ? value : defaultValue);
	}

	public static <
		T extends IEnumItem<?>> String enumValue(T[] values, T[] defaultValues) {
		return valueOf(IEnumItem::getName, Objects.nonNull(values) ? values : defaultValues);
	}

	public static <
		T extends Number> String numValue(T value, T defaultValue) {
		return valueOf(String::valueOf, Objects.nonNull(value) ? value : defaultValue);
	}

	public static <
		T extends Number> String numValue(T[] values, T[] defaultValues) {
		return valueOf(String::valueOf, Objects.nonNull(values) ? values : defaultValues);
	}

	@SafeVarargs
	public static <
		T> String valueOf(Function<? super T, ? extends String> mapper, T... item) {
		return strings(Arrays.asList(item).stream().map(mapper).collect(Collectors.toList()));
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
