package io.codelirium.game.core.option;


public interface Option<T> {

	public T getOrElse(T defaultObject);

}
