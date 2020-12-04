package org.academiadecodigo.tailormoons.world_of_ac_client.responsehandler;

import org.academiadecodigo.tailormoons.world_of_ac_client.clientmanager.ResponseHandler;

public class RemoveCommand implements Commands {

    @Override
    public void execute(String rawData, ResponseHandler responseHandler) {
        try {
            responseHandler.getClientManager().remove((Integer.parseInt(rawData)));
        } catch (NumberFormatException ex) {
            System.err.println("Invalid new client data " + ex.getMessage());
        }
    }
}
