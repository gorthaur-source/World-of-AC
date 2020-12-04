package org.academiadecodigo.tailormoons.world_of_ac_client.responsehandler;


import org.academiadecodigo.tailormoons.world_of_ac_client.clientmanager.ResponseHandler;

public class InvalidCommand implements Commands {

    @Override
    public void execute(String rawData, ResponseHandler responseHandler) {
        System.out.println("Impossible resolve command requested");
    }

}
