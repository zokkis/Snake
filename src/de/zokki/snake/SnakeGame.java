package de.zokki.snake;

import java.awt.EventQueue;

import de.zokki.snake.Gui.Gui;
import de.zokki.snake.Utils.GlobalSettings;

public class SnakeGame {

    private GlobalSettings settings = GlobalSettings.getInstance();

    public static Gui gui = null;

    public static void main(String[] args) {
	new SnakeGame();
    }

    public SnakeGame() {
	setDefaultSettings();
	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		gui = new Gui("Snake!");
		gui.startGame();
	    }
	});
    }

    private void setDefaultSettings() {
	settings.setWidth(600);
	settings.setHeight(600);
	settings.setDotCount(20);
	settings.setDelay(200);
    }
}
