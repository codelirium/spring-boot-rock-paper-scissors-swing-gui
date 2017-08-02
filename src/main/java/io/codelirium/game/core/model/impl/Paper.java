package io.codelirium.game.core.model.impl;

import io.codelirium.game.core.model.Choice;
import io.codelirium.game.core.model.ChoiceObject;
import io.codelirium.game.core.model.Result;

import static io.codelirium.game.core.model.Result.LOSE;
import static io.codelirium.game.core.model.Result.TIE;
import static io.codelirium.game.core.model.Result.WIN;


@ChoiceObject
public class Paper implements Choice {

	static String NAME = Paper.class.getSimpleName();

	@Override
	public int getId() {
		return 2;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public Result gameOnWith(final Choice choice) {

		if (this.getId() == choice.getId()) {
			return TIE;
		}

		if (choice.getId() % 2 == 0) {
			return Math.min(this.getId(), choice.getId()) == this.getId() ? WIN : LOSE;
		}

		return Math.max(this.getId(), choice.getId()) == this.getId() ? WIN : LOSE;
	}
}