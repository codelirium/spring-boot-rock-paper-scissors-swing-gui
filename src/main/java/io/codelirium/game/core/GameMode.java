package io.codelirium.game.core;

import io.codelirium.game.core.model.Choice;
import io.codelirium.game.core.model.impl.Paper;
import io.codelirium.game.core.model.impl.Rock;
import io.codelirium.game.core.model.impl.Scissors;
import io.codelirium.game.core.option.Option;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


@Component
public class GameMode {

	private static List<Choice> ALL_CHOICES = Arrays.asList(new Rock(), new Paper(), new Scissors());


	public Game humanVSComputerGame() {
		return new HumanVSComputerGame();
	}

	public Game computerVSComputerGame() {
		return new ComputerVSComputerGame();
	}


	public final class HumanVSComputerGame implements Game {

		@Override
		public List<Choice> play(Option<Choice> option) {
			return Arrays.asList(option.getOrElse(getRandomChoice()), getRandomChoice());
		}
	}

	public final class ComputerVSComputerGame implements Game {

		@Override
		public List<Choice> play(Option<Choice> option) {
			return Arrays.asList(getRandomChoice(), getRandomChoice());
		}
	}

	static Choice getRandomChoice() {
		return ALL_CHOICES.get(new Random().nextInt(ALL_CHOICES.size()));
	}
}
