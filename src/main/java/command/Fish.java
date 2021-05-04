package command;

import board.Board;

import java.util.Random;

import static board.elems.Player.JATEKOS_TIPUS;
import static board.elems.Sea.SEA_TYPE;

public class Fish extends Command {

    private static final Random random = new Random();

    @Override
    protected boolean isApplicable(Board board) {
        return board
                .currField()
                .ellenorizFelsoKetTipusat(JATEKOS_TIPUS, SEA_TYPE);
    }

    @Override
    protected void execute(Board board) {
        if (random.nextInt(4) == 1) {
            board.getPlayer().pickup(new product.Fish());
        }
    }
}
