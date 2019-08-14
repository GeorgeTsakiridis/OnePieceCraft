package georgetsak.opcraft.client.power;

import georgetsak.opcraft.OPCraft;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class PowerHandler {
    private static ArrayList<Power> powers = new ArrayList<>();

    public static void addPower(int[] cooldownTimes, int[] usesToReduceCooldown, int[] hitsToUpgradePower, String actionMessage, String actionName, int devilFruitID, int key){
        powers.add(new Power(cooldownTimes, usesToReduceCooldown, hitsToUpgradePower, actionMessage, actionName, devilFruitID, key, new ResourceLocation(OPCraft.MODID, "null.png")));
    }

    public static void addPower(int[] cooldownTimes, int[] usesToReduceCooldown, int[] hitsToUpgradePower, String actionMessage, String actionName, int devilFruitID, int key, String resourceName){
        ResourceLocation resourceLocation = new ResourceLocation(OPCraft.MODID, "textures/gui/powers_icons/" + resourceName + ".png");
        powers.add(new Power(cooldownTimes, usesToReduceCooldown, hitsToUpgradePower, actionMessage, actionName, devilFruitID, key, resourceLocation));
    }

    public static Power getPower(int devilFruitID, int key){
        for(Power power : powers){
            if(power.getDevilFruitID() == devilFruitID && power.getKey() == key){
                return power;
            }
        }
        return null;
    }

    public static int getTotalPowersForFruit(int fruitID){
        int i = 0;
        for(Power power : powers){
            if(power.getDevilFruitID() == fruitID){
                i++;
            }
        }
        return i;
    }

}
