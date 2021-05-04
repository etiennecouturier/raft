package command;

import board.Board;
import board.Elem;

import static board.elems.resource.Resource.nyersanyagE;
import static product.Net.NET_TYPE;

public class CollectNet extends Command {

    @Override
    protected boolean isApplicable(Board board) {
        return nyersanyagE(board.currField().getTopElem().tipus())
                && board.currField().getSecondTopElem().tipus().equals(NET_TYPE);
    }

    @Override
    protected void execute(Board board) {
        Elem elem = board.currField().levesz();
        board.getPlayer().pickup(elem);
    }

}
