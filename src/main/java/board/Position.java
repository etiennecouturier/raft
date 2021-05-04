package board;

/**
 * eltarolja a poziciokat
 */
public class Position {

    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Position add(Position position) {
        return new Position(row + position.getRow(), column + position.getColumn());
    }

    int getColumn() {
        return column;
    }

    int getRow() {
        return row;
    }

}
