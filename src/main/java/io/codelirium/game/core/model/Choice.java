package io.codelirium.game.core.model;


public interface Choice {

	int getId();

	String getName();

	Result gameOnWith(Choice choice);
}
