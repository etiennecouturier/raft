package product;

import board.elems.resource.Resource;

public class Potato extends Resource {

    public static final String POTATO_TYPE = "burgonya";

    private int baked = 0;

    void bake() {
        baked++;
    }

    boolean ready() {
        return baked >= 25;
    }

    @Override
    public String tipus() {
        return POTATO_TYPE;
    }
}
