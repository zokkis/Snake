package de.zokki.snake.Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import de.zokki.snake.SnakeGame;
import de.zokki.snake.Apple.Apple;
import de.zokki.snake.Utils.GlobalSettings;

public class Snake implements Runnable {

    private GlobalSettings settings = GlobalSettings.getInstance();

    private int xDir, yDir, currentXDir, currentYDir, dotSizeWidth, dotSizeHeight, startDotWidth, startDotHeight, bodyCount = 5;

    private int[] x, y, startX, startY;

    @Override
    public void run() {
	int dotCount = settings.getDotCount();
	x = new int[dotCount * dotCount + 1];
	y = new int[x.length];
	startX = new int[x.length];
	startY = new int[x.length];

	for (int i = 0; i < bodyCount; i++) {
	    x[i] = settings.getWidth() / 2;
	    y[i] = settings.getHeight() / 2;
	}
	
	System.out.println(x[0] + " : " + settings.getDotSizeWidth());

	int delay = settings.getDelay();
	try {
	    while (true) {
		move();
		currentXDir = xDir;
		currentYDir = yDir;
		Thread.sleep(delay);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void paint(Graphics graphics) {
	if (x == null) {
	    return;
	}
	graphics.setColor(Color.GREEN);
	for (int i = 0; i < bodyCount; i++) {
	    graphics.fillRect(x[i], y[i], dotSizeWidth, dotSizeHeight);
	}
    }

    public void repaint() {
	if (dotSizeWidth != 0) {
	    System.out.println("lol");
	    for (int i = 0; i < x.length; i++) {
		x[i] = startX[i] / startDotWidth * dotSizeWidth;
		y[i] = startY[i] / startDotHeight * dotSizeHeight;
	    }
	}
	dotSizeWidth = settings.getDotSizeWidth();
	dotSizeHeight = settings.getDotSizeHeight();
    }

    public int[] getY() {
	return y;
    }

    public int[] getX() {
	return x;
    }

    public Point[] getPossiblePoints() {
	if (x == null) {
	    return null;
	}

	int dotHeight = settings.getDotSizeHeight();
	int dotWidth = settings.getDotSizeWidth();
	int dotCount = settings.getDotCount();
	ArrayList<Point> points = new ArrayList<Point>();
	for (int i = 0; i < dotCount; i++) {
	    int posX = i * dotHeight + 1;
	    for (int j = 0; j < dotCount; j++) {
		int posY = i * dotWidth + 1;
		boolean isIn = false;
		for (int k = 0; k < x.length; k++) {
		    if (x[k] == posX && y[k] == posY) {
			isIn = true;
		    }
		}
		if (!isIn) {
		    points.add(new Point(posX, posY));
		}
	    }
	}
	return points.toArray(new Point[points.size()]);
    }

    public int getCurrentXDir() {
	return currentXDir;
    }

    public int getCurrentYDir() {
	return currentYDir;
    }

    public void setXDir(int xDir) {
	this.xDir = xDir;
    }

    public void setYDir(int yDir) {
	this.yDir = yDir;
    }

    private void move() {
	startDotWidth = settings.getDotSizeWidth();
	startDotHeight = settings.getDotSizeHeight();
	Apple apple = settings.getApple();
	int headX = x[0];
	int headY = y[0];

	// System.out.println(apple.x + " : " + headX);

	// check for apple
	if (apple.x == headX && apple.y == headY) {
	    System.out.println("INNN");
	    bodyCount++;
	    apple.setApple();
	}

	// check for borders
	if ((headY > 0 || yDir == 1) && (headY < settings.getHeight() || yDir == -1) //
		&& (headX > 0 || xDir == 1) && (headX < settings.getWidth() || xDir == -1)) {
	    x[0] = headX + dotSizeWidth * xDir;
	    y[0] = headY + dotSizeHeight * yDir;
	    startX[0] = x[0];
	    startY[0] = y[0];
	} else {
	    restartGame();
	}

	// move snake
	for (int i = bodyCount; i > 0; i--) {
	    x[i] = x[i - 1];
	    y[i] = y[i - 1];
	    startX[i] = x[i];
	    startY[i] = y[i];
	}

	// check for snake body
	if (xDir != 0 || yDir != 0) {
	    for (int i = bodyCount; i > 1; i--) {
		if (x[0] == x[i] && y[0] == y[i]) {
		    restartGame();
		}
	    }
	}
    }

    private void restartGame() {
	SnakeGame.gui.restartGame();
	Thread.currentThread().interrupt();
    }
}
