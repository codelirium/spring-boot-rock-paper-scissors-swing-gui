package io.codelirium.game.core;

import io.codelirium.game.core.model.Choice;
import io.codelirium.game.core.model.impl.Paper;
import io.codelirium.game.core.model.impl.Rock;
import io.codelirium.game.core.model.impl.Scissors;
import io.codelirium.game.core.option.Option;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Game {

	private static List<Choice> ALL_CHOICES = Arrays.asList(new Rock(), new Paper(), new Scissors());

	private Mode mode;


	public Game(Mode mode) {
		this.mode = mode;
	}


	public List<Choice> play(Option<Choice> option) {

		switch (mode) {

			case HUMAN_VS_COMPUTER:

				return Arrays.asList(option.getOrElse(getRandomChoice()), getRandomChoice());

			case COMPUTER_VS_COMPUTER:

				return Arrays.asList(getRandomChoice(), getRandomChoice());
		}

		return Collections.emptyList();
	}

	static Choice getRandomChoice() {
		return ALL_CHOICES.get(new Random().nextInt(ALL_CHOICES.size()));
	}
}
