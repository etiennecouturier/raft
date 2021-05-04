package product;

import board.Elem;

public class Net implements Elem {

    public static final String NET_TYPE = "halo";

    @Override
    public String tipus() {
        return NET_TYPE;
    }
}
