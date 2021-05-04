package command;

import board.Board;
import board.Elem;
import board.Field;
import product.Net;

import static board.elems.Sea.SEA_TYPE;
import static board.elems.resource.Resource.nyersanyagE;

public class MoveResources extends Command {

    @Override
    protected boolean isApplicable(Board board) {
        Field field = board.currField();
        return nyersanyagE(field.getTopElem().tipus());
    }

    @Override
    protected void execute(Board board) {
        leMozgat(board);
    }

    private void leMozgat(Board p) {
        Field[][] palya = p.getFields();
        int currI = p.getCurrI();
        int currJ = p.getCurrJ();
        Elem elem = palya[currI][currJ].levesz();
        if (currI < palya.length - 1
                && (palya[currI + 1][currJ].getTopElem().tipus().equals(SEA_TYPE)
                || palya[currI + 1][currJ].getTopElem().tipus().equals(Net.NET_TYPE))) {
            palya[currI + 1][currJ].lehelyez(elem);
        }
    }

}
