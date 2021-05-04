package command;

import board.Direction;

import static board.Direction.*;

public class CommandFactory {

    private CommandFactory() {
    }

    public static Command letrehozParancs(int parancsKod) {
        if (parancsKod >= 90) { // iranymentes parancsok
            return getIranyNelkuliParancs(parancsKod);
        }
        Direction direction = getIrany(parancsKod % 10);
        switch (parancsKod / 10) {
            case 0:
                return new Move(direction);
            case 1:
                return new PickUpResource(direction);
            case 2:
                return new PlaceNet(direction);
            case 3:
                return new PlaceWaterCleaner(direction);
            case 4:
                return new PlaceFire(direction);
            case 5:
                return new AddToRaft(direction);
            default:
                return new NoOp();
        }
    }

    private static Command getIranyNelkuliParancs(int parancsKod) {
        switch (parancsKod) {
            case 90:
                return new Drink();
            case 91:
                return new EatFish();
            case 92:
                return new EatPotato();
            case 93:
                return new Fish();
            case 94:
                return new BakeFish();
            case 95:
                return new BakeFries();
            default:
                return new NoOp();
        }
    }

    private static Direction getIrany(int parancsKod) {
        switch (parancsKod) {
            case 1:
                return LEFT_DOWN;
            case 2:
                return DOWN;
            case 3:
                return RIGHT_DOWN;
            case 4:
                return LEFT;
            case 6:
                return RIGHT;
            case 7:
                return LEFT_UP;
            case 8:
                return UP;
            case 9:
                return RIGHT_UP;
            default:
                return NOOP;
        }
    }

}
