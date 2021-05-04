package command;

import board.Board;
import board.Direction;
import board.elems.Raft;

import java.util.Map;

import static board.elems.Sea.SEA_TYPE;
import static board.elems.resource.Leaf.LEAF_TYPE;
import static board.elems.resource.Plank.PLANK_TYPE;
import static java.util.Map.entry;
import static java.util.Map.ofEntries;

public class AddToRaft extends Command {

    private final Direction direction;

    AddToRaft(Direction direction) {
        this.direction = direction;
    }

    @Override
    protected boolean isApplicable(Board board) {
        return board.isOnBoard(direction)
                && jatekosFoldonEsMellettVmi(board, direction, SEA_TYPE);
    }

    @Override
    protected Map<String, Integer> price() {
        return ofEntries(
                entry(PLANK_TYPE, 2),
                entry(LEAF_TYPE, 2)
        );
    }

    @Override
    public void execute(Board board) {
        board.getNextField(direction).lehelyez(new Raft());
    }

}
