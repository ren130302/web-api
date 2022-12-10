package com.ren130302.webapi.lib.interfaces;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface IBuilder {

	Map<String, String> getQueryMap();

	@SuppressWarnings("unchecked")
	default <
		BUILDER extends IBuilder> BUILDER put(String key, String value) {
		this.getQueryMap().put(key, value);
		return (BUILDER) this;
	}

	@SuppressWarnings("unchecked")
	default <
		BUILDER extends IBuilder> BUILDER remove(Predicate<? super String> filter) {
		this.getQueryMap().values().removeIf(filter);
		return (BUILDER) this;
	}

	@SuppressWarnings("unchecked")
	default <
		BUILDER extends IBuilder> BUILDER putItems(Consumer<BUILDER> consumer) {
		consumer.accept((BUILDER) this);
		return (BUILDER) this;
	}

	default <
		BUILDER extends IBuilder> BUILDER removeItems() {
		return this.remove(String::isEmpty).remove(String::isBlank).remove(Objects::isNull);
	}

	default <
		BUILDER extends IBuilder> Map<String, String> initQuery(Consumer<BUILDER> consumer) {
		return this.putItems(consumer).removeItems().getQueryMap();
	}
}
