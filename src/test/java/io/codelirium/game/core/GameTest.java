package io.codelirium.game.core;

import io.codelirium.game.core.option.impl.None;
import io.codelirium.game.core.option.impl.Some;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static io.codelirium.game.core.GameStrategies.getRandomChoice;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
public class GameTest {

	@Test
	public void testHumanVsComputerGameWhenHumanMakesAChoice() {
		GameStrategy gameStrategy = new GameStrategies().humanVSComputerStrategy();
		assertThat(gameStrategy.play(new Some<>(getRandomChoice())).size(), is(2));
	}

	@Test
	public void testHumanVsComputerGameWhenHumanDoesNotMakeAChoice() {
		GameStrategy gameStrategy = new GameStrategies().humanVSComputerStrategy();
		assertThat(gameStrategy.play(new None<>()).size(), is(2));
	}

	@Test
	public void testComputerVsComputerGame() {
		GameStrategy gameStrategy = new GameStrategies().computerVSComputerStrategy();
		assertThat(gameStrategy.play(new None<>()).size(), is(2));
	}
}
