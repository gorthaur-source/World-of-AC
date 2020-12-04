package org.academiadecodigo.tailormoons.world_of_ac_client;

import java.io.IOException;

public class ClientLauncher {

    public static void main(String[] args) {

       String host = "2.82.171.4";
       int port = 25565;

        if (args.length > 0) {
            host = args[0];
            if (args.length > 1) {
                try {
                    port = Integer.parseInt(args[1]);
                } catch (NumberFormatException ex) {
                    System.err.println("Invalid number format to port. USAGE: java ClientLauncher <host> <port>");
                }
            }
        }

        try {
            Client client = new Client(host, port);
            client.start();
        } catch (IOException ex) {
            System.err.println("Error opening connection with the server" + ex.toString());
        }
    }

}
