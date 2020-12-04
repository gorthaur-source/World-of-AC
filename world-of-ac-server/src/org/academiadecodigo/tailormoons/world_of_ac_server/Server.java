package org.academiadecodigo.tailormoons.world_of_ac_server;

import org.academiadecodigo.tailormoons.world_of_ac_server.client_connection.ClientManager;
import org.academiadecodigo.tailormoons.world_of_ac_server.client_connection.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final ServerSocket serverSocket;
    private final ClientManager clientManager = new ClientManager();


    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }


    public void start() {

        Socket clientSocket;
        User user;
        ExecutorService service = Executors.newFixedThreadPool(35);

        while (!serverSocket.isClosed()) {

            try {
                clientSocket = serverSocket.accept();
                System.out.println("New client accepted");
            } catch (IOException exception) {
                System.err.println("Error getting new client");
                continue;
            }

            user = new User(clientSocket, clientManager);
            service.execute(user);

        }

        service.shutdownNow();
    }

}
