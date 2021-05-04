package command;

import board.Board;
import product.WaterCleaner;

import static product.WaterCleaner.WATER_CLEANER_TYPE;

public class CleanWater extends Command {

    @Override
    protected boolean isApplicable(Board board) {
        return board
                .currField()
                .hasElemWithType(WATER_CLEANER_TYPE);
    }

    @Override
    protected void execute(Board board) {
        board
                .currField()
                .getElemsOfType(WATER_CLEANER_TYPE)
                .forEach(e -> ((WaterCleaner) e).clean());
    }

}
