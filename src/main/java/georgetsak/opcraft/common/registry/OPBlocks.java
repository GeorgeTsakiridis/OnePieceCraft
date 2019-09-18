package georgetsak.opcraft.common.registry;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.block.*;
import georgetsak.opcraft.common.block.tile.LawDomeTileEntity;
import georgetsak.opcraft.common.block.tile.SmokeCloudTileEntity;
import georgetsak.opcraft.common.block.tile.SnailTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.awt.*;

import static georgetsak.opcraft.common.network.proxy.CommonProxy.*;

/**
 * Created by GeorgeTsak on 8/7/2017.
 */
public class OPBlocks {

    public static Block BlockCherryTreeSapling;
    public static Block BlockCherryTreeWood;
    public static Block BlockCherryTreePlanks;
    public static Block BlockCherryTreeLeaves;
    public static Block BlockCherryTreeLeavesNonDecayable;
    public static Block BlockAdamTreeWood;
    public static Block BlockAdamTreePlanks;
    public static Block BlockAdamTreeLeaves;
    public static Block BlockAdamTreeLeavesNonDecayable;
    public static Block BlockKairosekiStone;
    public static Block BlockSteelOre;
    public static Block BlockDarkSteelOre;
    public static Block BlockTemporaryIce;
    public static Block BlockSteelBlock;
    public static Block BlockDarkSteelBlock;
    public static Block BlockLawDome;
    public static Block BlockLawDomeCenter;
    public static Block BlockIceCage;
    public static Block BlockIceAge;
    public static Block BlockSnail;
    public static Block BlockShipBuilder;
    public static Block BlockKairosekiBlock;
    public static Block BlockKairosekiBars;
    public static Block BlockSmokeCloud;
    public static Block BlockThinCloud;
    public static Block BlockDenseCloud;
    public static Block BlockWaterCloud;

