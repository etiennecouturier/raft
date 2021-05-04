package board.elems.resource;

import lombok.Getter;

import static board.elems.resource.ResourceFactory.generateResourceForBarrel;

@Getter
public class Barrel extends Resource {

    public static final String BARREL_TYPE = "hordo";

    private final Resource[] contents = new Resource[5];

    public Barrel() {
        for (int i = 0; i < contents.length; i++) {
            contents[i] = generateResourceForBarrel();
        }
    }

    @Override
    public String tipus() {
        return BARREL_TYPE;
    }

}
