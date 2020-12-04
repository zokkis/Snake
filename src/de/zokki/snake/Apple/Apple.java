package de.zokki.snake.Apple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;
import java.util.Random;

import de.zokki.snake.Snake.Snake;
import de.zokki.snake.Utils.GlobalSettings;

public class Apple {

    public int x, y, startW, startH, startDotW;

    private GlobalSettings settings = GlobalSettings.getInstance();

    private Random random = new Random();

    public Apple() {
	startW = settings.getWidth();
	startH = settings.getHeight();
	startDotW = settings.getDotSizeWidth();
    }
    
    public void paint(Graphics graphics) {
	graphics.setColor(Color.RED);
	graphics.fillOval(x, y, settings.getDotSizeWidth(), settings.getDotSizeHeight());
    }
    
    public void repaint() {
	System.out.println((double) settings.getWidth() * 100 / startW);
	//Need relative percentage
    }

    public void setApple() {
	Snake snake = settings.getSnake();

	try {
	    Point[] points = snake.getPossiblePoints();
	    
	    Point point = points[random.nextInt(points.length)];
	    x = point.x - 1;
	    y = point.y - 1;
	} catch (Exception e) {
	    e.printStackTrace();
	    x = random.nextInt((int) settings.getWidth() / settings.getDotSizeWidth()) * settings.getDotSizeWidth();
	    y = random.nextInt((int) settings.getHeight() / settings.getDotSizeHeight()) * settings.getDotSizeHeight();
	    checkSnake();
	}
    }

    private void checkSnake() {
	Snake snake = settings.getSnake();
	if (snake.getX() != null) {
	    if (Arrays.stream(snake.getX()).anyMatch(i -> i == x + 1)
		    && Arrays.stream(snake.getY()).anyMatch(i -> i == y + 1)) {
		setApple();
	    }
	}
    }
}
