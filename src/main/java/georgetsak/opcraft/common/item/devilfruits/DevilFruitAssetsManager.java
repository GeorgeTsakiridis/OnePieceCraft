package georgetsak.opcraft.common.item.devilfruits;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.power.Power;
import georgetsak.opcraft.client.power.PowerHandler;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class DevilFruitAssetsManager {

    private static ArrayList<DevilFruitAsset> devilFruitAssets = new ArrayList<>();

    public static void addDevilFruitAsset(int id, String resName, String tooltip, String fullName){
        ResourceLocation resourceLocation = new ResourceLocation(OPCraft.MODID, "textures/items/devil_fruits/devil_fruit_" + resName + ".png");
        devilFruitAssets.add(new DevilFruitAsset(id, resourceLocation, tooltip, fullName));
    }

    public static DevilFruitAsset getDevilFruitAsset(int id){
        for(DevilFruitAsset devilFruitAsset : devilFruitAssets){
            if(devilFruitAsset.getId() == id){
                return devilFruitAsset;
            }
        }
        return devilFruitAssets.get(1);//null
    }

}
