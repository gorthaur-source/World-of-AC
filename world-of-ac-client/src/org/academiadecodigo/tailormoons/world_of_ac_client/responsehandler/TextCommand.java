package org.academiadecodigo.tailormoons.world_of_ac_client.responsehandler;

import org.academiadecodigo.tailormoons.world_of_ac_client.clientmanager.ResponseHandler;

public class TextCommand implements Commands {


    @Override
    public void execute(String rawData, ResponseHandler responseHandler) {

        String[] temp = rawData.split("#");
        if (temp.length < 3) {
            return;
        }

        try {
            Integer clientID = Integer.parseInt(temp[0]);
            String name = temp[1];
            String message = temp[2];
            responseHandler.getClientManager().sendText(clientID, message);
            responseHandler.getChatBox().addMessageToChatBox(name + ": " + message);
        } catch (NumberFormatException ex) {
            System.err.println("Invalid move client data " + ex.getMessage());
        }
    }

}
