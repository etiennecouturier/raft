package board.elems.resource;

import product.Potato;

import java.util.Random;

public class ResourceFactory {

    private static final Random random = new Random();

    private ResourceFactory() {
    }

    public static Resource createResource() {
        int rand = random.nextInt(100);
        if (rand < 32) return new Plank();
        else if (rand < 64) return new Leaf();
        else if (rand < 96) return new Trash();
        else return new Barrel();
    }

    static Resource generateResourceForBarrel() {
        int rand = random.nextInt(4);
        if (rand == 0) return new Plank();
        else if (rand == 1) return new Leaf();
        else if (rand == 2) return new Trash();
        else return new Potato();
    }

}
