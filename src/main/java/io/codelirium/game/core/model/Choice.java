package io.codelirium.game.core.model;


public interface Choice {

	abstract int getId();

	abstract String getName();

	Result gameOnWith(Choice choice);
}
