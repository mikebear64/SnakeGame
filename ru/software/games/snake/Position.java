package ru.software.games.snake;

/**
 * Created by MikeBear on 15.04.2017.
 */
public class Position {

    private int coordinateX;
    private int coordinateY;

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public Position(int coordinateX, int coordinateY) {
        setCoordinateX(coordinateX);
        setCoordinateY(coordinateY);
    }

    @Override
    public int hashCode() {
        return coordinateX * 100 + coordinateY;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Position)) return false;

        int objCoordinateX = ((Position) obj).coordinateX;
        int objCoordinateY = ((Position) obj).coordinateY;
        return this.coordinateX == objCoordinateX && this.coordinateY == objCoordinateY;
    }

}
