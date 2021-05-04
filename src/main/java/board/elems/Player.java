package board.elems;

import board.Elem;
import board.elems.resource.Barrel;
import board.elems.resource.Resource;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

import static board.elems.resource.Barrel.BARREL_TYPE;
import static board.elems.resource.Leaf.LEAF_TYPE;
import static board.elems.resource.Plank.PLANK_TYPE;
import static board.elems.resource.Trash.TRASH_TYPE;
import static java.lang.Math.min;
import static product.Fish.FISH_TYPE;
import static product.Potato.POTATO_TYPE;

@Getter
@Setter
public class Player implements Elem {

    public static final String JATEKOS_TIPUS = "jatekos";

    private int hunger = 100;
    private int thirst = 100;
    private int timeTillRescued = 1000;
    private boolean isInWater = false;
    private boolean isEaten = false;

    private final Map<String, Integer> items;

    public Player() {
        items = new LinkedHashMap<>();
        items.put(PLANK_TYPE, 2);
        items.put(LEAF_TYPE, 2);
        items.put(TRASH_TYPE, 0);
        items.put(POTATO_TYPE, 0);
        items.put(FISH_TYPE, 0);
    }

    public boolean isDead() {
        return hunger <= 0 || thirst <= 0 || isEaten;
    }

    public boolean hasHelpArrived() {
        return timeTillRescued <= 0;
    }

    public int countItem(String itemName) {
        return items.getOrDefault(itemName, 0);
    }

    public void decrementItem(String itemName, int quantity) {
        items.put(itemName, items.get(itemName) - quantity);
    }

    public void pickup(Elem elem) {
        if (elem.tipus().equals(BARREL_TYPE)) {
            for (Resource resource : ((Barrel) elem).getContents()) {
                items.put(resource.tipus(), items.get(resource.tipus()) + 1);
            }
        } else {
            items.put(elem.tipus(), items.get(elem.tipus()) + 1);
        }
    }

    public void decrementHealth() {
        hunger--;
        thirst--;
    }

    public void decrementTimeTillRescued() {
        timeTillRescued--;
    }

    public void drink() {
        thirst = min(thirst + 40, 100 + 1); //+1 a mostani kor miatt
    }

    public void eat() {
        hunger = min(hunger + 60, 100 + 1);
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public String getFormattedTimeTillRescued() {
        return "korok szama: " + timeTillRescued;
    }

    public String getFormattedThirst() {
        return "szomjusag: " + thirst;
    }

    public String getFormattedHunger() {
        return "ehseg: " + hunger;
    }

    public String getFormattedItems() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Integer> e : items.entrySet()) {
            builder.append(e.getKey())
                    .append(": ")
                    .append(e.getValue())
                    .append("  ");
        }
        return builder.toString();
    }

    @Override
    public String tipus() {
        return JATEKOS_TIPUS;
    }
}
