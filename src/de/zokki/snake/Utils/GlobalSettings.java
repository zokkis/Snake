package de.zokki.snake.Utils;

import de.zokki.snake.Apple.Apple;
import de.zokki.snake.Snake.Snake;

public class GlobalSettings {

    private static GlobalSettings instance;

    public static GlobalSettings getInstance() {
	if (instance == null) {
	    instance = new GlobalSettings();
	}
	return instance;
    }

    private int width;
    private int height;
    private int dotCount;
    private int delay;

    private Snake snake;
    private Apple apple;

    public void setSnake(Snake snake) {
	this.snake = snake;
    }

    public void setApple(Apple apple) {
	this.apple = apple;
    }

    public int getDotSizeWidth() {
	return width / dotCount;
    }

    public int getDotSizeHeight() {
	return height / dotCount;
    }

    public Snake getSnake() {
	return snake;
    }

    public Apple getApple() {
	return apple;
    }

    public int getDotCount() {
	return dotCount;
    }

    public void setDotCount(int dotCount) {
	this.dotCount = dotCount;
    }

    public int getDelay() {
	return delay;
    }

    public void setDelay(int delay) {
	this.delay = delay;
    }

    public int getWidth() {
	return width;
    }

    public void setWidth(int width) {
	this.width = width;
    }

    public int getHeight() {
	return height;
    }

    public void setHeight(int height) {
	this.height = height;
    }
}
