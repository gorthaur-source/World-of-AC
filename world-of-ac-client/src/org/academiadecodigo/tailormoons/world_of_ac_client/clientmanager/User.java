package org.academiadecodigo.tailormoons.world_of_ac_client.clientmanager;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class User {

    private final Ellipse ellipse;
    private final UserColor color;
    private final Rectangle rectangleName;
    private final Text textName;
    private Popup popup;
    private String name;


    public User(int x, int y, UserColor color, String name) {

        this.color = color;
        this.name = name;

        ellipse = new Ellipse(x, y, ClientManager.SIZE, ClientManager.SIZE);
        ellipse.setColor(color.getColor());
        rectangleName = new Rectangle(x + 5, y + 23, 50, 15);
        rectangleName.setColor(Color.WHITE);
        textName = new Text(x + 6, y + 22, name);
    }


    public void draw() {
        ellipse.fill();
        rectangleName.fill();
        textName.draw();
    }


    public void move(int x, int y) {

        ellipse.translate(x, y);
        rectangleName.translate(x, y);
        textName.translate(x, y);

        if (popup != null) {
            popup.translate(x, y);
        }
    }


    public void addPopup(String message) {

        if (popup != null) {
            removePopup();
        }

        popup = new Popup(message, this);
        Thread thread = new Thread(popup);
        thread.start();

    }


    public void removePopup() {

        if (popup != null) {
            popup.remove();
            popup = null;
        }
    }


    public Ellipse getEllipse() {
        return ellipse;
    }


    public UserColor getColor() {
        return color;
    }


    public void delete() {
        ellipse.delete();
        rectangleName.delete();
        textName.delete();
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
        textName.setText(name);
    }

}
