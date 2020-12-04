package org.academiadecodigo.tailormoons.world_of_ac_server.position;

import org.academiadecodigo.tailormoons.world_of_ac_server.client_connection.User;

import java.util.LinkedList;
import java.util.List;

public class CollisionDetector {


    public static boolean hasCollision(User user, List<User> users, int moveX, int moveY) {

        int x1 = user.getPosition().getX() + moveX;
        int y1 = user.getPosition().getY() + moveY;
        int diameter = Position.PLAYER_SIZE;

        for (User client : users) {

            if (client == user) {
                continue;
            }

            int x2 = client.getPosition().getX();
            int y2 = client.getPosition().getY();

            if (getCircleCenterDistance(x1, x2, y1, y2) < diameter) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSpawnInvalid(List<User> users, int x1, int y1) {

        int diameter = Position.PLAYER_SIZE;

        for (User user : users) {

            int x2 = user.getPosition().getX();
            int y2 = user.getPosition().getY();

            if(getCircleCenterDistance(x1, x2, y1, y2) < diameter) {
                return true;
            }
        }
        return false;

    }

    public static double getCircleCenterDistance(int x1, int x2, int y1, int y2) {

        int distX = x1 - x2;
        int distY = y1 - y2;

        return Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));

    }
}
