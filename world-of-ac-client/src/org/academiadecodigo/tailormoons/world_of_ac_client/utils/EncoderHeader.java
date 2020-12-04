package org.academiadecodigo.tailormoons.world_of_ac_client.utils;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.tailormoons.world_of_ac_client.screen.KeyBoardListener;

import java.util.HashMap;

public class EncoderHeader {


    public static String getMoveHeader(KeyBoardListener listener) {

        StringBuilder header = new StringBuilder();
        header.append("move#");

        int moveX = 0;
        int moveY = 0;

        if (listener.isPressed(KeyboardEvent.KEY_UP)) {
            moveY += -2;
        }
        if (listener.isPressed(KeyboardEvent.KEY_DOWN)) {
            moveY += 2;
        }
        if (listener.isPressed(KeyboardEvent.KEY_LEFT)) {
            moveX += -2;
        }
        if (listener.isPressed(KeyboardEvent.KEY_RIGHT)) {
            moveX += 2;
        }

        header.append(moveX + "," +  moveY);
        return header.toString();
    }


    public static String getTextHeader(String content) {

        if (!content.startsWith(".")) return "text#" + content;

        content = content.substring(1);
        if (!content.contains(" ")) {
            return content + "#";
        }
        String command = content.substring(0, content.indexOf(" "));

        int numberOfArguments = 0;
        try {
            numberOfArguments = CommandsArguments.valueOf(command.toUpperCase()).getNumberOfArgs();
        } catch (IllegalArgumentException e) {
            System.err.println("Command not Found");
            numberOfArguments = 2;
        }

        String[] arguments = content.split(" ", numberOfArguments);

        StringBuilder header = new StringBuilder();

        for (String argument : arguments) {
            header.append(argument);
            header.append("#");
        }

        header.deleteCharAt(header.length() - 1);

        return header.toString();
    }

}
