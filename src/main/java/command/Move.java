package command;

import board.Board;
import board.Direction;
import board.Elem;
import board.Field;

import static board.elems.Player.JATEKOS_TIPUS;
import static board.elems.Sea.SEA_TYPE;

public class Move extends Command {

    private final Direction direction;
    private boolean valid = true;

    Move(Direction direction) {
        this.direction = direction;
    }

    @Override
    protected boolean isApplicable(Board p) {
        Field field = p.currField();
        if (valid && field.getElems()
                .stream()
                .anyMatch(e -> e.tipus().equals(JATEKOS_TIPUS))) {
            valid = false;
            return p.isOnBoard(direction);
        }
        return false;
    }

    @Override
    protected void execute(Board p) {
        Elem elem = p.currField().levesz();
        Field nextField = p.getNextField(direction);
        nextField.lehelyez(elem);
        p.getPlayer().setInWater(nextField.hasElemWithType(SEA_TYPE)); //todo tenger higher than earth
    }

}
