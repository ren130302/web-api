package com.ren130302.webapi.lib.interfaces;

import java.util.function.Consumer;

public interface IForm<
	BUILDER extends IBuilder> {
	Consumer<BUILDER> wrap();
}
