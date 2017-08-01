package io.codelirium.game.core;

import io.codelirium.game.core.model.Choice;
import io.codelirium.game.core.option.Option;

import java.util.List;


public interface GameStrategy {

	List<Choice> play(Option<Choice> option);

}
