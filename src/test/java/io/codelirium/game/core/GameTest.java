package io.codelirium.game.core;

import io.codelirium.game.core.option.impl.None;
import io.codelirium.game.core.option.impl.Some;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static io.codelirium.game.core.GameMode.getRandomChoice;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
public class GameTest {

	@Test
	public void testHumanVsComputerGameWhenHumanMakesAChoice() {
		Game game = new GameMode().humanVSComputerGame();
		assertThat(game.play(new Some<>(getRandomChoice())).size(), is(2));
	}

	@Test
	public void testHumanVsComputerGameWhenHumanDoesNotMakeAChoice() {
		Game game = new GameMode().humanVSComputerGame();
		assertThat(game.play(new None<>()).size(), is(2));
	}

	@Test
	public void testComputerVsComputerGame() {
		Game game = new GameMode().computerVSComputerGame();
		assertThat(game.play(new None<>()).size(), is(2));
	}
}
