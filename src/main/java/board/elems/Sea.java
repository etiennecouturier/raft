package board.elems;

import board.Elem;

public class Sea implements Elem {

    public static final String SEA_TYPE = "tenger";

    @Override
    public String tipus() {
        return SEA_TYPE;
    }
}
