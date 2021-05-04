package product;

import board.Elem;

public class Fish implements Elem {

    public static final String FISH_TYPE = "hal";

    private int baked = 0;

    void bake() {
        baked++;
    }

    boolean ready() {
        return baked >= 25;
    }

    @Override
    public String tipus() {
        return FISH_TYPE;
    }
}
