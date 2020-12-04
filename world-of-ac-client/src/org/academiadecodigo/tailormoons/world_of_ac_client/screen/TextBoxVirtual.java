package org.academiadecodigo.tailormoons.world_of_ac_client.screen;

import org.academiadecodigo.simplegraphics.graphics.Line;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;


public class TextBoxVirtual {

    private final Line cursor;
    private final Text label;
    private String text = "";


    public TextBoxVirtual() {
        Rectangle textBox = new Rectangle(0, Screen.HEIGHT, Screen.WIDTH - 83, 25);
        textBox.draw();
        cursor = new Line(5, Screen.HEIGHT + 2, 5, Screen.HEIGHT + 21);
        cursor.draw();
        label = new Text(5, Screen.HEIGHT + 2, "");
        label.draw();
    }


    public void addToText(int textASCII) {
        char letter = (char) textASCII;
        text = (text + letter).toLowerCase();
        int oldX = label.getX() + label.getWidth();
        label.setText(text.toLowerCase());
        cursor.translate((label.getX() + label.getWidth()) - oldX, 0);
    }


    public void clearText() {
        int oldX = label.getX() + label.getWidth();
        label.setText("");
        text = "";
        cursor.translate(5 - oldX, 0);
    }


    public String getText() {
        return text.toLowerCase();
    }


    public void erase() {
        if (text.isEmpty()) {
            return;
        }
        int oldX = label.getX() + label.getWidth();
        text = (text.substring(0, text.length() - 1)).toLowerCase();
        label.setText(text);
        cursor.translate((label.getWidth() + label.getX()) - oldX, 0);
    }

}
