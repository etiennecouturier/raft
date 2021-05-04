package board.elems.resource;

import board.Elem;

import java.util.List;

import static board.elems.resource.Barrel.BARREL_TYPE;
import static board.elems.resource.Leaf.LEAF_TYPE;
import static board.elems.resource.Plank.PLANK_TYPE;
import static board.elems.resource.Trash.TRASH_TYPE;
import static java.util.Arrays.asList;

public abstract class Resource implements Elem {

    private static final List<String> NYERSANYAGOK = asList(PLANK_TYPE, BARREL_TYPE, TRASH_TYPE, LEAF_TYPE);

    protected Resource() {
    }

    public static boolean nyersanyagE(String elemTipus) {
        return NYERSANYAGOK.contains(elemTipus);
    }

}
