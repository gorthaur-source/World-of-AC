package org.academiadecodigo.tailormoons.world_of_ac_server.game;

public interface Game {

    String getName();

    int gameLength();

    String question(int questionNumber);

    String possibleAnswer(int question, int answer);

    String correctAnswer(int questionNumber);

}
