package runner;

import board.Board;
import ui.Tabla;

public class Main {

    public static void main(String[] args) {
        new Tabla(new Board(25, 35));
    }

}
