package command;

import board.Board;
import board.Field;
import product.WaterCleaner;

import static board.elems.Player.JATEKOS_TIPUS;
import static product.WaterCleaner.WATER_CLEANER_TYPE;

public class Drink extends Command {

    @Override
    protected boolean isApplicable(Board board) {
        Field field = board.currField();
        return field.ellenorizFelsoKetTipusat(JATEKOS_TIPUS, WATER_CLEANER_TYPE)
                && ((WaterCleaner) field.getSecondTopElem()).isGlassOfWaterCleaned();
    }

    @Override
    protected void execute(Board board) {
        Field field = board.currField();
        ((WaterCleaner) field.getSecondTopElem()).drawGlassOfWater();
        board.getPlayer().drink();
    }
}
