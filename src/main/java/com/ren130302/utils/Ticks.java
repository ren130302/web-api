package com.ren130302.utils;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Ticks {
	public static synchronized <
		T> void tick(long ticks, T t, Predicate<T> predicate, Consumer<T> consumer) {
		long start = 0;
		long calc = 0;
		while (predicate.test(t)) {
			calc = System.currentTimeMillis() - start;

			if (ticks <= calc) {
				start = System.currentTimeMillis();
				consumer.accept(t);
			}
		}
	}
}
