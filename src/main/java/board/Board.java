package board;

import board.elems.Player;
import command.Command;
import lombok.Getter;

import static board.Direction.getElmozdulasIranyhoz;
import static io.FileHandler.loadGameFromClassPath;

@Getter
public class Board {

    private Player player;

    private Field[][] fields;
    private int currI;
    private int currJ;

    public Board(int height, int width) {
        this.fields = new Field[height][width];
        player = new Player();
        loadGameFromClassPath(this, "board/base.rft");
    }

    public void iterate(Command command) {
        for (int i = fields.length - 1; i >= 0; i--) {
            for (int j = 0; j < fields[i].length; j++) {
                currI = i;
                currJ = j;
                command.exec(this);
            }
        }
    }

    public Field currField() {
        return fields[currI][currJ];
    }

    public void clear() {
        this.fields = new Field[fields.length][fields[0].length];
    }

    public Field getNextField(Field field, Direction direction) {
        Position elmozdulas = getElmozdulasIranyhoz(direction);
        Position ujPosition = field.getPosition().add(elmozdulas);
        return isOnBoard(ujPosition) ? getField(ujPosition) : currField();
    }

    public Field getNextField(Direction direction) {
        return getNextField(currField(), direction);
    }

    public boolean isOnBoard(Direction direction) {
        Position elmozdulas = getElmozdulasIranyhoz(direction);
        Position ujPosition = new Position(currI + elmozdulas.getRow(), currJ + elmozdulas.getColumn());
        return isOnBoard(ujPosition);
    }

    public boolean isOnBoard(Position position) {
        return (position.getColumn() >= 0 && position.getColumn() < fields[0].length) &&
                position.getRow() >= 0 && position.getRow() < fields.length;
    }

    public Field getField(Position position) {
        return fields[position.getRow()][position.getColumn()];
    }

}
