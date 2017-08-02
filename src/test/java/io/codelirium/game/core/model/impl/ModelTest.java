package io.codelirium.game.core.model.impl;

import io.codelirium.game.core.model.Choice;
import io.codelirium.game.core.model.ChoiceObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static io.codelirium.game.core.model.Choice.CHOICE_IMPLEMENTATION_PACKAGE;
import static io.codelirium.game.core.model.Result.*;
import static io.codelirium.game.util.Utils.getObjectInstancesFromPackageAnnotatedWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
public class ModelTest {

	@Test
	public void testThatRockBeatsScissors() {
		assertThat(new Rock().gameOnWith(new Scissors()), is(WIN));
	}

	@Test
	public void testThatScissorsDoesNotBeatRock() {
		assertThat(new Scissors().gameOnWith(new Rock()), is(LOSE));
	}

	@Test
	public void testThatPaperBeatsRock() {
		assertThat(new Paper().gameOnWith(new Rock()), is(WIN));
	}

	@Test
	public void testThatRockDoesNotBeatPaper() {
		assertThat(new Rock().gameOnWith(new Paper()), is(LOSE));
	}

	@Test
	public void testThatScissorsBeatsPaper() {
		assertThat(new Scissors().gameOnWith(new Paper()), is(WIN));
	}

	@Test
	public void testThatPaperDoesNotBeatScissors() {
		assertThat(new Paper().gameOnWith(new Scissors()), is(LOSE));
	}

	@Test
	public void testThatRockTiesRock() {
		assertThat(new Rock().gameOnWith(new Rock()), is(TIE));
	}

	@Test
	public void testThatPaperTiesPaper() {
		assertThat(new Paper().gameOnWith(new Paper()), is(TIE));
	}

	@Test
	public void testThatScissorsTiesScissors() {
		assertThat(new Scissors().gameOnWith(new Scissors()), is(TIE));
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
	public void testThatGameModelIsAndRemainsBalancedAndValidAfterPossibleExtension() {

		final List<Choice> modelObjectInstances = getObjectInstancesFromPackageAnnotatedWith(CHOICE_IMPLEMENTATION_PACKAGE, ChoiceObject.class);


		assertThatPossibleChoiceCountIsOdd(modelObjectInstances);

		assertThatOneChoiceCanWinHalfOfTheRestChoices(modelObjectInstances);

		assertThatChoiceIdsStartFromOneAndAreContinuousWithoutGaps(modelObjectInstances);
	}

	private void assertThatPossibleChoiceCountIsOdd(final List<Choice> modelObjectInstances) {

		assertThat(modelObjectInstances.size() % 2 == 1, is(Boolean.TRUE));

	}

	private void assertThatOneChoiceCanWinHalfOfTheRestChoices(final List<Choice> modelObjectInstances) {

		final int expectedBeatCount = (modelObjectInstances.size() - 1) / 2;

		for (Choice outerChoice : modelObjectInstances) {

			int actualBeatCount = 0;

			for (Choice innerChoice : modelObjectInstances) {
				if (outerChoice.gameOnWith(innerChoice) == WIN) {
					actualBeatCount++;
				}
			}

			assertThat(actualBeatCount, is(expectedBeatCount));
		}
	}

	private void assertThatChoiceIdsStartFromOneAndAreContinuousWithoutGaps(final List<Choice> modelObjectInstances) {

		final List<Integer> modelObjectIds = modelObjectInstances.stream().map(Choice::getId).collect(Collectors.toList());

		Collections.sort(modelObjectIds);

		assertThat(modelObjectIds.get(0), is (1));

		for (int i = 0; i < modelObjectIds.size() - 1; i++) {
			assertThat(modelObjectIds.get(i) == modelObjectIds.get(i + 1) - 1, is(Boolean.TRUE));
		}
	}
}
