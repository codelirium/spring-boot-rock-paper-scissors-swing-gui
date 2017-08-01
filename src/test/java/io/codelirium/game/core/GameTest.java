package io.codelirium.game.core;

import io.codelirium.game.core.option.impl.None;
import io.codelirium.game.core.option.impl.Some;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static io.codelirium.game.core.Mode.COMPUTER_VS_COMPUTER;
import static io.codelirium.game.core.Mode.HUMAN_VS_COMPUTER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
public class GameTest {


	@Test
	public void testHumanVsComputerGameWhenHumanMakesAChoice() {
		Game game = new Game(HUMAN_VS_COMPUTER);
		assertThat(game.play(new Some<>(Game.getRandomChoice())).size(), is(2));
	}

	@Test
	public void testHumanVsComputerGameWhenHumanDoesNotMakeAChoice() {
		Game game = new Game(HUMAN_VS_COMPUTER);
		assertThat(game.play(new None<>()).size(), is(2));
	}

	@Test
	public void testComputerVsComputerGame() {
		Game game = new Game(COMPUTER_VS_COMPUTER);
		assertThat(game.play(new None<>()).size(), is(2));
	}
}
