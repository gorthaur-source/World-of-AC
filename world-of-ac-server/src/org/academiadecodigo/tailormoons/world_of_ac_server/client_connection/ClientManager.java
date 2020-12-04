package org.academiadecodigo.tailormoons.world_of_ac_server.client_connection;

import org.academiadecodigo.tailormoons.world_of_ac_server.command.Command;
import org.academiadecodigo.tailormoons.world_of_ac_server.command.CommandHandler;
import org.academiadecodigo.tailormoons.world_of_ac_server.command.GameStartCommand;
import org.academiadecodigo.tailormoons.world_of_ac_server.game.GameManager;
import org.academiadecodigo.tailormoons.world_of_ac_server.position.CollisionDetector;
import org.academiadecodigo.tailormoons.world_of_ac_server.position.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class ClientManager {

    private int counterID = 0;

    //added for gameplay
    private boolean gameGoing;
    private GameManager gameManager = new GameManager(this);
    private Thread newGame;

    private final List<User> users = Collections.synchronizedList(new LinkedList<>());


    public void receive(String message, User user) {
        String[] args = message.split("#", 2);
        args[0] = args[0].toUpperCase();

        Command commandHandler;
        try {
            commandHandler = Command.valueOf(args[0]);
        } catch (IllegalArgumentException ex) {
            commandHandler = Command.INVALID;
            commandHandler.getCommandHandler().run(user, this, message);
            return;
        }
        commandHandler.getCommandHandler().run(user, this, (args.length > 1) ? args[1] : "");

        //new addition for gameplay
        if (gameGoing) {
            gameManager.checkAnswer(user, args[1]);
        }

    }


    public void broadcast(String message) {
        synchronized (users) {
            for (User client : users) {
                client.sendMessageUser(message);
            }
        }
    }


    public void addUser(User user, Socket clientSocket) {

        boolean nameConfirmed = false;
        BufferedReader bufferedReader = null;
        PrintWriter out = null;

        while (!nameConfirmed) {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String name = bufferedReader.readLine();

                if (name == null) {
                    clientSocket.close();
                    return;
                }

                name = name.substring(name.indexOf("#") + 1);

                out = new PrintWriter(clientSocket.getOutputStream());

                nameConfirmed = true;
                for (User client : users) {
                    if (client.getName().equals(name)) {
                        user.sendMessageUser("name#");
                        nameConfirmed = false;
                        System.out.println("name not confirmed " + name);
                        break;
                    }
                }

                if (nameConfirmed) {
                    user.sendMessageUser("name#" + name);
                    user.setName(name);
                    System.out.println("name confirmed " + name);
                }
            } catch (IOException e) {
                return;
            }
        }
        System.out.println("continue");
        users.add(user);
        Command.ADD.getCommandHandler().run(user, this, "");
    }


    public void removeUser(User user) {
        users.remove(user);
        Command.QUIT.getCommandHandler().run(user, this, "");
    }


    public int getCounterID() {
        return counterID++;
    }


    public List<User> getUsers() {
        return users;
    }


    public Position getNewPosition() {
        synchronized (this) {
            int x, y;
            Position pos;
            do {

                x = new Random().nextInt(Position.WINDOW_WIDTH - 60);
                y = new Random().nextInt(Position.WINDOW_HEIGHT - 83);
                pos = new Position(x, y, Position.PLAYER_SIZE, Position.PLAYER_SIZE);

            } while (CollisionDetector.isSpawnInvalid(users, x, y));
            return pos;
        }
    }


    //added for game play


    public void gameStart() {
        gameGoing = true;
        newGame = new Thread(gameManager);
        newGame.start();
    }


    public void finishGame() {
        gameGoing = false;
        newGame.interrupt();
    }

}


