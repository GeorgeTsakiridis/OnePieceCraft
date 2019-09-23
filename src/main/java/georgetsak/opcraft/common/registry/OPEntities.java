package georgetsak.opcraft.common.registry;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.entity.boat.EntityAceBoat;
import georgetsak.opcraft.common.entity.boat.EntitySailBoat;
import georgetsak.opcraft.common.entity.devilfruit.*;
import georgetsak.opcraft.common.entity.marine.*;
import georgetsak.opcraft.common.entity.other.*;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.awt.*;

/**
 * Created by GeorgeTsak on 8/7/2017.
 */
public class OPEntities {

    private static int id = 0;

    public static void registerEntities(){

        Biome[] allSpawnableBiomes = new Biome[]{Biomes.PLAINS, Biomes.BEACH, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.COLD_BEACH, Biomes.COLD_TAIGA, Biomes.COLD_TAIGA_HILLS, Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.EXTREME_HILLS, Biomes.EXTREME_HILLS_EDGE, Biomes.EXTREME_HILLS_WITH_TREES, Biomes.FOREST, Biomes.FOREST_HILLS, Biomes.ICE_MOUNTAINS, Biomes.ICE_PLAINS, Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.MESA, Biomes.MESA_CLEAR_ROCK, Biomes.MESA_ROCK, Biomes.MUTATED_BIRCH_FOREST, Biomes.MUTATED_BIRCH_FOREST_HILLS, Biomes.MUTATED_DESERT, Biomes.MUTATED_EXTREME_HILLS, Biomes.MUTATED_EXTREME_HILLS_WITH_TREES, Biomes.MUTATED_FOREST, Biomes.MUTATED_ICE_FLATS, Biomes.MUTATED_DESERT, Biomes.MUTATED_JUNGLE, Biomes.MUTATED_JUNGLE_EDGE, Biomes.MUTATED_MESA, Biomes.MUTATED_MESA_CLEAR_ROCK, Biomes.MUTATED_MESA_ROCK, Biomes.MUTATED_PLAINS, Biomes.MUTATED_REDWOOD_TAIGA, Biomes.MUTATED_REDWOOD_TAIGA_HILLS, Biomes.MUTATED_ROOFED_FOREST, Biomes.MUTATED_SAVANNA, Biomes.MUTATED_SAVANNA_ROCK, Biomes.MUTATED_SWAMPLAND, Biomes.MUTATED_TAIGA, Biomes.MUTATED_TAIGA_COLD, Biomes.REDWOOD_TAIGA, Biomes.REDWOOD_TAIGA_HILLS, Biomes.ROOFED_FOREST, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.STONE_BEACH, Biomes.SWAMPLAND, Biomes.TAIGA, Biomes.TAIGA_HILLS};
        Biome[] hotBiomes = new Biome[]{Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.MUTATED_DESERT, Biomes.MESA, Biomes.MESA_CLEAR_ROCK, Biomes.MESA_ROCK, Biomes.MUTATED_MESA, Biomes.MUTATED_MESA_CLEAR_ROCK, Biomes.MUTATED_MESA_ROCK, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU};

        registerEntity(EntityKabutoAmmo.class, "kabuto_ammo", 300, 20, true);
        registerEntity(EntityGomuPistol.class, "gomu_pistol", 48, 3, true);
        registerEntity(EntityFirePunch.class, "fire_fist", 48, 3, true);
        registerEntity(EntityEntei.class, "entei", 100, 3, true);
        registerEntity(EntitySlowBeam.class, "slow_beam", 48, 3, true);
        registerEntity(EntitySlowBeamSpawner.class, "slow_beam_spawner", 48, 3, false);
        registerEntity(EntityIceSaber.class, "ice_saber", 100, 3, true);
        registerEntity(EntityIcePhoenix.class, "ice_phoenix", 100, 3, true);
        registerEntity(EntityUrsusBubble.class, "ursus_bubble", 48, 3, false);
        registerEntity(EntitySmokePunch.class, "smoke_punch", 48, 3, true);
        registerEntity(EntitySmokeSnake.class, "smoke_snake", 48, 3, true);
        registerEntity(EntityDark.class, "black_hole", 48, 3, true);
        registerEntity(EntityMarine.class, "marine", 48, 3, true, toRGB(Color.WHITE), toRGB(Color.blue));
        registerEntity(EntityHardMarine.class, "hard_marine", 48, 3, true, toRGB(Color.WHITE), toRGB(Color.BLUE.darker()));
        registerEntity(EntityWildMarine.class, "wild_marine", 48, 3, true);
        registerEntity(EntityWildHardMarine.class, "wild_hard_marine", 48, 3, true);
        registerEntity(EntityMorgan.class, "morgan", 64, 3, true, toRGB(Color.BLUE), toRGB(Color.CYAN.darker()));
        registerEntity(EntityBandit.class, "bandit", 48, 3, true, Color.BLACK.getRGB(), Color.WHITE.getRGB());
        registerEntity(EntityOPVillager.class, "one_piece_villager", 48, 3, true, toRGB(104, 29, 0), toRGB(160, 42, 0));
        registerEntity(EntityAceBoat.class, "ace_boat", 48, 20, true);
        registerEntity(EntitySailBoat.class, "sail_boat", 48, 20, true);
        registerEntity(EntityPirate.class, "pirate", 48, 3, true, toRGB(Color.BLUE), toRGB(Color.BLUE));
        registerEntity(EntityCrocodile.class, "crocodile", 48, 3, true, toRGB(197, 164, 131), Color.BLACK.getRGB());
        registerEntity(EntityTonta.class, "tonta", 48, 3, true, toRGB(178, 150, 120), toRGB(107, 224, 96));
        registerEntity(EntityLiberation.class, "liberation", 48, 3, true);
        registerEntity(EntityRayleigh.class, "rayleigh", 48, 3, true, toRGB(244, 167, 66), toRGB(239, 239, 239));
        registerEntity(EntityStormLeg.class, "storm_leg", 48, 3, false);
        registerEntity(EntityTamaito.class,"tamaito", 200, 3, true);
        registerEntity(EntityLongLine.class,"long_line", 200, 3, true);
        registerEntity(EntityOverheat.class,"overheat",200, 3, true);
        registerEntity(EntityGoshikito.class,"goshikito",48, 3, true);
        registerEntity(EntitySkypiean.class,"skypiean",64,3,true, toRGB(Color.WHITE), toRGB(157,100,87));
        registerEntity(EntityKuro.class,"kuro",64,3,true, toRGB(Color.BLACK), toRGB(65,65,65));
        registerEntity(EntityPeacekeeper.class,"peacekeeper",48,3,true, toRGB(Color.WHITE), toRGB(Color.BLACK));
        registerEntity(EntityHomieTree.class,"homie_tree",48,3,true, toRGB(104, 84, 51), toRGB(37, 137, 0));
        registerEntity(EntityTequilaBridgeGuard.class,"tequila_bridge_guard", 64, 3, true, toRGB(148, 53, 54), toRGB(225, 225, 225));
        registerEntity(EntitySlave.class, "slave", 48, 3, true, toRGB(132, 168, 179), toRGB(225, 225, 225));

        //registerEntity2(EntitySeaKing.class, "sea_king", id++, OPCraft.MODID, 48, 3, true);

        EntityRegistry.addSpawn(EntityWildMarine.class, 20, 1, 5, EnumCreatureType.MONSTER, allSpawnableBiomes);
        EntityRegistry.addSpawn(EntityWildHardMarine.class, 10, 1, 1, EnumCreatureType.MONSTER, allSpawnableBiomes);
        EntityRegistry.addSpawn(EntityPirate.class, 5, 1, 6, EnumCreatureType.MONSTER, allSpawnableBiomes);
        EntityRegistry.addSpawn(EntityCrocodile.class, 1, 1, 1, EnumCreatureType.MONSTER, hotBiomes);
        EntityRegistry.addSpawn(EntityRayleigh.class, 1, 1, 1, EnumCreatureType.CREATURE, allSpawnableBiomes);
        EntityRegistry.addSpawn(EntityTonta.class, 1, 1, 6, EnumCreatureType.CREATURE, allSpawnableBiomes);

    }

    private static void registerEntity(Class entityClass, String registryName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates){
        EntityRegistry.registerModEntity(new ResourceLocation(OPCraft.MODID, registryName), entityClass, registryName, id++, OPCraft.MODID, trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    private static void registerEntity(Class entityClass, String registryName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int primaryColor, int secondaryColor){
        EntityRegistry.registerModEntity(new ResourceLocation(OPCraft.MODID, registryName), entityClass, registryName, id++, OPCraft.MODID, trackingRange, updateFrequency, sendsVelocityUpdates, primaryColor, secondaryColor);
    }

    private static int toRGB(int r, int g, int b){
        return new Color(r, g, b).getRGB();
    }

    private static int toRGB(Color color){
        return color.getRGB();
    }

}
