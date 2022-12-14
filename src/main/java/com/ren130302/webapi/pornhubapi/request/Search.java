package com.ren130302.webapi.pornhubapi.request;

import com.ren130302.webapi.lib.ApiBuilder;

public final class Search {

	public static class Builder
		extends ApiBuilder {

		public Builder q(String q) {
			this.put("search", q);
			return this;
		}

		public Builder thumbsize(String q) {
			this.put("thumbsize", q);
			return this;
		}

		public Builder category(String q) {
			this.put("category", q);
			return this;
		}

		public Builder page(String q) {
			this.put("page", q);
			return this;
		}

		public Builder ordering(String q) {
			this.put("ordering", q);
			return this;
		}

		public Builder phrase(String q) {
			this.put("phrase", q);
			return this;
		}

		public Builder tags(String q) {
			this.put("tags", q);
			return this;
		}

		public Builder period(String q) {
			this.put("period", q);
			return this;
		}
	}
}
