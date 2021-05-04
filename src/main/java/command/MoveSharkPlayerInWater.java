package command;

import board.Board;
import board.Direction;
import board.Elem;
import board.Field;
import board.elems.Nyom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static board.elems.Player.JATEKOS_TIPUS;
import static board.elems.Sea.SEA_TYPE;
import static board.elems.Shark.SHARK_TYPE;

public class MoveSharkPlayerInWater extends Command {

    private boolean valid = true;

    @Override
    protected boolean isApplicable(Board board) {
        return mezoTipusu(board, SHARK_TYPE)
                && board.getPlayer().isInWater()
                && valid;
    }

    @Override
    protected void execute(Board p) {
        List<Field> shortestPath = getShortestPath(p);
        Field nextField;
        if (shortestPath.size() < 3) {
            p.getPlayer().setEaten(true);
            nextField = shortestPath.get(0);
        } else {
            shortestPath.subList(1, shortestPath.size() - 2).forEach(e -> e.lehelyez(new Nyom()));
            nextField = shortestPath.get(shortestPath.size() - 2);
        }

        Elem elem = p.currField().levesz();
        nextField.lehelyez(elem);
        valid = false;
    }

    private List<Field> getShortestPath(Board board) {
        Queue<Field> fieldQueue = new LinkedList<>();
        board.currField().setVisited(true);
        fieldQueue.add(board.currField());
        while (!fieldQueue.isEmpty()) {
            Field currentField = fieldQueue.poll();
            if (currentField.isVisibleElemOfType(JATEKOS_TIPUS)) {
                return trackbackPathToStart(currentField);
            }
            fieldQueue.addAll(getChildren(board, currentField));
        }
        return new ArrayList<>();
    }

    private List<Field> getChildren(Board board, Field parent) {
        List<Field> gyerekMezok = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            Field kovField = board.getNextField(parent, direction);
            if (!kovField.isVisited()
                    && (kovField.isVisibleElemOfType(SEA_TYPE)
                    || kovField.isVisibleElemOfType(JATEKOS_TIPUS))) {
                kovField.setParent(parent);
                kovField.setVisited(true);
                gyerekMezok.add(kovField);
            }
        }
        return gyerekMezok;
    }

    private List<Field> trackbackPathToStart(Field targetField) {
        List<Field> lanc = new ArrayList<>();
        Field current = targetField;
        while (current != null) {
            lanc.add(current);
            current = current.getParent();
        }
        return lanc;
    }

}
