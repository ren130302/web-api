package com.ren130302.webapi.pornhubapi.request;

import com.ren130302.webapi.lib.ApiBuilder;

public final class VideoActive {

	public static class Builder
		extends ApiBuilder {

		public static Builder ofId(String id) {
			return new Builder().id(id);
		}

		private Builder id(String id) {
			return this.put("id", id);
		}
	}
}
