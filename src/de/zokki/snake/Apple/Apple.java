package de.zokki.snake.Apple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import de.zokki.snake.Snake.Snake;
import de.zokki.snake.Utils.GlobalSettings;

public class Apple {

    public int x, y, startX, startY;

    private GlobalSettings settings = GlobalSettings.getInstance();

    private Random random = new Random();

    public void paint(Graphics graphics) {
	graphics.setColor(Color.RED);
	graphics.fillOval(x, y, settings.getDotSizeWidth(), settings.getDotSizeHeight());
    }

    public void repaint() {
	x = startX * settings.getDotSizeWidth();
	y = startY * settings.getDotSizeHeight();
    }

    public void setApple() {
	Snake snake = settings.getSnake();

	Point[] points = snake.getPossiblePoints();

	Point point = points[random.nextInt(points.length)];
	x = point.x - 1;
	y = point.y - 1;

	startX = x / settings.getDotSizeWidth();
	startY = y / settings.getDotSizeHeight();
    }
}
