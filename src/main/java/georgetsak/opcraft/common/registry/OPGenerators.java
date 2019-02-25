package georgetsak.opcraft.common.registry;

import georgetsak.opcraft.common.generator.OPIWorldGenerator;
import georgetsak.opcraft.common.generator.structures.village.components.*;
import georgetsak.opcraft.common.generator.structures.village.handlers.*;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

/**
 * Created by GeorgeTsak on 8/7/2017.
 */
public class OPGenerators {
    public static void registerGenerators(){
        GameRegistry.registerWorldGenerator(new OPIWorldGenerator(), 10);

        VillagerRegistry.instance().registerVillageCreationHandler(new BigHouse1Handler());
        VillagerRegistry.instance().registerVillageCreationHandler(new SmallHouse1Handler());
        VillagerRegistry.instance().registerVillageCreationHandler(new BanditHouse1Handler());
        VillagerRegistry.instance().registerVillageCreationHandler(new Market1Handler());
        VillagerRegistry.instance().registerVillageCreationHandler(new Bar1Handler());
        VillagerRegistry.instance().registerVillageCreationHandler(new AbandonedHouse1Handler());

        MapGenStructureIO.registerStructureComponent(BigHouse1Component.class, "OPBiH1");//One Piece Big House
        MapGenStructureIO.registerStructureComponent(SmallHouse1Component.class, "OPSH1"); //One Piece Small House
        MapGenStructureIO.registerStructureComponent(BanditHouse1Component.class, "OPBaH1"); //One Piece Bandit House
        MapGenStructureIO.registerStructureComponent(Market1Component.class, "OPMa1"); //One Piece Market
        MapGenStructureIO.registerStructureComponent(Bar1Component.class, "OPBa1"); //One Piece Bar
        MapGenStructureIO.registerStructureComponent(AbandonedHouse1Component.class, "OPAbH1"); //One Piece Abandoned House

    }
}
