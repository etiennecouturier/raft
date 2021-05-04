package command;

import board.Board;
import board.Field;
import product.Fire;
import product.Fish;

import static board.elems.Player.JATEKOS_TIPUS;
import static product.Fire.TUZ_TIPUS;
import static product.Fish.FISH_TYPE;

public class BakeFish extends Command {

    @Override
    protected boolean isApplicable(Board board) {
        Field field = board.currField();
        return field.ellenorizFelsoKetTipusat(JATEKOS_TIPUS, TUZ_TIPUS)
                && board.getPlayer().countItem(FISH_TYPE) > 0;
    }

    @Override
    protected void execute(Board board) {
        Field field = board.currField();
        board.getPlayer().decrementItem(FISH_TYPE, 1);
        ((Fire) field.getSecondTopElem()).rarak(new Fish());
    }

}
