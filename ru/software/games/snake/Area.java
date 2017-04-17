package ru.software.games.snake;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MikeBear on 14.04.2017.
 */
public class Area {

    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private Map<Integer, String> symbols;

    public Area(int width, int height) {
        this.width = width;
        this.height = height;

        field = new int[width + 2][height + 2];

        symbols = new HashMap<>();
        // Game symbols
        symbols.put(0, "   ");  // empty cell of Area
        symbols.put(1, " s ");  // body of Snake
        symbols.put(2, " S ");  // head of Snake
        symbols.put(3, " X ");  // head of Dead Snake
        symbols.put(4, " F ");  // Food
        // Area`s border symbols
        symbols.put(11, " + "); // corner of Area
        symbols.put(12, " - "); // horizontal border of Area
        symbols.put(13, " | "); // vertical border of Area
    }

    private int[][] field;

    public int[][] getField() {
        calculateField();
        return field;
    }

    public void print() {
        calculateField();

        for (int j = 0; j < height + 2; j++) {
            for (int i = 0; i < width + 2; i++)
                System.out.print(symbols.get(field[i][j]));
            System.out.println();
        }
    }

    private void calculateField() {
        Snake snake = SnakeGame.game.getSnake();
        Food food = SnakeGame.game.getFood();

        for (int j = 0; j < height + 2; j++) {
            for (int i = 0; i < width + 2; i++) {
                if (j == 0 || j == height + 1)
                    field[i][j] = (i == 0 || i == width + 1) ? 11 : 12;
                else
                    field[i][j] = (i == 0 || i == width + 1) ? 13 : 0;
            }
        }

        for (SnakeBodyItem snakeBodyItem : snake.getSnakeBody()) {
            int coordinateX = snakeBodyItem.getCoordinateX();
            int coordinateY = snakeBodyItem.getCoordinateY();
            field[coordinateX + 1][coordinateY + 1] = snakeBodyItem.equals(snake.getSnakeHead()) ? 2 : 1;
        }

        field[food.getCoordinateX() + 1][food.getCoordinateY() + 1] = 4;

        if (!snake.isAlive()) {
            field[snake.getSnakeHead().getCoordinateX() + 1][snake.getSnakeHead().getCoordinateY() + 1] = 3;
        }
    }

}