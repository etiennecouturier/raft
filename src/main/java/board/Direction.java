package board;

public enum Direction {
    DOWN,
    UP,
    LEFT,
    RIGHT,
    LEFT_DOWN,
    LEFT_UP,
    RIGHT_DOWN,
    RIGHT_UP,
    NOOP;

    public static Position getElmozdulasIranyhoz(Direction direction) {
        if (direction == UP) {
            return new Position(-1, 0);
        } else if (direction == DOWN) {
            return new Position(1, 0);
        } else if (direction == LEFT) {
            return new Position(0, -1);
        } else if (direction == RIGHT) {
            return new Position(0, 1);
        } else if (direction == LEFT_UP) {
            return new Position(-1, -1);
        } else if (direction == LEFT_DOWN) {
            return new Position(1, -1);
        } else if (direction == RIGHT_DOWN) {
            return new Position(1, 1);
        } else if (direction == RIGHT_UP) {
            return new Position(-1, 1);
        }
        return new Position(0, 0);
    }
}
