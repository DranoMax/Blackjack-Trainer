package com.hatstick.blackjacktrainer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hatstick.blackjacktrainer.BlackjackTrainer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = BlackjackTrainer.SCREEN_WIDTH;
		config.height = BlackjackTrainer.SCREEN_HEIGHT;
		new LwjglApplication(new BlackjackTrainer(), config);
	}
}
