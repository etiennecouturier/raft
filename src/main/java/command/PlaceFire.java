package command;

import board.Board;
import board.Direction;
import product.Fire;

import java.util.Map;

import static board.elems.Raft.RAFT_TYPE;
import static board.elems.resource.Leaf.LEAF_TYPE;
import static board.elems.resource.Plank.PLANK_TYPE;
import static board.elems.resource.Trash.TRASH_TYPE;
import static java.util.Map.entry;
import static java.util.Map.ofEntries;

public class PlaceFire extends Command {

    private final Direction direction;

    PlaceFire(Direction direction) {
        this.direction = direction;
    }

    @Override
    protected boolean isApplicable(Board board) {
        return jatekosEsMellettVmi(board, direction, RAFT_TYPE);
    }

    @Override
    protected Map<String, Integer> price() {
        return ofEntries(
                entry(PLANK_TYPE, 2),
                entry(LEAF_TYPE, 4),
                entry(TRASH_TYPE, 3)
        );
    }

    @Override
    public void execute(Board p) {
        p.getNextField(direction).lehelyez(new Fire());
    }
}
