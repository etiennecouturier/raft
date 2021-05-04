package command;

import board.Board;
import board.Field;
import product.Fire;

import static board.elems.Player.JATEKOS_TIPUS;
import static product.Fire.TUZ_TIPUS;

public class EatPotato extends Command {

    @Override
    protected boolean isApplicable(Board board) {
        Field field = board.currField();
        return field.ellenorizFelsoKetTipusat(JATEKOS_TIPUS, TUZ_TIPUS)
                && ((Fire) field.getSecondTopElem()).vanSultBurgonya();
    }

    @Override
    protected void execute(Board board) {
        Field field = board.currField();
        ((Fire) field.getSecondTopElem()).leveszBurgonya();
        board.getPlayer().eat();
    }

}
