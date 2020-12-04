package org.academiadecodigo.tailormoons.world_of_ac_client.utils;

public enum CommandsArguments {

    NAME(2),
    WHISPER(3),
    QUIT(1),
    GAME(1);

    private final int numberOfArgs;


    CommandsArguments(int numberOfArgs) {
        this.numberOfArgs = numberOfArgs;
    }


    public int getNumberOfArgs() {
        return numberOfArgs;
    }

}