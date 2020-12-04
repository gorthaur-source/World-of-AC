package org.academiadecodigo.tailormoons.world_of_ac_server;

import java.io.IOException;

public class ServerMain {

    public static void main(String[] args) {

        int port = 25565;

        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ex) {
                System.out.println("USAGE: java ClientMain <port>");
                return;
            }
        }

        Server server;

        try {
            server = new Server(port);
        } catch (IOException ex) {
            System.out.println("Error opening the server on port " + port + ". Error: " + ex.toString());
            return;
        }

        server.start();

    }

}
