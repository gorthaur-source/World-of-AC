package org.academiadecodigo.tailormoons.world_of_ac_server.command;

import org.academiadecodigo.tailormoons.world_of_ac_server.client_connection.ClientManager;
import org.academiadecodigo.tailormoons.world_of_ac_server.client_connection.User;

public class InvalidCommand implements CommandHandler {

    @Override
    public void run(User user, ClientManager clientManager, String text) {
        System.err.println("client: " + user.getName() + " got an error in his request. \"" + user.getName() + "\"");
    }

}
