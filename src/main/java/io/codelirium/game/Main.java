package io.codelirium.game;

import io.codelirium.game.gui.GUILayout;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.awt.*;


@SpringBootApplication
public class Main {

	public static void main(final String[] args) {
		EventQueue.invokeLater(() -> new SpringApplicationBuilder(Main.class)
																	.headless(false)
																	.run(args)
																			.getBean(GUILayout.class)
																						.setVisible(true));
	}
}