package command;

import board.Board;
import board.Elem;
import board.Position;

import java.util.Random;

import static board.elems.Sea.SEA_TYPE;
import static board.elems.Shark.SHARK_TYPE;

public class MoveSharkPlayerOnRaft extends Command {

    private static final Random random = new Random();

    private boolean valid = true;

    @Override
    protected boolean isApplicable(Board board) {
        return mezoTipusu(board, SHARK_TYPE)
                && !board.getPlayer().isInWater()
                && valid;
    }

    @Override
    protected void execute(Board p) {
        Position newPosition = p.currField().getPosition().add(new Position(random.nextInt(3) - 1, random.nextInt(3) - 1));
        while (!tengerMezoE(p, newPosition)) {
            newPosition = p.currField().getPosition().add(new Position(random.nextInt(3) - 1, random.nextInt(3) - 1));
        }

        Elem elem = p.currField().levesz();
        p.getField(newPosition).lehelyez(elem);
        valid = false;
    }

    private boolean tengerMezoE(Board board, Position position) {
        return board.isOnBoard(position)
                && board.getField(position)
                .getTopElem()
                .tipus()
                .equals(SEA_TYPE);
    }
}
