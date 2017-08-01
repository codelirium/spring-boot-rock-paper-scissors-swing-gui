package io.codelirium.game.core.model.impl;

import io.codelirium.game.core.model.Choice;
import io.codelirium.game.core.model.ChoiceObject;
import io.codelirium.game.core.model.Result;


@ChoiceObject
public class Paper implements Choice {

	static String NAME = "Paper";

	@Override
	public int getId() {
		return 2;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public Result gameOnWith(Choice choice) {

		if (this.getId() == choice.getId()) {
			return Result.TIE;
		}

		if (choice.getId() % 2 == 0) {
			return Math.min(this.getId(), choice.getId()) == this.getId() ? Result.WIN : Result.LOSE;
		}

		return Math.max(this.getId(), choice.getId()) == this.getId() ? Result.WIN : Result.LOSE;
	}
}