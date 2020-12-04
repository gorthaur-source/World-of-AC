package org.academiadecodigo.tailormoons.world_of_ac_server.command;

import org.academiadecodigo.tailormoons.world_of_ac_server.client_connection.ClientManager;
import org.academiadecodigo.tailormoons.world_of_ac_server.client_connection.User;

public class AddCommand implements CommandHandler {

    @Override
    public void run(User user, ClientManager clientManager, String text) {
        for (User client : clientManager.getUsers()) {
            if (client == user) {
                continue;
            }
            user.sendMessageUser("add#" + client.getId() + "#" + client.getName() + "#" + client.getPosition().getX() + "," + client.getPosition().getY());
        }
        clientManager.broadcast("add#" + user.getId() + "#" + user.getName() + "#" + user.getPosition().getX() + "," + user.getPosition().getY());
    }

}
