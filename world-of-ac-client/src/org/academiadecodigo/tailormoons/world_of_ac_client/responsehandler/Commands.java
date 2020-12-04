package org.academiadecodigo.tailormoons.world_of_ac_client.responsehandler;

import org.academiadecodigo.tailormoons.world_of_ac_client.clientmanager.ResponseHandler;

public interface Commands {

    void execute(String rawData, ResponseHandler responseHandler);

}
