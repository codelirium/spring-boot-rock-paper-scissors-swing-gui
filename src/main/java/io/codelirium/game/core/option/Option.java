package io.codelirium.game.core.option;


public interface Option<T> {

	T getOrElse(T defaultObject);

	boolean empty();
}
