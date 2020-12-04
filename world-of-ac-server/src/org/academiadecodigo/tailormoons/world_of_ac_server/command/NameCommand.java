package org.academiadecodigo.tailormoons.world_of_ac_server.command;

import org.academiadecodigo.tailormoons.world_of_ac_server.client_connection.ClientManager;
import org.academiadecodigo.tailormoons.world_of_ac_server.client_connection.User;

public class NameCommand implements CommandHandler {

    @Override
    public void run(User user, ClientManager clientManager, String text) {
        user.setName(text);
        clientManager.broadcast("name#" + user.getId() + "#" + user.getName());
    }

}