    public static void registerBlocks(){

        GameRegistry.registerTileEntity(LawDomeTileEntity.class, "law_dome");
        GameRegistry.registerTileEntity(SnailTileEntity.class, "snail");
        GameRegistry.registerTileEntity(SmokeCloudTileEntity.class, "smoke_cloud");

        BlockCherryTreeSapling = new BlockCherryTreeSapling(Material.PLANTS).setRegistryName("cherry_tree_sapling").setCreativeTab(OPTab);
        BlockCherryTreeWood = new BlockCherryTreeWood(Material.WOOD).setRegistryName("cherry_tree_wood").setCreativeTab(OPTab);
        BlockCherryTreePlanks = new BlockCherryTreePlanks(Material.WOOD).setRegistryName("cherry_tree_planks").setCreativeTab(OPTab);
        BlockCherryTreeLeaves = new BlockCherryLeaves(Material.LEAVES, true).setRegistryName("cherry_tree_leaves");
        BlockCherryTreeLeavesNonDecayable = new BlockCherryLeaves(Material.LEAVES, false).setRegistryName("cherry_tree_leaves_non_decayable").setCreativeTab(OPTab);
        BlockAdamTreeWood = new BlockAdamTreeWood(Material.IRON).setRegistryName("adam_tree_wood").setCreativeTab(OPTab);
        BlockAdamTreePlanks = new BlockAdamTreePlanks(Material.IRON).setRegistryName("adam_tree_planks").setCreativeTab(OPTab);
        BlockAdamTreeLeaves = new BlockAdamTreeLeaves(Material.LEAVES, true).setRegistryName("adam_tree_leaves");
        BlockAdamTreeLeavesNonDecayable = new BlockAdamTreeLeaves(Material.LEAVES, false).setRegistryName("adam_tree_leaves_non_decayable").setCreativeTab(OPTab);
        BlockKairosekiStone = new BlockKairosekiStone(Material.ROCK).setRegistryName("kairoseki_stone").setCreativeTab(OPTab);
        BlockSteelOre = new BlockSteelOre(Material.ROCK).setRegistryName("steel_ore").setCreativeTab(OPTab);
        BlockDarkSteelOre = new BlockDarkSteelOre(Material.ROCK).setRegistryName("dark_steel_ore").setCreativeTab(OPTab);
        BlockSteelBlock = new BlockSteel(Material.IRON, 1).setRegistryName("steel_block").setCreativeTab(OPTab);
        BlockDarkSteelBlock = new BlockSteel(Material.IRON, 2).setRegistryName("dark_steel_block").setCreativeTab(OPTab);
        BlockTemporaryIce = new BlockTemporaryIce(Material.ICE, 2.5F, 0.5F, true, 0).setRegistryName("temporary_ice");
        BlockLawDome = new BlockLawDome(Material.ROCK).setRegistryName("dome_block");
        BlockLawDomeCenter = new BlockLawDomeCenter(Material.ROCK).setRegistryName("dome_center_block");
        BlockIceCage = new BlockTemporaryIce(Material.ICE, 2F, 10F, false, 1).setRegistryName("ice_cage");
        BlockIceAge = new BlockTemporaryIce(Material.ICE, 10F, 10F, true, 6).setRegistryName("ice_age");
        BlockSnail = new BlockSnail().setRegistryName("snail").setCreativeTab(OPTab);
        BlockShipBuilder = new BlockShipBuilder().setRegistryName("ship_builder_block").setCreativeTab(OPTab);
        BlockKairosekiBlock = new BlockKairosekiBlock().setRegistryName("kairoseki_block").setCreativeTab(OPTab);
        BlockKairosekiBars = new BlockKairosekiBars().setRegistryName("kairoseki_bars").setCreativeTab(OPTab);
        BlockSmokeCloud = new BlockSmokeCloud(Material.ROCK).setRegistryName("smoke_cloud");
        BlockThinCloud = new BlockCloud(true).setRegistryName("cloud_thin").setCreativeTab(OPTab);
        BlockDenseCloud = new BlockCloud(false).setRegistryName("cloud_dense").setCreativeTab(OPTab);

        registerBlock(BlockCherryTreeSapling);
        registerBlock(BlockCherryTreeWood);
        registerBlock(BlockCherryTreePlanks);
        registerBlock(BlockCherryTreeLeaves);
        registerBlock(BlockCherryTreeLeavesNonDecayable);
        registerBlock(BlockAdamTreeWood);
        registerBlock(BlockAdamTreePlanks);
        registerBlock(BlockAdamTreeLeaves);
        registerBlock(BlockAdamTreeLeavesNonDecayable);
        registerBlock(BlockKairosekiStone);
        registerBlock(BlockSteelOre);
        registerBlock(BlockDarkSteelOre);
        registerBlock(BlockSteelBlock);
        registerBlock(BlockDarkSteelBlock);
        registerBlock(BlockTemporaryIce);
        registerBlock(BlockLawDome);
        registerBlock(BlockLawDomeCenter);
        registerBlock(BlockIceCage);
        registerBlock(BlockIceAge);
        registerBlock(BlockSnail);
        registerBlock(BlockShipBuilder);
        registerBlock(BlockKairosekiBlock);
        registerBlock(BlockKairosekiBars);
        registerBlock(BlockSmokeCloud);
        registerBlock(BlockThinCloud);
        registerBlock(BlockDenseCloud);
    }

    public static void registerFluids(){
        ResourceLocation stillTexture = new ResourceLocation(OPCraft.MODID, "blocks/cloud_water_still");
        ResourceLocation flowingTexture = new ResourceLocation(OPCraft.MODID, "blocks/cloud_water_flow");

        Fluid fluid = new FluidWaterCloud("cloud_water", stillTexture, flowingTexture, new Color(189, 239, 254, 140));
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
       BlockWaterCloud = new BlockWaterCloud(fluid).setRegistryName("cloud_water").setCreativeTab(OPTab);

        registerBlock(BlockWaterCloud);
    }

    static void registerBlock(Block block) {
        ForgeRegistries.BLOCKS.register(block.setUnlocalizedName(block.getRegistryName().toString()));
    }


}
