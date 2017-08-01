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
public class GameStrategies {

	private static List<Choice> ALL_CHOICES = Arrays.asList(new Rock(), new Paper(), new Scissors());


	public GameStrategy humanVSComputerStrategy() {
		return new HumanVSComputerStrategy();
	}

	public GameStrategy computerVSComputerStrategy() {
		return new ComputerVSComputerStrategy();
	}


	public final class HumanVSComputerStrategy implements GameStrategy {

		@Override
		public List<Choice> play(Option<Choice> option) {
			return Arrays.asList(option.getOrElse(getRandomChoice()), getRandomChoice());
		}
	}

	public final class ComputerVSComputerStrategy implements GameStrategy {

		@Override
		public List<Choice> play(Option<Choice> option) {
			return Arrays.asList(getRandomChoice(), getRandomChoice());
		}
	}

	static Choice getRandomChoice() {
		return ALL_CHOICES.get(new Random().nextInt(ALL_CHOICES.size()));
	}
}
