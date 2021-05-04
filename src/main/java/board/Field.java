package board;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
public class Field {

    private final List<Elem> elems = new ArrayList<>();

    private boolean visited = false;

    private Field parent;

    private Position position;

    public Field(Position position) {
        this.position = position;
    }

    public Field(int row, int column) {
        this.position = new Position(row, column);
    }

    public Field(Elem elem, Position position) {
        this.elems.add(elem);
        this.position = position;
    }

    public Field(Elem elem, int row, int column) {
        this.elems.add(elem);
        this.position = new Position(row, column);
    }

    public void lehelyez(Elem elem) {
        elems.add(elem);
    }

    public Elem levesz() {
        return elems.remove(getNumberOfElems() - 1);
    }

    public boolean ellenorizFelsoKetTipusat(String felsoTipus, String alattaTipus) {
        return getTopElem().tipus().equals(felsoTipus)
                && getSecondTopElem().tipus().equals(alattaTipus);
    }

    public boolean hasElemWithType(String type) {
        return elems
                .stream()
                .anyMatch(e -> e.tipus().equals(type));
    }

    public List<Elem> getElemsOfType(String type) {
        return elems
                .stream()
                .filter(e -> e.tipus().equals(type))
                .collect(toList());
    }

    public Elem getTopElem() {
        return elems.get(getNumberOfElems() - 1);
    }

    public boolean isVisibleElemOfType(String type) {
        return getTopElem().tipus().equals(type);
    }

    public Elem getSecondTopElem() {
        return elems.get(getNumberOfElems() - 2);
    }

    private int getNumberOfElems() {
        return elems.size();
    }

}
