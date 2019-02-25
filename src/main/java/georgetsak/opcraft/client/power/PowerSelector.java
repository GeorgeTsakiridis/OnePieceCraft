package georgetsak.opcraft.client.power;

import georgetsak.opcraft.client.power.Power;
import georgetsak.opcraft.client.power.PowerHandler;
import georgetsak.opcraft.common.item.devilfruits.DevilFruitAssetsManager;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class PowerSelector {

    private static int fruitID = 0;
    private static int index = 1;
    private static int powersNum = 0;
    private static int oldFruitID = 0;

    public static void setFruitID(int id){
        fruitID = id;
        powersNum = PowerHandler.getTotalPowersForFruit(fruitID);
        if(oldFruitID != fruitID){
            index = 1;
            oldFruitID = fruitID;
        }
    }

    public static Power getSelectedPower(){
        return PowerHandler.getPower(fruitID, index);
    }

    public static void buttonPressed(boolean nextKey, boolean prevKey){
        if(nextKey){
            index = getNextIndex();
        }
        if(prevKey){
            index = getPrevIndex();
        }
    }

    public static int getNextIndex(){
        return (index + 1) > powersNum ? 1 : index + 1;
    }

    public static int getPrevIndex(){
        return (index - 1) < 1 ? powersNum : index - 1;
    }

    public static ArrayList<ResourceLocation> getIcons(){
        ArrayList<ResourceLocation> resourceLocations = new ArrayList<>();
        resourceLocations.add(PowerHandler.getPower(fruitID, getPrevIndex()).getResourceLocation());
        resourceLocations.add(PowerHandler.getPower(fruitID, index).getResourceLocation());
        resourceLocations.add(PowerHandler.getPower(fruitID, getNextIndex()).getResourceLocation());
        return resourceLocations;
    }

}
