package georgetsak.opcraft.common.registry;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.registry.OPRender;
import georgetsak.opcraft.common.block.*;
import georgetsak.opcraft.common.block.tile.LawDomeTileEntity;
import georgetsak.opcraft.common.block.tile.SmokeCloudTileEntity;
import georgetsak.opcraft.common.block.tile.SnailTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.awt.*;
import java.util.ArrayList;

import static georgetsak.opcraft.common.network.proxy.CommonProxy.OPTab;

/**
 * Created by GeorgeTsak on 8/7/2017.
 */
public class OPBlocks {

    public static ArrayList<Block> BLOCKS_TO_RENDER;

    public static Block CHERRY_TREE_SAPLING;
    public static Block CHERRY_TREE_WOOD;
    public static Block CHERRY_TREE_PLANKS;
    public static Block CHERRY_TREE_LEAVES;
    public static Block CHERRY_TREE_LEAVES_NON_DECAYABLE;
    public static Block ADAM_TREE_WOOD;
    public static Block ADAM_TREE_PLANKS;
    public static Block ADAM_TREE_LEAVES;
    public static Block ADAM_TREE_LEAVES_NON_DECAYABLE;
    public static Block KAIROSEKI_STONE;
    public static Block STEEL_ORE;
    public static Block DARK_STEEL_ORE;
    public static Block TEMPORARY_ICE;
    public static Block STEEL_BLOCK;
    public static Block DARK_STEEL_BLOCK;
    public static Block LAW_DOME;
    public static Block LAW_DOME_CENTER;
    public static Block ICE_CAGE;
    public static Block ICE_AGE;
    public static Block SNAIL;
    public static Block SHIP_BUILDER;
    public static Block KAIROSEKI_BLOCK;
    public static Block KAIROSEKI_BARS;
    public static Block SMOKE_CLOUD;
    public static Block THIN_CLOUD;
    public static Block DENSE_CLOUD;
    public static Block WATER_CLOUD;
    public static Block MIRROR_WALL_BLOCK;
    public static Block MIRROR;

