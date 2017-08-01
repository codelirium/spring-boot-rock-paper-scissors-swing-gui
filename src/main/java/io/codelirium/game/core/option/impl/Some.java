package io.codelirium.game.core.option.impl;

import io.codelirium.game.core.option.Option;

import static java.util.Objects.isNull;


public class Some<T> implements Option<T> {

	private final T object;

	public Some(T object) {
		this.object = object;
	}

	public T getOrElse(T defaultObject) {
		return isNull(this.object) ? defaultObject : object;
	}
}
