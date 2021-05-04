package command;

import board.Board;
import board.Direction;
import board.Field;
import board.elems.Player;

import java.util.Map;

import static board.elems.Player.JATEKOS_TIPUS;
import static board.elems.Raft.RAFT_TYPE;
import static java.util.Map.ofEntries;

public abstract class Command {

    public void exec(Board board) {
        if (isApplicable(board) && canBuy(board.getPlayer())) {
            deduceAmount(board.getPlayer());
            execute(board);
        }
    }

    protected abstract boolean isApplicable(Board board);

    protected abstract void execute(Board board);

    protected Map<String, Integer> price() {
        return ofEntries();
    }

    private boolean canBuy(Player player) {
        return price()
                .entrySet()
                .stream()
                .allMatch(e -> player
                        .getItems()
                        .get(e.getKey()) - e.getValue() >= 0);
    }

    private void deduceAmount(Player player) {
        price()
                .forEach((key, value) -> player
                        .getItems()
                        .put(key, player.getItems().get(key) - value));
    }

    boolean jatekosEsMellettVmi(Board board, Direction direction, String keresettTipus) {
        Field field = board.currField();
        return board.getNextField(direction)
                .getTopElem()
                .tipus().equals(keresettTipus) && field.getTopElem().tipus().equals(JATEKOS_TIPUS);
    }

    boolean jatekosFoldonEsMellettVmi(Board board, Direction direction, String keresettTipus) {
        Field field = board.currField();
        return board.getNextField(direction)
                .getTopElem()
                .tipus().equals(keresettTipus)
                && field.getTopElem().tipus().equals(JATEKOS_TIPUS)
                && field.getSecondTopElem().tipus().equals(RAFT_TYPE);
    }

    boolean mezoTipusu(Board board, String keresettTipus) {
        Field field = board.currField();
        return field.getTopElem().tipus().equals(keresettTipus);
    }

}