    public static void registerBlocks(){
        BLOCKS_TO_RENDER = new ArrayList<>();
        GameRegistry.registerTileEntity(LawDomeTileEntity.class, "law_dome");
        GameRegistry.registerTileEntity(SnailTileEntity.class, "snail");
        GameRegistry.registerTileEntity(SmokeCloudTileEntity.class, "smoke_cloud");

        CHERRY_TREE_SAPLING = new BlockCherryTreeSapling(Material.PLANTS).setRegistryName("cherry_tree_sapling").setCreativeTab(OPTab);
        CHERRY_TREE_WOOD = new BlockCherryTreeWood(Material.WOOD).setRegistryName("cherry_tree_wood").setCreativeTab(OPTab);
        CHERRY_TREE_PLANKS = new BlockCherryTreePlanks(Material.WOOD).setRegistryName("cherry_tree_planks").setCreativeTab(OPTab);
        CHERRY_TREE_LEAVES = new BlockCherryLeaves(Material.LEAVES, true).setRegistryName("cherry_tree_leaves");
        CHERRY_TREE_LEAVES_NON_DECAYABLE = new BlockCherryLeaves(Material.LEAVES, false).setRegistryName("cherry_tree_leaves_non_decayable").setCreativeTab(OPTab);
        ADAM_TREE_WOOD = new BlockAdamTreeWood(Material.IRON).setRegistryName("adam_tree_wood").setCreativeTab(OPTab);
        ADAM_TREE_PLANKS = new BlockAdamTreePlanks(Material.IRON).setRegistryName("adam_tree_planks").setCreativeTab(OPTab);
        ADAM_TREE_LEAVES = new BlockAdamTreeLeaves(Material.LEAVES, true).setRegistryName("adam_tree_leaves");
        ADAM_TREE_LEAVES_NON_DECAYABLE = new BlockAdamTreeLeaves(Material.LEAVES, false).setRegistryName("adam_tree_leaves_non_decayable").setCreativeTab(OPTab);
        KAIROSEKI_STONE = new BlockKairosekiStone(Material.ROCK).setRegistryName("kairoseki_stone").setCreativeTab(OPTab);
        STEEL_ORE = new BlockSteelOre(Material.ROCK).setRegistryName("steel_ore").setCreativeTab(OPTab);
        DARK_STEEL_ORE = new BlockDarkSteelOre(Material.ROCK).setRegistryName("dark_steel_ore").setCreativeTab(OPTab);
        STEEL_BLOCK = new BlockSteel(Material.IRON, 1).setRegistryName("steel_block").setCreativeTab(OPTab);
        DARK_STEEL_BLOCK = new BlockSteel(Material.IRON, 2).setRegistryName("dark_steel_block").setCreativeTab(OPTab);
        TEMPORARY_ICE = new BlockTemporaryIce(Material.ICE, 2.5F, 0.5F, true, 0).setRegistryName("temporary_ice");
        LAW_DOME = new BlockLawDome(Material.ROCK).setRegistryName("dome_block");
        LAW_DOME_CENTER = new BlockLawDomeCenter(Material.ROCK).setRegistryName("dome_center_block");
        ICE_CAGE = new BlockTemporaryIce(Material.ICE, 2F, 10F, false, 1).setRegistryName("ice_cage");
        ICE_AGE = new BlockTemporaryIce(Material.ICE, 10F, 10F, true, 6).setRegistryName("ice_age");
        SNAIL = new BlockSnail().setRegistryName("snail").setCreativeTab(OPTab);
        SHIP_BUILDER = new BlockShipBuilder().setRegistryName("ship_builder_block").setCreativeTab(OPTab);
        KAIROSEKI_BLOCK = new BlockKairosekiBlock().setRegistryName("kairoseki_block").setCreativeTab(OPTab);
        KAIROSEKI_BARS = new BlockKairosekiBars().setRegistryName("kairoseki_bars").setCreativeTab(OPTab);
        SMOKE_CLOUD = new BlockSmokeCloud(Material.ROCK).setRegistryName("smoke_cloud");
        THIN_CLOUD = new BlockCloud(true).setRegistryName("cloud_thin").setCreativeTab(OPTab);
        DENSE_CLOUD = new BlockCloud(false).setRegistryName("cloud_dense").setCreativeTab(OPTab);
        MIRROR_WALL_BLOCK = new MirrorWallBlock().setRegistryName("mirror_block").setBlockUnbreakable().setResistance(6000000.0F).setCreativeTab(OPTab);
        MIRROR = new MirrorBlock().setRegistryName("mirror").setCreativeTab(OPTab);

        registerBlock(CHERRY_TREE_SAPLING);
        registerBlock(CHERRY_TREE_WOOD);
        registerBlock(CHERRY_TREE_PLANKS);
        registerBlock(CHERRY_TREE_LEAVES, false);
        registerBlock(CHERRY_TREE_LEAVES_NON_DECAYABLE);
        registerBlock(ADAM_TREE_WOOD);
        registerBlock(ADAM_TREE_PLANKS);
        registerBlock(ADAM_TREE_LEAVES, false);
        registerBlock(ADAM_TREE_LEAVES_NON_DECAYABLE);
        registerBlock(KAIROSEKI_STONE);
        registerBlock(STEEL_ORE);
        registerBlock(DARK_STEEL_ORE);
        registerBlock(STEEL_BLOCK);
        registerBlock(DARK_STEEL_BLOCK);
        registerBlock(TEMPORARY_ICE);
        registerBlock(LAW_DOME);
        registerBlock(LAW_DOME_CENTER, false);
        registerBlock(ICE_CAGE);
        registerBlock(ICE_AGE);
        registerBlock(SNAIL, false);
        registerBlock(SHIP_BUILDER);
        registerBlock(KAIROSEKI_BLOCK);
        registerBlock(KAIROSEKI_BARS, false);
        registerBlock(SMOKE_CLOUD, false);
        registerBlock(THIN_CLOUD);
        registerBlock(DENSE_CLOUD);
        registerBlock(MIRROR_WALL_BLOCK);
        registerBlock(MIRROR, false);

        OreDictionary.registerOre("plankWood", OPBlocks.CHERRY_TREE_PLANKS);
        OreDictionary.registerOre("plankWood", OPBlocks.ADAM_TREE_PLANKS);

    }

    public static void registerFluids(){
        ResourceLocation stillTexture = new ResourceLocation(OPCraft.MODID, "blocks/cloud_water_still");
        ResourceLocation flowingTexture = new ResourceLocation(OPCraft.MODID, "blocks/cloud_water_flow");

        Fluid fluid = new FluidWaterCloud("cloud_water", stillTexture, flowingTexture, new Color(189, 239, 254, 140));
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
        WATER_CLOUD = new BlockWaterCloud(fluid).setRegistryName("cloud_water").setCreativeTab(OPTab);

        registerBlock(WATER_CLOUD, false);
    }

    static void registerBlock(Block block, boolean normalRender){
        ForgeRegistries.BLOCKS.register(block.setUnlocalizedName(block.getRegistryName().toString()));
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName().toString()));
        if(normalRender){
            BLOCKS_TO_RENDER.add(block);
        }
    }

    static void registerBlock(Block block) {
        registerBlock(block,true);
    }


}
