package georgetsak.opcraft.common.registry;

import georgetsak.opcraft.OPCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class OPLootTables {

    public static ResourceLocation OPVILLAGE_CHEST = new ResourceLocation(OPCraft.MODID, "village_chest");
    public static ResourceLocation ENTITY_MARINE = new ResourceLocation(OPCraft.MODID, "entity_marine");
    public static ResourceLocation ENTITY_MORGAN = new ResourceLocation(OPCraft.MODID, "entity_morgan");
    public static ResourceLocation ENTITY_CROCODILE = new ResourceLocation(OPCraft.MODID, "entity_crocodile");

    public static void registerLootTables(){
        LootTableList.register(OPVILLAGE_CHEST);
        LootTableList.register(ENTITY_MARINE);
        LootTableList.register(ENTITY_MORGAN);
        LootTableList.register(ENTITY_CROCODILE);
    }

}
