package de.zokki.snake.Apple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import de.zokki.snake.Snake.Snake;
import de.zokki.snake.Utils.GlobalSettings;

public class Apple {

    public int x, y, startDotWidth, startDotHeight, startX, startY;

    private GlobalSettings settings = GlobalSettings.getInstance();

    private Random random = new Random();

    public Apple() {
	setStartSize();
    }

    public void paint(Graphics graphics) {
	graphics.setColor(Color.RED);
	graphics.fillOval(x, y, settings.getDotSizeWidth(), settings.getDotSizeHeight());
    }

    public void repaint() {
	x = startX / startDotWidth * settings.getDotSizeWidth();
	y = startY / startDotHeight * settings.getDotSizeHeight();
	setStartSize();
    }

    public void setApple() {
	Snake snake = settings.getSnake();

	Point[] points = snake.getPossiblePoints();

	Point point = points[random.nextInt(points.length)];
	x = point.x - 1;
	y = point.y - 1;

	startX = x;
	startY = y;
    }

    private void setStartSize() {
	startDotWidth = settings.getDotSizeWidth();
	startDotHeight = settings.getDotSizeHeight();
    }
}
