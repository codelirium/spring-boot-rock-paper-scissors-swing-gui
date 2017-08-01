package io.codelirium.game.core.model.impl;

import io.codelirium.game.core.model.Choice;
import io.codelirium.game.core.model.ChoiceObject;
import io.codelirium.game.core.model.Result;


@ChoiceObject
public class Rock implements Choice {

	static String NAME = "Rock";

	@Override
	public int getId() {
		return 1;
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

		if (choice.getId() % 2 == 1) {
			return Math.min(this.getId(), choice.getId()) == this.getId() ? Result.WIN : Result.LOSE;
		}

		return Math.max(this.getId(), choice.getId()) == this.getId() ? Result.WIN : Result.LOSE;
	}
}
