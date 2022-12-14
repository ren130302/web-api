package com.ren130302.webapi.pornhubapi.request;

import com.ren130302.webapi.lib.ApiBuilder;

public final class Video {

	public static class Builder
		extends ApiBuilder {

		public static Builder ofId(String id) {
			return new Builder().id(id);
		}

		private Builder id(String id) {
			return this.put("id", id);
		}

		public Builder thumbsize(String thumbsize) {
			return this.put("thumbsize", thumbsize);
		}
	}
}
