package io.codelirium.game.core.option;


public interface Option<T> {

	T getOrElse(final T defaultObject);

	boolean empty();
}
