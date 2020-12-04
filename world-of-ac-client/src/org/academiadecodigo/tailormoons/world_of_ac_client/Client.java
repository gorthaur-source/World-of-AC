package org.academiadecodigo.tailormoons.world_of_ac_client;

import org.academiadecodigo.tailormoons.world_of_ac_client.clientmanager.ClientName;
import org.academiadecodigo.tailormoons.world_of_ac_client.clientmanager.ResponseHandler;
import org.academiadecodigo.tailormoons.world_of_ac_client.screen.Screen;

import java.io.IOException;
import java.net.Socket;

public class Client {

    private final Socket clientSocket;


    public Client(String host, int serverPort) throws IOException {
        clientSocket = new Socket(host, serverPort);
    }


    public void start() {
        ClientName clientName = new ClientName(clientSocket);

        Screen screen = new Screen(clientSocket);
        ResponseHandler responseHandler = new ResponseHandler(clientSocket);
        responseHandler.start();
    }

}
