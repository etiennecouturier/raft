package runner;

import board.Board;
import command.*;

import java.util.ArrayList;
import java.util.List;

import static command.CommandFactory.letrehozParancs;

public class CommandHandler {

    private final Board board;

    public CommandHandler(Board board) {
        this.board = board;
    }

    public void execute(int commandCode) {
        prepareCommands(commandCode);
        for (Command p : prepareCommands(commandCode)) {
            board.iterate(p);
        }
    }

    private List<Command> prepareCommands(int commandCode) {
        List<Command> commands = new ArrayList<>();
        commands.add(new EraseTraces());
        commands.add(letrehozParancs(commandCode));
        commands.add(new MoveResources());
        commands.add(new GenerateResources());
        commands.add(new DecrementPlayerLife());
        commands.add(new CleanWater());
        commands.add(new PrepareFood());
        commands.add(new CollectNet());
        commands.add(new MoveSharkPlayerOnRaft());
        commands.add(new MoveSharkPlayerInWater());
        return commands;
    }

}
