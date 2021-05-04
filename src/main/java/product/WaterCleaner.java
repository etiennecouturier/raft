package product;

import board.Elem;

public class WaterCleaner implements Elem {

    public static final String WATER_CLEANER_TYPE = "viztisztito";

    private int waterCleaned = 0;

    public void clean() {
        waterCleaned++;
    }

    public boolean isGlassOfWaterCleaned() {
        return waterCleaned >= 25;
    }

    public void drawGlassOfWater() {
        waterCleaned -= 25;
    }

    @Override
    public String tipus() {
        return WATER_CLEANER_TYPE;
    }

}
