package command;

import board.Board;
import board.Direction;
import board.Elem;

import static board.elems.Player.JATEKOS_TIPUS;
import static board.elems.resource.Resource.nyersanyagE;

public class PickUpResource extends Command {

    private final Direction direction;

    PickUpResource(Direction direction) {
        this.direction = direction;
    }

    @Override
    protected boolean isApplicable(Board board) {
        return nyersanyagE(board.getNextField(direction)
                .getTopElem()
                .tipus())
                && board.currField().getTopElem().tipus().equals(JATEKOS_TIPUS);
    }

    @Override
    protected void execute(Board p) {
        Elem elem = p.getNextField(direction).levesz();
        p.getPlayer().pickup(elem);
    }
}
