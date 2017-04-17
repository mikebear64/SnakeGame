package ru.software.games.snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by MikeBear on 14.04.2017.
 */
public class SnakeGame implements Observer{

    private Area area;
    private Food food;
    private Snake snake;

    public Area getArea() {
        return area;
    }

    public Food getFood() {
        return food;
    }

    public Snake getSnake() {
        return snake;
    }

    public static SnakeGame game = new SnakeGame();

    public SnakeGame() {
        area = new Area(15, 15);
        snake = new Snake(new Position(8, 8));
        snake.setDirection(SnakeDirection.UP);
        food = new Food((int) (Math.random() * area.getWidth()), (int) (Math.random() * area.getHeight()));

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof KeyPressed) {
            KeyEvent keyEvent = (KeyEvent) arg;
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.VK_UP:
                    snake.setDirection(SnakeDirection.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    snake.setDirection(SnakeDirection.DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    snake.setDirection(SnakeDirection.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    snake.setDirection(SnakeDirection.RIGHT);
                    break;
                case KeyEvent.VK_ESCAPE:
                    System.out.println("--- === G A M E   C L O S E D === ---");
                    System.exit(0);
            }
        }
    }

    public void run() {
        KeyPressed keyPressed = new KeyPressed();
        keyPressed.addObserver(this);
        new Thread(keyPressed).run();

        while (snake.isAlive()) {
            snake.move();
            if (!snake.isInArea(area) || snake.isCrossedSelf()) snake.setAlive(false);
            area.print();
            delay();
        }

        System.out.println("--- === G A M E   O V E R ! ! ! === ---");

        System.out.println("\nYour level is " + snake.getSnakeBody().size());

        System.exit(0);
    }

    private void delay() {
        int level = snake.getSnakeBody().size();
        int delay = 1000 - (int) (Math.log10(level)/Math.log10(100) * 1000);
        if (delay < 250) delay = 250;
        try {
            Thread.sleep(delay);
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        game.run();
    }

}