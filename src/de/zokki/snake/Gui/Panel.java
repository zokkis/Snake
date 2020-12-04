package de.zokki.snake.Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import de.zokki.snake.Apple.Apple;
import de.zokki.snake.Snake.Snake;
import de.zokki.snake.Utils.GlobalSettings;

public class Panel extends JPanel {

    private static final long serialVersionUID = 1L;

    private GlobalSettings settings = GlobalSettings.getInstance();

    private int width = settings.getWidth();
    private int height = settings.getHeight();
    
    Snake snake = new Snake();

    Apple apple = new Apple();

    public Panel() {
	setPreferredSize(new Dimension(width, height));
	settings.setSnake(snake);
	settings.setApple(apple);

	addComponentListener(new ComponentAdapter() {
	    @Override
	    public void componentResized(ComponentEvent e) {
		int width = getWidth();
		int height = getHeight();

		if (width != 0 && height != 0) {
		    settings.setWidth(width);
		    settings.setHeight(height);
		    snake.repaint();
		    apple.repaint();
		}
	    }
	});
    }

    @Override
    public void paint(Graphics graphics) {
	int dotSizeWidth = settings.getDotSizeWidth();
	int dotSizeHeight = settings.getDotSizeHeight();
	int dotCount = settings.getDotCount();
	int width = settings.getWidth();
	int height = settings.getHeight();
		
	Image image = createImage(width, height);
	Graphics imageGraphics = image.getGraphics();

	imageGraphics.setColor(Color.WHITE);
	for (int i = 1; i < dotCount; i++) {
	    imageGraphics.drawLine(0, i * dotSizeHeight, width, i * dotSizeHeight); // width
	}
	for (int i = 1; i < dotCount; i++) {
	    imageGraphics.drawLine(i * dotSizeWidth, 0, i * dotSizeWidth, height); // height
	}

	snake.paint(imageGraphics);
	apple.paint(imageGraphics);

	graphics.drawImage(image, 0, 0, this);
	repaint();
    } 
    
    public void startGame() {
	new Thread(snake).start();
	apple.setApple();
    }
}
