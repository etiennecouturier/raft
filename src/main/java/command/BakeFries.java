package command;

import board.Board;
import board.Field;
import product.Fire;
import product.Potato;

import static board.elems.Player.JATEKOS_TIPUS;
import static product.Fire.TUZ_TIPUS;
import static product.Potato.POTATO_TYPE;

public class BakeFries extends Command {

    protected boolean isApplicable(Board board) {
        Field field = board.currField();
        return field.ellenorizFelsoKetTipusat(JATEKOS_TIPUS, TUZ_TIPUS)
                && board.getPlayer().countItem(POTATO_TYPE) > 0;
    }

    @Override
    protected void execute(Board board) {
        Field field = board.currField();
        board.getPlayer().decrementItem(POTATO_TYPE, 1);
        ((Fire) field.getSecondTopElem()).rarak(new Potato());
    }
}
