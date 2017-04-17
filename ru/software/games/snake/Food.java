package ru.software.games.snake;

/**
 * Created by MikeBear on 14.04.2017.
 */
public class Food extends Position {

    public Food(int coordinateX, int coordinateY) {
        super(coordinateX, coordinateY);
    }

    public void createFood() {
        Snake snake = SnakeGame.game.getSnake();

        int coordinateX;
        int coordinateY;

        do {
            coordinateX = (int) (Math.random() * SnakeGame.game.getArea().getWidth());
            coordinateY = (int) (Math.random() * SnakeGame.game.getArea().getHeight());
        } while (snake.getSnakeBody().contains(new Position(coordinateX, coordinateY)));

        setCoordinateX(coordinateX);
        setCoordinateY(coordinateY);
    }

}
