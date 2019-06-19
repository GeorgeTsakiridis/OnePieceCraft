package georgetsak.opcraft.common.registry;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.entity.boat.EntityAceBoat;
import georgetsak.opcraft.common.entity.boat.EntitySailBoat;
import georgetsak.opcraft.common.entity.devilfruit.*;
import georgetsak.opcraft.common.entity.marine.*;
import georgetsak.opcraft.common.entity.other.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.awt.*;

/**
 * Created by GeorgeTsak on 8/7/2017.
 */
public class OPEntities {

    static int id = 0;

    public static void registerEntities(){

        Biome[] allSpawnableBiomes = new Biome[]{Biomes.PLAINS, Biomes.BEACH, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.COLD_BEACH, Biomes.COLD_TAIGA, Biomes.COLD_TAIGA_HILLS, Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.EXTREME_HILLS, Biomes.EXTREME_HILLS_EDGE, Biomes.EXTREME_HILLS_WITH_TREES, Biomes.FOREST, Biomes.FOREST_HILLS, Biomes.ICE_MOUNTAINS, Biomes.ICE_PLAINS, Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.MESA, Biomes.MESA_CLEAR_ROCK, Biomes.MESA_ROCK, Biomes.MUTATED_BIRCH_FOREST, Biomes.MUTATED_BIRCH_FOREST_HILLS, Biomes.MUTATED_DESERT, Biomes.MUTATED_EXTREME_HILLS, Biomes.MUTATED_EXTREME_HILLS_WITH_TREES, Biomes.MUTATED_FOREST, Biomes.MUTATED_ICE_FLATS, Biomes.MUTATED_DESERT, Biomes.MUTATED_JUNGLE, Biomes.MUTATED_JUNGLE_EDGE, Biomes.MUTATED_MESA, Biomes.MUTATED_MESA_CLEAR_ROCK, Biomes.MUTATED_MESA_ROCK, Biomes.MUTATED_PLAINS, Biomes.MUTATED_REDWOOD_TAIGA, Biomes.MUTATED_REDWOOD_TAIGA_HILLS, Biomes.MUTATED_ROOFED_FOREST, Biomes.MUTATED_SAVANNA, Biomes.MUTATED_SAVANNA_ROCK, Biomes.MUTATED_SWAMPLAND, Biomes.MUTATED_TAIGA, Biomes.MUTATED_TAIGA_COLD, Biomes.REDWOOD_TAIGA, Biomes.REDWOOD_TAIGA_HILLS, Biomes.ROOFED_FOREST, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.STONE_BEACH, Biomes.SWAMPLAND, Biomes.TAIGA, Biomes.TAIGA_HILLS};
        Biome[] hotBiomes = new Biome[]{Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.MUTATED_DESERT, Biomes.MESA, Biomes.MESA_CLEAR_ROCK, Biomes.MESA_ROCK, Biomes.MUTATED_MESA, Biomes.MUTATED_MESA_CLEAR_ROCK, Biomes.MUTATED_MESA_ROCK, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU};

        registerEntity(EntityKabutoAmmo.class, "kabuto_ammo", id++, OPCraft.MODID, 300, 20, true);
        registerEntity(EntityGomuPistol.class, "gomu_pistol", id++, OPCraft.MODID, 48, 3, true);
        registerEntity(EntityFirePunch.class, "fire_fist", id++, OPCraft.MODID, 48, 3, true);
        registerEntity(EntityEntei.class, "entei", id++, OPCraft.MODID, 100, 3, true);
        registerEntity(EntitySlowBeam.class, "slow_beam", id++, OPCraft.MODID, 48, 3, true);
        registerEntity(EntitySlowBeamSpawner.class, "slow_beam_spawner", id++, OPCraft.MODID, 48, 3, false);
        registerEntity(EntityOpeDome.class, "dome", id++, OPCraft.MODID, 64, 3, false);
        registerEntity(EntityIceSaber.class, "ice_saber", id++, OPCraft.MODID, 100, 3, true);
        registerEntity(EntityIcePhoenix.class, "ice_phoenix", id++, OPCraft.MODID, 100, 3, true);
        registerEntity(EntityUrsusBubble.class, "ursus_bubble", id++, OPCraft.MODID, 48, 3, false);
        registerEntity(EntitySmokePunch.class, "smoke_punch", id++, OPCraft.MODID, 48, 3, true);
        registerEntity(EntitySmokeSnake.class, "smoke_snake", id++, OPCraft.MODID, 48, 3, true);
        registerEntity(EntityDark.class, "black_hole", id++, OPCraft.MODID, 48, 3, true);
        registerEntity(EntityMarine.class, "marine", id++, OPCraft.MODID, 48, 3, true, toRGB(Color.WHITE), toRGB(Color.blue));
        registerEntity(EntityHardMarine.class, "hard_marine", id++, OPCraft.MODID, 48, 3, true, toRGB(Color.WHITE), toRGB(Color.BLUE.darker()));
        registerEntity(EntityWildMarine.class, "wild_marine", id++, OPCraft.MODID, 48, 3, true);
        registerEntity(EntityWildHardMarine.class, "wild_hard_marine", id++, OPCraft.MODID, 48, 3, true);
        registerEntity(EntityMorgan.class, "morgan", id++, OPCraft.MODID, 48, 3, true, toRGB(Color.BLUE), toRGB(Color.CYAN.darker()));
        registerEntity(EntityBandit.class, "bandit", id++, OPCraft.MODID, 48, 3, true, Color.BLACK.getRGB(), Color.WHITE.getRGB());
        registerEntity(EntityOPVillager.class, "one_piece_villager", id++, OPCraft.MODID, 48, 3, true, toRGB(104, 29, 0), toRGB(160, 42, 0));
        registerEntity(EntityAceBoat.class, "ace_boat", id++, OPCraft.MODID, 48, 20, true);
        registerEntity(EntitySailBoat.class, "sail_boat", id++, OPCraft.MODID, 48, 20, true);
        registerEntity(EntityPirate.class, "pirate", id++, OPCraft.MODID, 48, 3, true, toRGB(Color.BLUE), toRGB(Color.BLUE));
        //registerEntity2(EntitySeaKing.class, "sea_king", id++, OPCraft.MODID, 48, 3, true);
        registerEntity(EntityCrocodile.class, "crocodile", id++, OPCraft.MODID, 48, 3, true, toRGB(197, 164, 131), Color.BLACK.getRGB());
        registerEntity(EntityTonta.class, "tonta", id++, OPCraft.MODID, 48, 3, true, toRGB(178, 150, 120), toRGB(107, 224, 96));
        registerEntity(EntityLiberation.class, "liberation", id++, OPCraft.MODID, 48, 3, true);
        registerEntity(EntityRayleigh.class, "rayleigh", id++, OPCraft.MODID, 48, 3, true, toRGB(244, 167, 66), toRGB(239, 239, 239));
        registerEntity(EntityStormLeg.class, "storm_leg", id++, OPCraft.MODID, 48, 3, false);

        EnumCreatureType crocodile = EnumHelper.addCreatureType("crocodile", EntityCrocodile.class, 1, Material.AIR, false, false);
        EnumCreatureType rayleigh = EnumHelper.addCreatureType("rayleigh", EntityRayleigh.class, 1, Material.AIR, true, false);

        EntityRegistry.addSpawn(EntityWildMarine.class, 20, 1, 5, EnumCreatureType.MONSTER, allSpawnableBiomes);
        EntityRegistry.addSpawn(EntityWildHardMarine.class, 10, 1, 1, EnumCreatureType.MONSTER, allSpawnableBiomes);
        EntityRegistry.addSpawn(EntityPirate.class, 5, 1, 6, EnumCreatureType.MONSTER, allSpawnableBiomes);
        EntityRegistry.addSpawn(EntityCrocodile.class, 1, 0, 1, crocodile, hotBiomes);
        EntityRegistry.addSpawn(EntityRayleigh.class, 1, 0, 1, rayleigh, allSpawnableBiomes);
        EntityRegistry.addSpawn(EntityTonta.class, 1, 1, 6, EnumCreatureType.CREATURE, allSpawnableBiomes);


    }

    private static void registerEntity(Class entityClass, String registryName, int id, String modID, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates){
        EntityRegistry.registerModEntity(new ResourceLocation(modID, registryName), entityClass, registryName, id, modID, trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    private static void registerEntity(Class entityClass, String registryName, int id, String modID, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int primaryColor, int secondaryColor){
        EntityRegistry.registerModEntity(new ResourceLocation(modID, registryName), entityClass, registryName, id, modID, trackingRange, updateFrequency, sendsVelocityUpdates, primaryColor, secondaryColor);
    }

    private static int toRGB(int r, int g, int b){
        return new Color(r, g, b).getRGB();
    }

    private static int toRGB(Color color){
        return color.getRGB();
    }

}
