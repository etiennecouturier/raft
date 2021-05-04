package board.elems;

import board.Elem;

public class Nyom implements Elem {

    public static final String NYOM_TIPUS = "nyom";

    @Override
    public String tipus() {
        return NYOM_TIPUS;
    }
}
