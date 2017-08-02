package io.codelirium.game.core.model;


public interface Choice {

	String CHOICE_IMPLEMENTATION_PACKAGE = String.format("%s.impl", Choice.class.getPackage().toString());

	int getId();

	String getName();

	Result gameOnWith(final Choice choice);
}
