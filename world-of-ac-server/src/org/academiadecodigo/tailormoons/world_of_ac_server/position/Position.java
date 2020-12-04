package org.academiadecodigo.tailormoons.world_of_ac_server.position;

public class Position {

    private int x;
    private int y;
    private final int width;
    private final int height;

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final int PLAYER_SIZE = 60;


    public Position(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }


    public int getMaxX() {
        return x + width;
    }


    public int getMaxY() {
        return y + height;
    }


    public boolean setPosition(int moveX, int moveY) {

        if (x + moveX < 0 || getMaxX() + moveX > WINDOW_WIDTH || y + moveY < 0 || getMaxY() + moveY > WINDOW_HEIGHT) {
            return false;
        }

        x += moveX;
        y += moveY;
        return true;
    }

}
