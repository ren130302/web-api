package com.ren130302.webapi.lib.interfaces;

import java.util.Collections;
import java.util.Map;

public interface IBuilder<REQUEST extends IRequest> {

	public REQUEST getRequest();

	public Map<String, String> getQueryMap();

	public default IBuilder<REQUEST> put(String key, String value) {
		this.getQueryMap().put(key, value);
		return this;
	}

	public default IBuilder<REQUEST> clear() {
		this.getQueryMap().clear();
		return this;
	}

	public default IBuilder<REQUEST> putApi() {
		return this.put(this.getRequest().getClient().getApiKeyLabel(), this.getRequest().getClient().getApiKeyValue());
	}

	public default IBuilder<REQUEST> putItems() {
		this.getRequest().getParams().accept(this.getRequest().getBuilder());
		return this;
	}

	public default IBuilder<REQUEST> remove(Object value) {
		this.getQueryMap().values().removeAll(Collections.singleton(value));
		return this;
	}

	public default IBuilder<REQUEST> removeItems() {
		return this.remove(null).remove("null");
	}

	public default IBuilder<REQUEST> initQuery() {
		return this.clear().putApi().putItems().removeItems();
	}
}
