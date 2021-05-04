package command;

import board.Board;
import product.Fire;

import static product.Fire.TUZ_TIPUS;

public class PrepareFood extends Command {

    @Override
    protected boolean isApplicable(Board board) {
        return board
                .currField()
                .hasElemWithType(TUZ_TIPUS);
    }

    @Override
    protected void execute(Board board) {
        board
                .currField()
                .getElemsOfType(TUZ_TIPUS)
                .forEach(e -> ((Fire) e).sul());
    }
}
