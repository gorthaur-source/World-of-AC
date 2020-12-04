package org.academiadecodigo.tailormoons.world_of_ac_server.game;

import org.academiadecodigo.tailormoons.world_of_ac_server.client_connection.ClientManager;
import org.academiadecodigo.tailormoons.world_of_ac_server.client_connection.User;

import java.util.*;

public class GameManager implements Runnable {


    private int currentQuestion;
    private ClientManager clientManager;
    private Map<Integer, Integer> participants = new HashMap<>();
    private Game game;
    private ArrayList<User> answerCurrentQuestion = new ArrayList<>();

    public GameManager(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    @Override
    public void run() {
        game = new GeographyGame(this);
        clientManager.broadcast("game#" + game.getName() + " is going to start");
        try {
            Thread.sleep(1000);
            for (int i = 0; i < game.gameLength(); i++) {
                int question = i + 1;
                clientManager.broadcast("game#" + "Question no." + question + ": " + game.question(i));
                currentQuestion = i;
                Thread.sleep(3000);
                for (int j = 0; j < 4; j++) {
                    clientManager.broadcast("game#" + game.possibleAnswer(i, j));
                }
                Thread.sleep(10000);
                clientManager.broadcast("game#" + "The correct answer is " + game.correctAnswer(i));
                answerCurrentQuestion.clear();
                Thread.sleep(2000);
            }
            if (participants.size() == 0) {
                clientManager.broadcast("game#" + "There are no winners");
            } else {
                clientManager.broadcast("game#" + "The winner of this game is " + clientManager.getUsers().get(winner().getKey()).getName() + " with " + winner().getValue()
                        + " points");
            }
            participants.clear();
            clientManager.finishGame();
        } catch (InterruptedException e) {
            System.err.println("Could not launch game" + e.getMessage());
        }

    }


    public void checkAnswer(User user, String clientAnswer) {
        String[] temp = clientAnswer.split(" ");
        if (temp[0] == null || temp[0].equals("")) {
            return;
        }

        if (answerCurrentQuestion.contains(user)) {
            return;
        }
        answerCurrentQuestion.add(user);

        String answer = temp[0].toUpperCase();
        if (answer.equals(game.correctAnswer(currentQuestion))) {
            if (participants.containsKey(user.getId())) {
                participants.replace(user.getId(), participants.get(user.getId()), participants.get(user.getId()) + 1);
                return;
            }
            participants.put(user.getId(), 1);
        }
    }


    private Map.Entry<Integer, Integer> winner() {
        ArrayList<Map.Entry<Integer, Integer>> temp = new ArrayList<>(participants.entrySet());

        Comparator<Map.Entry<Integer, Integer>> comparator = new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                Integer int1 = o1.getValue();
                Integer int2 = o2.getValue();
                return int1.compareTo(int2);
            }
        };

        Collections.sort(temp, comparator);
        System.out.println("Key " + temp.get(temp.size()-1).getKey());
        System.out.println("Value " + temp.get(temp.size()-1).getValue());

        return temp.get(temp.size() - 1);
    }

}
