package org.academiadecodigo.tailormoons.world_of_ac_server.command;

import org.academiadecodigo.tailormoons.world_of_ac_server.client_connection.ClientManager;
import org.academiadecodigo.tailormoons.world_of_ac_server.client_connection.User;
import org.academiadecodigo.tailormoons.world_of_ac_server.position.CollisionDetector;

public class MoveCommand implements CommandHandler {

    @Override
    public void run(User user, ClientManager clientManager, String text) {

        String[] parsedMovement = text.split(",");

        int moveX = Integer.parseInt(parsedMovement[0]);
        int moveY = Integer.parseInt(parsedMovement[1]);


        if (CollisionDetector.hasCollision(user, clientManager.getUsers(), moveX, moveY)) {
            return;
        }

        if (user.getPosition().setPosition(moveX, moveY)) {
            clientManager.broadcast("move#" + user.getId() + "#" + moveX + "," + moveY);
        }
    }

}
