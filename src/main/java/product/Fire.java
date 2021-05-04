package product;

import board.Elem;

import java.util.ArrayList;
import java.util.List;

/**
 * eldonti a statikus metodusa  hogy termek e
 * es eltarolja a termekhez tartozo informaciokat
 * es metodusokat
 */
public class Fire implements Elem {

    public static final String TUZ_TIPUS = "tuz";
    private final List<Potato> burgonyak;
    private final List<Fish> halak;

    public Fire() {
        burgonyak = new ArrayList<>();
        halak = new ArrayList<>();
    }

    /**
     * rarakja a burgonyat
     *
     * @param b
     */
    public void rarak(Potato b) {
        burgonyak.add(b);
    }

    /**
     * rarakja a halat
     *
     * @param h
     */
    public void rarak(Fish h) {
        halak.add(h);
    }

    /**
     * van e mar megsult hal
     *
     * @return
     */
    public boolean vanSultHal() {
        for (Fish h : halak) {
            if (h.ready()) {
                return true;
            }
        }
        return false;
    }

    /**
     * van e mar megsult burgonya
     *
     * @return
     */
    public boolean vanSultBurgonya() {
        for (Potato b : burgonyak) {
            if (b.ready()) {
                return true;
            }
        }
        return false;
    }

    /**
     * suti a burgonyat es a halat
     */
    public void sul() {
        for (Potato b : burgonyak) {
            b.bake();
        }
        for (Fish h : halak) {
            h.bake();
        }
    }

    /**
     * ha megsult a burgony leveszi
     */
    public void leveszBurgonya() {
        for (Potato b : burgonyak) {
            if (b.ready()) {
                burgonyak.remove(b);
                break;
            }
        }
    }

    /**
     * meg lehet enni a halat akkor leveszi
     */
    public void leveszHal() {
        for (Fish h : halak) {
            if (h.ready()) {
                halak.remove(h);
                break;
            }
        }
    }

    /**
     * feluldefinialja a tipust
     *
     * @return
     */
    @Override
    public String tipus() {
        return TUZ_TIPUS;
    }
}
