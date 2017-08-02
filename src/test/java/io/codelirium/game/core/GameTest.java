package io.codelirium.game.core;

import io.codelirium.game.core.model.Choice;
import io.codelirium.game.core.option.impl.None;
import io.codelirium.game.core.option.impl.Some;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static io.codelirium.game.core.GameMode.getRandomChoice;
import static io.codelirium.game.core.option.impl.Some.MESSAGE_OPTION_CANNOT_BE_NULL;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
public class GameTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();


	@Test
	public void testHumanVsComputerGameWhenHumanMakesAChoice() {
		final Game game = new GameMode().humanVSComputerGame();
		final Choice humanChoice = getRandomChoice();
		final List<Choice> gameChoices = game.play(new Some<>(humanChoice));

		assertThat(gameChoices.size(), is(2));
		assertThat(gameChoices.get(0), is(humanChoice));
	}

	@Test
	public void testThatHumanVsComputerGameThrowsExceptionForNullHumanChoiceOption() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(MESSAGE_OPTION_CANNOT_BE_NULL);

		final Game game = new GameMode().humanVSComputerGame();

		game.play(new Some<>(null));
	}

	@Test
	public void testHumanVsComputerGameWhenHumanDoesNotMakeAChoice() {
		final Game game = new GameMode().humanVSComputerGame();

		assertThat(game.play(new None<>()).size(), is(2));
	}

	@Test
	public void testComputerVsComputerGame() {
		final Game game = new GameMode().computerVSComputerGame();

		assertThat(game.play(new None<>()).size(), is(2));
	}
}
