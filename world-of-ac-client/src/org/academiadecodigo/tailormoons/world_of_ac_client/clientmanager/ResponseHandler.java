package org.academiadecodigo.tailormoons.world_of_ac_client.clientmanager;


import org.academiadecodigo.tailormoons.world_of_ac_client.clientmanager.ClientManager;
import org.academiadecodigo.tailormoons.world_of_ac_client.responsehandler.*;
import org.academiadecodigo.tailormoons.world_of_ac_client.screen.ChatBox;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    private final Socket serverSocket;
    private final Map<String, Commands> possibleCommands = new HashMap<>();
    private final ClientManager clientManager;
    private ChatBox chatBox;


    public ResponseHandler(Socket serverSocket) {
        this.serverSocket = serverSocket;
        clientManager = new ClientManager();
        setupCommands();
        chatBox = new ChatBox(30, 825, 600);
    }


    public void start() {
        BufferedReader in;

        try {
            in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

            while (!serverSocket.isClosed()) {
                String instructions = in.readLine();

                if (instructions == null) {
                    //send message to client - balão
                    serverSocket.close();
                    continue;
                }
                resolveInstructions(instructions);
            }

        } catch (IOException ex) {
            System.err.println("Could not receive server's instruction " + ex.getMessage());
        }
    }


    private void resolveInstructions(String instructions) {
        String[] temp = instructions.split("#", 2);
        if (temp.length < 2) {
            //warn server
            return;
        }

        System.out.println(Arrays.toString(temp));
        String requestedCommand = temp[0];
        Commands commands = possibleCommands.getOrDefault(requestedCommand, new InvalidCommand());
        commands.execute(temp[1], this);
    }


    private void setupCommands() {
        possibleCommands.put("text", new TextCommand());
        possibleCommands.put("move", new MoveCommand());
        possibleCommands.put("remove", new RemoveCommand());
        possibleCommands.put("add", new AddCommand());
        possibleCommands.put("game", new GameCommand());
        possibleCommands.put("name", new NameCommand());
    }


    public ClientManager getClientManager() {
        return clientManager;
    }


    public ChatBox getChatBox() {
        return chatBox;
    }

}
