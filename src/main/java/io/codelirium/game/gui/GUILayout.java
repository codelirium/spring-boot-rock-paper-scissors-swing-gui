package io.codelirium.game.gui;

import io.codelirium.game.core.Game;
import io.codelirium.game.core.Mode;
import io.codelirium.game.core.model.Choice;
import io.codelirium.game.core.model.impl.Paper;
import io.codelirium.game.core.model.impl.Rock;
import io.codelirium.game.core.model.impl.Scissors;
import io.codelirium.game.core.option.Option;
import io.codelirium.game.core.option.impl.None;
import io.codelirium.game.core.option.impl.Some;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import static io.codelirium.game.core.Mode.COMPUTER_VS_COMPUTER;
import static io.codelirium.game.core.Mode.HUMAN_VS_COMPUTER;
import static java.util.Objects.nonNull;


@UglyCode
@Component
public class GUILayout extends JFrame {

	private JPanel selectionPanel;
	private JPanel statusPanel;

	@PostConstruct
	public void init() {

		setTitle("Waste an Hour Having Fun");
		setSize(600, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		initSelectionAndStatusPanels();

		initModePanel();
	}

	private void initSelectionAndStatusPanels() {

		if (nonNull(selectionPanel)) {
			selectionPanel.removeAll();
		} else {
			selectionPanel = new JPanel();
		}

		selectionPanel.setVisible(false);
		getContentPane().add(selectionPanel, BorderLayout.PAGE_START);


		if (nonNull(statusPanel)) {
			statusPanel.removeAll();
		} else {
			statusPanel = new JPanel();
			statusPanel.setLayout(new BorderLayout());
		}

		statusPanel.setVisible(false);
		getContentPane().add(statusPanel, BorderLayout.CENTER);
	}

	private void initModePanel() {

		JButton humanVSComputerButton = new JButton("Human VS Computer");
		JButton computerVSComputerButton = new JButton("Computer VS Computer");
		JButton quitButton = new JButton("Quit");

		JPanel modePanel = new JPanel();

		modePanel.add(humanVSComputerButton);
		modePanel.add(computerVSComputerButton);
		modePanel.add(quitButton);

		getContentPane().add(modePanel, BorderLayout.PAGE_END);

		humanVSComputerButton.addActionListener((ActionEvent event) -> humanVSComputerButtonAction());
		computerVSComputerButton.addActionListener((ActionEvent event) -> computerVSComputerButtonAction());
		quitButton.addActionListener((ActionEvent event) -> quitButtonAction());
	}

	private void humanVSComputerButtonAction() {

		initSelectionAndStatusPanels();

		JButton rockButton = new JButton("Rock");
		JButton paperButton = new JButton("Paper");
		JButton scissorsButton = new JButton("Scissors");

		selectionPanel.add(rockButton);
		selectionPanel.add(paperButton);
		selectionPanel.add(scissorsButton);

		selectionPanel.setVisible(true);

		rockButton.addActionListener((ActionEvent event) -> rockButtonAction());
		paperButton.addActionListener((ActionEvent event) -> paperButtonAction());
		scissorsButton.addActionListener((ActionEvent event) -> scissorsButtonAction());
	}

	private void rockButtonAction() {

		initSelectionAndStatusPanels();

		displayGameResults(HUMAN_VS_COMPUTER, new Some<>(new Rock()));
	}

	private void paperButtonAction() {

		initSelectionAndStatusPanels();

		displayGameResults(HUMAN_VS_COMPUTER, new Some<>(new Paper()));
	}

	private void scissorsButtonAction() {

		initSelectionAndStatusPanels();

		displayGameResults(HUMAN_VS_COMPUTER, new Some<>(new Scissors()));
	}

	private void computerVSComputerButtonAction() {

		initSelectionAndStatusPanels();

		displayGameResults(COMPUTER_VS_COMPUTER, new None<>());
	}

	private void displayGameResults(Mode mode, Option<Choice> optionChoice) {

		List<Choice> choices = new Game(mode).play(optionChoice);

		String padding = "\t\t\t\t\t";
		String playerOneString = mode.equals(COMPUTER_VS_COMPUTER) ? "Computer 1" : "Human 1";
		String playerOneStringPadded = String.format("%s: %s%s", playerOneString, choices.get(0).getName(), padding);
		String playerTwoStringPadded = String.format("%sComputer 2: %s", padding, choices.get(1).getName());

		String statusString;

		switch (choices.get(0).gameOnWith(choices.get(1))) {

			case WIN:
				statusString = String.format("%s%s[ %s WINS! ]%s%s", playerOneStringPadded, padding, playerOneString, padding, playerTwoStringPadded);
				break;

			case LOSE:
				statusString = String.format("%s%s[ Computer 2 WINS! ]%s%s", playerOneStringPadded, padding, padding, playerTwoStringPadded);
				break;

			default:
				statusString = String.format("%s%s[ The game is a TIE! ]%s%s", playerOneStringPadded, padding, padding, playerTwoStringPadded);
		}

		statusPanel.add(new JLabel(statusString, SwingConstants.CENTER), BorderLayout.CENTER);
		statusPanel.setVisible(true);
	}

	private void quitButtonAction() {
		System.exit(0);
	}
}