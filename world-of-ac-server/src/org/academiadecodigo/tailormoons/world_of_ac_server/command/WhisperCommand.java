package org.academiadecodigo.tailormoons.world_of_ac_server.command;

import org.academiadecodigo.tailormoons.world_of_ac_server.client_connection.ClientManager;
import org.academiadecodigo.tailormoons.world_of_ac_server.client_connection.User;

public class WhisperCommand implements CommandHandler {

    @Override
    public void run(User user, ClientManager clientManager, String text) {
        String[] args = text.split("#");
        for (User client : clientManager.getUsers()) {
            if (client.getName().equals(args[0])) {
                client.sendMessageUser("text#" + user.getId() + "#" + text);
            }
        }
    }

}
