package georgetsak.opcraft.common.item.weapons.swords;

import net.minecraft.item.Item;

public class SwordPair {

    private Item item1;
    private Item item2;

    public SwordPair(Item item1, Item item2){
        this.item1 = item1;
        this.item2 = item2;
    }

    public Item getItem1() {
        return item1;
    }

    public Item getItem2() {
        return item2;
    }
}
