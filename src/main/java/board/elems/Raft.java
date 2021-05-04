package board.elems;

import board.Elem;

public class Raft implements Elem {

    public static final String RAFT_TYPE = "fold";

    @Override
    public String tipus() {
        return RAFT_TYPE;
    }

}
