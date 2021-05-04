package command;

import board.Board;

import static board.elems.Nyom.NYOM_TIPUS;

public class EraseTraces extends Command {

    @Override
    protected boolean isApplicable(Board board) {
        return true;
    }

    @Override
    protected void execute(Board board) {
        board.currField().setParent(null);
        board.currField().setVisited(false);
        if (board.currField().getTopElem().tipus().equals(NYOM_TIPUS)) {
            board.currField().levesz();
        }
    }

}
