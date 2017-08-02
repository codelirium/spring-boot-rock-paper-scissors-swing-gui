package io.codelirium.game.core.option.impl;

import io.codelirium.game.core.option.Option;

import static java.util.Objects.isNull;


public class Some<T> implements Option<T> {

	public static final String MESSAGE_OPTION_CANNOT_BE_NULL = "The option cannot be null.";

	private final T object;


	public Some(final T object) {

		if (isNull(object)) {
			throw new IllegalArgumentException(MESSAGE_OPTION_CANNOT_BE_NULL);
		}

		this.object = object;
	}


	@Override
	public T getOrElse(final T defaultObject) {
		return isNull(this.object) ? defaultObject : object;
	}

	@Override
	public boolean empty() {
		return false;
	}
}
