package org.academiadecodigo.tailormoons.world_of_ac_server.command;

import org.academiadecodigo.tailormoons.world_of_ac_server.client_connection.ClientManager;
import org.academiadecodigo.tailormoons.world_of_ac_server.client_connection.User;

import java.io.IOException;

public class QuitCommand implements CommandHandler {

    @Override
    public void run(User user, ClientManager clientManager, String text) {
        try {
            user.getClientSocket().close();
        } catch (IOException ex) {
            System.err.println("Error closing clientSocket. " + ex.toString());
        }
        clientManager.broadcast("remove#" + user.getId());
        System.out.println("Client removed");
    }

}
