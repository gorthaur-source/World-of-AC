package org.academiadecodigo.tailormoons.world_of_ac_server.command;

public enum Command {
    MOVE(new MoveCommand()),
    QUIT(new QuitCommand()),
    TEXT(new TextCommand()),
    NAME(new NameCommand()),
    WHISPER(new WhisperCommand()),
    ADD(new AddCommand()),
    GAME(new GameStartCommand()),

    INVALID(new InvalidCommand());

    private final CommandHandler commandHandler;


    Command(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }


    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

}
