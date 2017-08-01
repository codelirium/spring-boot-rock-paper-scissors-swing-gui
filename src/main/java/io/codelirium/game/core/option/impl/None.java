package io.codelirium.game.core.option.impl;

import io.codelirium.game.core.option.Option;


public class None<T> implements Option<T> {

	public T getOrElse(T defaultObject) {
		return defaultObject;
	}
}
