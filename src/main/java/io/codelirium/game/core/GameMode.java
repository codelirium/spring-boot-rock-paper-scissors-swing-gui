package io.codelirium.game.core;

import io.codelirium.game.core.model.Choice;
import io.codelirium.game.core.model.ChoiceObject;
import io.codelirium.game.core.option.Option;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static io.codelirium.game.core.model.Choice.CHOICE_IMPLEMENTATION_PACKAGE;
import static io.codelirium.game.util.Utils.getObjectInstancesFromPackageAnnotatedWith;


@Component
public class GameMode {

	private static List<Choice> ALL_CHOICES = getObjectInstancesFromPackageAnnotatedWith(CHOICE_IMPLEMENTATION_PACKAGE, ChoiceObject.class);


	public Game humanVSComputerGame() {
		return new HumanVSComputerGame();
	}

	public Game computerVSComputerGame() {
		return new ComputerVSComputerGame();
	}


	private final class HumanVSComputerGame implements Game {

		@Override
		public List<Choice> play(Option<Choice> option) {
			return Arrays.asList(option.getOrElse(getRandomChoice()), getRandomChoice());
		}
	}

	private final class ComputerVSComputerGame implements Game {

		@Override
		public List<Choice> play(Option<Choice> option) {
			return Arrays.asList(getRandomChoice(), getRandomChoice());
		}
	}

	static Choice getRandomChoice() {
		return ALL_CHOICES.get(new Random().nextInt(ALL_CHOICES.size()));
	}
}
