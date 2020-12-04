package org.academiadecodigo.tailormoons.world_of_ac_client.clientmanager;


import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.HashMap;
import java.util.Map;

public class ClientManager {

    private Map<Integer, User> clients;
    public static final int SIZE = 60;


    public ClientManager() {
        clients = new HashMap<>();
    }

    public void remove(Integer clientID) {
        clients.get(clientID).delete();
        clients.remove(clientID, clients.get(clientID));
    }

    public void add(Integer clientID, int xPosition, int yPosition, String name) {
        boolean colorFound= false;
        UserColor color = UserColor.RED;

        for (UserColor userColor : UserColor.values()) {
            colorFound = true;

            for (User user : clients.values()) {
                if (user.getColor() == userColor) {
                    colorFound = false;
                    break;
                }
            }

            if(colorFound) {
                color = userColor;
                break;
            }
        }

        if (colorFound) {
            clients.put(clientID, new User(xPosition, yPosition, color, name));
        } else {
            clients.put(clientID, new User(xPosition, yPosition, UserColor.RED, name));
        }

        clients.get(clientID).draw();
    }


    public void move(Integer clientID, int xPosition, int yPosition) {
        clients.get(clientID).move(xPosition, yPosition);
    }

    public void sendText(Integer clientID, String message) {
        clients.get(clientID).addPopup(message);
    }


    public void changeName(Integer clientID, String name) {
        if(clients.containsKey(clientID)) {
            clients.get(clientID).setName(name);
        }
    }

}
