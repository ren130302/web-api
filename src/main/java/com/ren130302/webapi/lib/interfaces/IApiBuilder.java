package com.ren130302.webapi.lib.interfaces;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface IApiBuilder {

	Map<String, String> queryMap();

	@SuppressWarnings("unchecked")
	default <
		BUILDER extends IApiBuilder> BUILDER put(String key, String value) {
		this.queryMap().put(key, value);
		return (BUILDER) this;
	}

	@SuppressWarnings("unchecked")
	default <
		BUILDER extends IApiBuilder> BUILDER remove(Predicate<? super String> filter) {
		this.queryMap().values().removeIf(filter);
		return (BUILDER) this;
	}

	@SuppressWarnings("unchecked")
	default <
		BUILDER extends IApiBuilder> BUILDER putItems(Consumer<BUILDER> builderConsumer) {
		builderConsumer.accept((BUILDER) this);
		return (BUILDER) this;
	}

	default <
		BUILDER extends IApiBuilder> BUILDER removeItems() {
		return this.remove(String::isEmpty).remove(String::isBlank).remove(Objects::isNull);
	}

	default <
		BUILDER extends IApiBuilder> Map<String, String> initQueryMap(Consumer<BUILDER> builderConsumee) {
		return this.putItems(builderConsumee).removeItems().queryMap();
	}
}
