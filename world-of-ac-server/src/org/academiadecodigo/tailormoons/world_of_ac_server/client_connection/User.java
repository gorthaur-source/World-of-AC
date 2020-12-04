package org.academiadecodigo.tailormoons.world_of_ac_server.client_connection;

import org.academiadecodigo.tailormoons.world_of_ac_server.position.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class User implements Runnable {

    private final Socket clientSocket;
    private final ClientManager clientManager;

    private PrintWriter out;

    private int id;
    private String name;
    private Position position;
    private boolean admin;


    public User(Socket clientSocket, ClientManager clientManager) {
        this.clientSocket = clientSocket;
        this.clientManager = clientManager;
    }


    @Override
    public void run() {

        this.id = clientManager.getCounterID();
        this.position = clientManager.getNewPosition();
        clientManager.addUser(this, clientSocket);

        BufferedReader bufferedReader;
        String line;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while (!clientSocket.isClosed()) {

                line = bufferedReader.readLine();

                if (line == null) {
                    closeClient();
                    continue;
                }

                clientManager.receive(line, this);
            }

        } catch (IOException ex) {
            System.err.println("Error reading output from client. ID: " + id + " " + ex.toString());
            closeClient();
        }
    }


    public void sendMessageUser(String message) {
        try {
            out = new PrintWriter(getClientSocket().getOutputStream(), true);
            out.println(message);
        } catch (IOException ex) {
            System.err.println("Error sending message. " + ex.toString());
        }
    }


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public Position getPosition() {
        return position;
    }


    public boolean isAdmin() {
        return admin;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setAdmin(boolean admin) {
        this.admin = admin;
    }


    public Socket getClientSocket() {
        return clientSocket;
    }


    public void closeClient() {
        try {
            clientSocket.close();
            clientManager.removeUser(this);
        } catch (IOException ex) {
            System.out.println("Error closing client. " + ex.toString());
        }
    }

}
