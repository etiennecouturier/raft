package command;

import board.Board;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static board.elems.resource.ResourceFactory.createResource;

public class GenerateResources extends Command {

    private static final Random random = new Random();

    private boolean valid = true;

    @Override
    protected boolean isApplicable(Board board) {
        if (valid) {
            valid = false;
            return true;
        }
        return false;
    }

    @Override
    protected void execute(Board board) {
        generalUjNyersanyagok(board);
    }

    private void generalUjNyersanyagok(Board board) {
        int nyersanyagSzam = random.nextInt(3 + 1);
        Set<Integer> hova = new HashSet<>();
        for (int i = 0; i < nyersanyagSzam; i++) {
            hova.add(random.nextInt(board.getFields()[0].length));
        }
        for (int oszlop : hova) {
            board.getFields()[0][oszlop].lehelyez(createResource());
        }
    }
}
