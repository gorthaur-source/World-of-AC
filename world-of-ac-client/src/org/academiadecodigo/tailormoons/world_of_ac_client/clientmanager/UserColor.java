package org.academiadecodigo.tailormoons.world_of_ac_client.clientmanager;

import org.academiadecodigo.simplegraphics.graphics.Color;

import java.util.NoSuchElementException;

public enum UserColor {

    RED(Color.RED),
    BLUE(Color.BLUE),
    LIGHT_GRAY(Color.LIGHT_GRAY),
    GRAY(Color.GRAY),
    DARK_GRAY(Color.DARK_GRAY),
    BLACK(Color.BLACK),
    CYAN(Color.CYAN),
    MAGENTA(Color.MAGENTA),
    YELLOW(Color.YELLOW),
    PINK(Color.PINK),
    ORANGE(Color.ORANGE),
    TAN(Color.TAN),
    THISTLE(Color.THISTLE),
    GOLDENROD(Color.GOLDENROD),
    MANDARIAN(Color.MANDARIAN),
    STEELBLUE(Color.STEELBLUE),
    REDBROWN(Color.REDBROWN),
    PURPLE(Color.PURPLE),
    QUARTZ(Color.QUARTZ),
    TURC(Color.TURC),
    YELLOWGREEN(Color.YELLOWGREEN),
    DARKGREEN(Color.DARKGREEN),
    VIOLET(Color.VIOLET),
    GREEN(Color.GREEN);

    private final Color color;

    UserColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
