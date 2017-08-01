package io.codelirium.game.core.model.impl;

import io.codelirium.game.core.model.Choice;
import io.codelirium.game.core.model.ChoiceObject;
import io.codelirium.game.core.model.Result;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reflections.Reflections;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
public class ModelTest {


	@Test
	public void testThatRockBeatsScissors() {
		assertThat(new Rock().gameOnWith(new Scissors()), CoreMatchers.is(Result.WIN));
	}

	@Test
	public void testThatScissorsDoesNotBeatRock() {
		assertThat(new Scissors().gameOnWith(new Rock()), is(Result.LOSE));
	}

	@Test
	public void testThatPaperBeatsRock() {
		assertThat(new Paper().gameOnWith(new Rock()), is(Result.WIN));
	}

	@Test
	public void testThatRockDoesNotBeatPaper() {
		assertThat(new Rock().gameOnWith(new Paper()), is(Result.LOSE));
	}

	@Test
	public void testThatScissorsBeatsPaper() {
		assertThat(new Scissors().gameOnWith(new Paper()), is(Result.WIN));
	}

	@Test
	public void testThatPaperDoesNotBeatScissors() {
		assertThat(new Paper().gameOnWith(new Scissors()), is(Result.LOSE));
	}

	@Test
	public void testThatRockTiesRock() {
		assertThat(new Rock().gameOnWith(new Rock()), is(Result.TIE));
	}

	@Test
	public void testThatPaperTiesPaper() {
		assertThat(new Paper().gameOnWith(new Paper()), is(Result.TIE));
	}

	@Test
	public void testThatScissorsTiesScissors() {
		assertThat(new Scissors().gameOnWith(new Scissors()), is(Result.TIE));
	}

	@Test
	public void testRockName() {
		assertThat(new Rock().getName(), is(Rock.NAME));
	}

	@Test
	public void testPaperName() {
		assertThat(new Paper().getName(), is(Paper.NAME));
	}

	@Test
	public void testScissorsName() {
		assertThat(new Scissors().getName(), is(Scissors.NAME));
	}

	@Test
	public void testThatGameModelIsAndRemainsBalancedAndValidAfterPossibleExtension() throws Exception {

		Set<Class<?>> modelclazzes = new Reflections(this.getClass()
																.getPackage()
																	.toString()
																		.replace("package ", ""))
																.getTypesAnnotatedWith(ChoiceObject.class);


		assertThatPossibleChoiceCountIsOdd(modelclazzes);

		assertThatOneChoiceCanWinHalfOfTheRestChoices(modelclazzes);

		assertThatChoiceIdsStartFromOneAndAreContinousWithoutGaps(modelclazzes);
	}

	private void assertThatPossibleChoiceCountIsOdd(Set<Class<?>> modelClazzes) {

		assertThat(modelClazzes.size() % 2 == 1, is(Boolean.TRUE));

	}

	private void assertThatOneChoiceCanWinHalfOfTheRestChoices(Set<Class<?>> modelClazzes) throws Exception {

		Set<Choice> choices = new LinkedHashSet<>(modelClazzes.size());

		for (Class<?> modelClazz : modelClazzes) {
			choices.add((Choice) modelClazz.newInstance());
		}

		int expectedBeatCount = (modelClazzes.size() - 1) / 2;

		for (Choice outerChoice : choices) {

			int actualBeatCount = 0;

			for (Choice innerChoice : choices) {
				if (outerChoice.gameOnWith(innerChoice) == Result.WIN) {
					actualBeatCount++;
				}
			}

			assertThat(actualBeatCount, is(expectedBeatCount));
		}
	}

	private void assertThatChoiceIdsStartFromOneAndAreContinousWithoutGaps(Set<Class<?>> modelClazzes) throws Exception {

		List<Integer> modelObjectIds = new ArrayList<>(modelClazzes.size());

		for (Class<?> modelClazz : modelClazzes) {
			modelObjectIds.add(((Choice) modelClazz.newInstance()).getId());
		}

		Collections.sort(modelObjectIds);

		assertThat(modelObjectIds.get(0), is (1));

		for (int i = 0; i < modelObjectIds.size() - 1; i++) {
			assertThat(modelObjectIds.get(i) == modelObjectIds.get(i + 1) - 1, is(Boolean.TRUE));
		}
	}
}
