package de.zokki.snake.Gui;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import de.zokki.snake.Snake.Snake;
import de.zokki.snake.Utils.GlobalSettings;

public class Gui extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private Panel panel = new Panel();
    
    public Gui(String title) {
	super(title);
	add(panel);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(true);
	setBackground(Color.DARK_GRAY);
	setVisible(true);
	pack();
	setLocationRelativeTo(null);
	
	addListeners();
    }

    public void startGame() {
	panel.startGame();
    }
    
    public void restartGame() {
	remove(panel);
	panel = new Panel();
	add(panel);
	repaint();
	validate();
	startGame();
    }

    private void addListeners() {
	addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		Snake snake = GlobalSettings.getInstance().getSnake();
		int xDir = snake.getCurrentXDir();
		int yDir = snake.getCurrentYDir();
		
		if ((e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) && yDir != 1) {
		    snake.setYDir(-1);
		    snake.setXDir(0);
		} else if ((e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) && yDir != -1) {
		    snake.setYDir(1);
		    snake.setXDir(0);
		} else if ((e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) && xDir != 1) {
		    snake.setXDir(-1);
		    snake.setYDir(0);
		} else if ((e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) && xDir != -1) {
		    snake.setXDir(1);
		    snake.setYDir(0);
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
		    System.out.println("space");
		    snake.setXDir(0);
		    snake.setYDir(0);
		}
	    }
	});
    }
}
