package org.academiadecodigo.tailormoons.world_of_ac_client.clientmanager;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.tailormoons.world_of_ac_client.screen.Screen;

import java.awt.*;

public class Popup implements Runnable {

    private final Rectangle rectangle;
    private final Rectangle border;
    private Text[] labels = new Text[2];
    private final User user;


    public Popup(String message, User user) {

        this.user = user;

        int x = user.getEllipse().getX() - 64;
        int y = user.getEllipse().getY() - 34;

        if (user.getEllipse().getY() <= 38) {
            x = user.getEllipse().getX() - 64;
            y = user.getEllipse().getY() + ClientManager.SIZE + 5;
        }

        if (x < 0) {
            x = 2;
        }

        if (x + 180 > Screen.WIDTH) {
            x = Screen.WIDTH - 182;
        }

        rectangle = new Rectangle(x, y, 180, 30);
        String[] labelBuilder = new String[2];

        int startChar = 0;
        int endChar = Math.min(message.length(), 15);

        for (int j = 0; j < labelBuilder.length; j++) {

            labelBuilder[j] = message.substring(startChar, endChar);

            if (message.length() <= 15) break;

            else if (message.length() >= 30) {
                startChar = endChar;
                endChar += 15;

            } else {
                int temp = startChar;
                startChar = endChar;
                endChar = message.length() - temp;
            }
        }

        border = new Rectangle(x, y, 180, 30);
        rectangle.setColor(Color.WHITE);
        rectangle.fill();
        border.draw();



        int offsetY = 0;

        for (int i = 0; i < labels.length; i++) {
            if (labelBuilder[i] == null) break;
            labels[i] = new Text(rectangle.getX(), rectangle.getY() + offsetY, labelBuilder[i]);
            labels[i].setColor(user.getColor().getColor());
            labels[i].draw();
            offsetY += 10;
        }


    }

    public void translate(int x, int y) {

        for (Text label : labels) {

            if (label != null) {
                label.translate(x, y);
            }
        }

        rectangle.translate(x, y);
        border.translate(x, y);

    }

    public void remove() {

        for (Text label : labels) {
            if (label != null) {
                label.delete();
            }
        }
        rectangle.delete();
        border.delete();

    }

    @Override
    public void run() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        user.removePopup();
    }
}
