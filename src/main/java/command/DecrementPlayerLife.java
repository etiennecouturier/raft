package command;

import board.Board;
import board.Field;

import static board.elems.Player.JATEKOS_TIPUS;

public class DecrementPlayerLife extends Command {

    @Override
    protected boolean isApplicable(Board board) {
        Field field = board.currField();
        return field.getTopElem().tipus().equals(JATEKOS_TIPUS);
    }

    @Override
    protected void execute(Board board) {
        board.getPlayer().decrementHealth();
        board.getPlayer().decrementTimeTillRescued();
    }
}
