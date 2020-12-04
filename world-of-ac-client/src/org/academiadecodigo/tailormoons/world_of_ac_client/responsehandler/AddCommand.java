package org.academiadecodigo.tailormoons.world_of_ac_client.responsehandler;

import org.academiadecodigo.tailormoons.world_of_ac_client.clientmanager.ResponseHandler;

public class AddCommand implements Commands {

    @Override
    public void execute(String rawData, ResponseHandler handler) {

        String[] temp = rawData.split("#");
        if (temp.length < 3) {
            return;
        }

        try {
            Integer clientID = Integer.parseInt(temp[0]);
            String name = temp[1];
            String[] position = temp[2].split(",");
            int xPosition = Integer.parseInt(position[0]);
            int yPosition = Integer.parseInt(position[1]);
            handler.getClientManager().add(clientID, xPosition, yPosition, name);
        } catch (NumberFormatException ex) {
            System.err.println("Invalid new client data " + ex.getMessage());
        }
    }

}
