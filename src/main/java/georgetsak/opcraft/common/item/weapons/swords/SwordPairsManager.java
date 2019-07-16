package georgetsak.opcraft.common.item.weapons.swords;

import net.minecraft.item.Item;

import java.util.ArrayList;

public class SwordPairsManager {

     private static ArrayList<SwordPair> pairs = new ArrayList<>();

    public static Item getPair(Item item){
        for(SwordPair pair : pairs){
            if(pair.getItem1() == item){
                return pair.getItem2();
            }
            else if(pair.getItem2() == item){
                return pair.getItem1();
            }
        }
        return null;
    }

    public static void addPair(Item item1, Item item2){
        pairs.add(new SwordPair(item1, item2));
    }

}
