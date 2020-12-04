package org.academiadecodigo.tailormoons.world_of_ac_client.clientmanager;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientName {

    private final Socket clientSocket;
    private boolean isNameValid;


    public ClientName(Socket clientSocket) {
        this.clientSocket = clientSocket;
        requestName();
    }


    public void requestName() {
        String name = "";
        while (!isNameValid) {
            name = JOptionPane.showInputDialog("What is your name? (Max: 10)");
            if (name.isEmpty() || name.isBlank() || name.length() > 10) {
                name = "";
                continue;
            }
            testName(name);
        }
    }


    private void testName(String name) {
        String verification = "";
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("name#" + name);

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            verification = in.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        isNameValid = verification.equals("name#" + name);
    }

}
