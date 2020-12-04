package org.academiadecodigo.tailormoons.world_of_ac_client.responsehandler;

import org.academiadecodigo.tailormoons.world_of_ac_client.clientmanager.ResponseHandler;

public class NameCommand implements Commands {


    @Override
    public void execute(String rawData, ResponseHandler responseHandler) {

        String[] temp = rawData.split("#");
        if (temp.length < 2) {
            return;
        }

        responseHandler.getClientManager().changeName(Integer.parseInt(temp[0]), temp[1]);
    }

}
