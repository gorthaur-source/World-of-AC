package org.academiadecodigo.tailormoons.world_of_ac_client.responsehandler;

import org.academiadecodigo.tailormoons.world_of_ac_client.clientmanager.ResponseHandler;

import java.util.Arrays;

public class MoveCommand implements Commands {

    @Override
    public void execute(String rawData, ResponseHandler handler) {
        String [] temp = rawData.split("#");
        if(temp.length < 2){
            return;
        }
        try{
            Integer clientID = Integer.parseInt(temp[0]);
            String [] position = temp[1].split(",");
            System.out.println(Arrays.toString(position));
            Integer xPosition = Integer.parseInt(position[0]);
            Integer yPosition = Integer.parseInt(position[1]);
            handler.getClientManager().move(clientID,xPosition,yPosition);
        }catch (NumberFormatException ex){
            System.err.println("Invalid move client data " + ex.getMessage());

        }
    }

}
