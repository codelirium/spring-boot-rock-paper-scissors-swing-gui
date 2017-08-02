package io.codelirium.game.core;

import io.codelirium.game.core.model.Choice;
import io.codelirium.game.core.option.Option;

import java.util.List;


public interface Game {

	List<Choice> play(final Option<Choice> option);

}
