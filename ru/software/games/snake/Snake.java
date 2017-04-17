package ru.software.games.snake;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MikeBear on 14.04.2017.
 */
public class Snake {

    private List<SnakeBodyItem> snakeBody = new ArrayList<>();

    public List<SnakeBodyItem> getSnakeBody() {
        return snakeBody;
    }

    private SnakeDirection direction;

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

    private boolean alive;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Snake(Position position) {
        snakeBody.add(new SnakeBodyItem(position.getCoordinateX(), position.getCoordinateY()));
        setAlive(true);
    }

    public boolean isInArea(Area area) {
        return getSnakeHead().getCoordinateX() >= 0 &&
                getSnakeHead().getCoordinateX() < area.getWidth() &&
                getSnakeHead().getCoordinateY() >= 0 &&
                getSnakeHead().getCoordinateY() < area.getHeight();
    }

    public boolean isCrossedSelf() {
        List<SnakeBodyItem> snakeBodyWithoutHead = snakeBody.subList(1, snakeBody.size());
        return snakeBodyWithoutHead.contains(getSnakeHead());
    }

    public Position getSnakeHead() {
        return snakeBody.get(0);
    }

    public void move() {
        increase(getNextPosition(direction));
        if (getSnakeHead().equals(SnakeGame.game.getFood()))
            SnakeGame.game.getFood().createFood();
        else
            snakeBody.remove(snakeBody.size() - 1);
    }

    private void increase(Position position) {
        snakeBody.add(0, new SnakeBodyItem(position.getCoordinateX(), position.getCoordinateY()));
    }

    private Position getNextPosition(SnakeDirection direction) {
        int nextCoordinateX = getSnakeHead().getCoordinateX();
        int nextCoordinateY = getSnakeHead().getCoordinateY();

        if (direction == SnakeDirection.UP)    nextCoordinateY--;
        if (direction == SnakeDirection.DOWN)  nextCoordinateY++;
        if (direction == SnakeDirection.LEFT)  nextCoordinateX--;
        if (direction == SnakeDirection.RIGHT) nextCoordinateX++;

        return new Position(nextCoordinateX, nextCoordinateY);
    }

}