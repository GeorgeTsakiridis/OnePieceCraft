package georgetsak.opcraft.common.generator.structures.village.components;

import georgetsak.opcraft.common.network.proxy.CommonProxy;
import georgetsak.opcraft.common.registry.OPLootTables;
import georgetsak.opcraft.common.util.WorldGenUtils;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import java.util.List;
import java.util.Random;

public class SmallHouse1Component extends StructureVillagePieces.House2{

    private static final int X_SIZE = 11;
    private static final int Y_SIZE = 9;
    private static final int Z_SIZE = 8;

    private int averageGroundLevel = -1;

    public SmallHouse1Component(){

    }

    public SmallHouse1Component(StructureBoundingBox boundingBox, EnumFacing par5){
        this.setCoordBaseMode(par5);
        this.boundingBox = boundingBox;
    }

    public static SmallHouse1Component buildComponent(List pieces, int p1, int p2, int p3, EnumFacing p4){
        StructureBoundingBox boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, X_SIZE, Y_SIZE, Z_SIZE, p4);
        return canVillageGoDeeper(boundingBox) && StructureComponent.findIntersecting(pieces, boundingBox) == null ? new SmallHouse1Component(boundingBox, p4) : null;
    }

    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox sbb){
        if(this.averageGroundLevel < 0){
            this.averageGroundLevel = this.getAverageGroundLevel(world, sbb);
            if(this.averageGroundLevel < 0){
                return true;
            }
            this.boundingBox.offset(0, this.averageGroundLevel-this.boundingBox.maxY+Y_SIZE-1, 0);
        }

        this.fillWithBlocks(world, sbb, 0, 0, 0, X_SIZE-1, Y_SIZE-1, Z_SIZE-1, Blocks.AIR);
        this.spawnActualHouse(world, rand, sbb);

        for(int i = 0; i < X_SIZE; i++){
            for(int j = 0; j < Z_SIZE; j++){
                this.clearCurrentPositionBlocksUpwards(world, i, Y_SIZE, j, sbb);
                this.replaceAirAndLiquidDownwards(world, Blocks.DIRT.getDefaultState(), i, -1, j, sbb);
            }
        }

        return true;
    }

    public void fillWithBlocks(World world, StructureBoundingBox sbb, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, Block block){
        this.fillWithBlocks(world, sbb, minX, minY, minZ, maxX, maxY, maxZ, block.getDefaultState(), block.getDefaultState(), false);
    }

    public void spawnActualHouse(World world, Random rand, StructureBoundingBox sbb){

        int j = -1;

        setBlock(world, sbb, 0, j + 5, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 0, j + 5, 7, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 0, j + 6, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 0, j + 6, 6, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 0, j + 7, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 0, j + 7, 5, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 0, j + 8, 3, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 0, j + 8, 4, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 1, j + 0, 1, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 0, 2, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 0, 3, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 0, 4, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 0, 5, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 0, 6, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 1, 1, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 1, 2, Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 1, j + 1, 3, Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 1, j + 1, 4, Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 1, j + 1, 5, Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 1, j + 1, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 2, 1, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 2, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 2, 3, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 2, 4, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 2, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 2, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 3, 1, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 3, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 3, 3, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 3, 4, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 3, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 3, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 4, 1, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 4, 2, Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 4, 3, Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 4, 4, Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 4, 5, Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 4, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 5, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 1, j + 5, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 5, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 5, 3, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 5, 4, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 5, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 5, 6, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 5, 7, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 1, j + 6, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 1, j + 6, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 6, 3, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 6, 4, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 6, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 6, 6, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 1, j + 7, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 1, j + 7, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 7, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 7, 5, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 1, j + 8, 3, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 1, j + 8, 4, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 2, j + 0, 1, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 0, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 0, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 0, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 0, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 0, 6, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 1, 1, Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 2, j + 1, 6, Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 2, j + 2, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 2, 6, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 3, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 3, 6, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 4, 1, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 2, j + 4, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 4, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 4, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 4, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 4, 6, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 2, j + 5, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 2, j + 5, 7, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 2, j + 6, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 2, j + 6, 6, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 2, j + 7, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 2, j + 7, 5, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 2, j + 8, 3, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 2, j + 8, 4, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 3, j + 0, 1, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 0, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 0, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 0, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 0, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 0, 6, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 1, 1, Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 3, j + 1, 2, Blocks.CHEST.getDefaultState(), 3);
        setBlock(world, sbb, 3, j + 1, 6, Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 3, j + 2, 1, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 2, 6, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 3, 1, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 3, 6, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 4, 1, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 3, j + 4, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 4, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 4, 6, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 3, j + 5, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 3, j + 5, 1, Blocks.WEB.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 5, 7, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 3, j + 6, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 3, j + 6, 6, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 3, j + 7, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 3, j + 7, 4, Blocks.WEB.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 7, 5, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 3, j + 8, 3, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 3, j + 8, 4, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 4, j + 0, 1, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 0, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 0, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 0, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 0, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 0, 6, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 1, 1, Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 4, j + 1, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 4, j + 2, 1, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 2, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 4, j + 3, 1, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 3, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 4, j + 4, 1, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 4, j + 4, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 4, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 4, j + 5, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 4, j + 5, 6, Blocks.WEB.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 5, 7, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 4, j + 6, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 4, j + 6, 6, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 4, j + 7, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 4, j + 7, 5, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 4, j + 8, 3, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 4, j + 8, 4, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 5, j + 0, 1, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 0, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 0, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 0, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 0, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 0, 6, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 1, 1, Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 5, j + 2, 1, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 3, 1, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 3, 6, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 5, j + 4, 1, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 5, j + 4, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 4, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 4, 6, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 5, j + 5, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 5, j + 5, 1, Blocks.WEB.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 5, 7, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 5, j + 6, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 5, j + 6, 6, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 5, j + 7, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 5, j + 7, 3, Blocks.WEB.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 7, 5, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 5, j + 8, 3, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 5, j + 8, 4, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 6, j + 0, 1, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 0, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 0, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 0, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 0, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 0, 6, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 1, 1, Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 6, j + 1, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, j + 2, 1, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 2, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, j + 3, 1, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 3, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, j + 4, 1, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 6, j + 4, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 4, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 4, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, j + 5, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 6, j + 5, 7, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 6, j + 6, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 6, j + 6, 6, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 6, j + 7, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 6, j + 7, 5, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 6, j + 8, 3, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 6, j + 8, 4, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 7, j + 0, 1, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 0, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 0, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 0, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 0, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 0, 6, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 1, 1, Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 7, j + 1, 6, Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 7, j + 2, 1, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 2, 6, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 3, 1, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 3, 6, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 4, 1, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 7, j + 4, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 4, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 4, 6, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 7, j + 5, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 7, j + 5, 7, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 7, j + 6, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 7, j + 6, 2, Blocks.WEB.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 6, 6, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 7, j + 7, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 7, j + 7, 5, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 7, j + 8, 3, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 7, j + 8, 4, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 8, j + 0, 1, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 0, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 0, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 0, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 0, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 0, 6, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 1, 1, Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 8, j + 1, 6, Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 8, j + 2, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 2, 6, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 3, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 3, 6, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 4, 1, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 8, j + 4, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 4, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 4, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 4, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 4, 6, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 8, j + 5, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 8, j + 5, 3, Blocks.WEB.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 5, 5, Blocks.WEB.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 5, 6, Blocks.WEB.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 5, 7, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 8, j + 6, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 8, j + 6, 6, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 8, j + 7, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 8, j + 7, 5, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 8, j + 8, 3, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 8, j + 8, 4, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 9, j + 0, 1, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 0, 2, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 0, 3, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 0, 4, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 0, 5, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 0, 6, Blocks.DIRT.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 1, 1, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 9, j + 1, 2, Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 9, j + 1, 3, Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 9, j + 1, 4, Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 9, j + 1, 5, Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 9, j + 1, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 9, j + 2, 1, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 9, j + 2, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 2, 3, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 2, 4, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 2, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 2, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 9, j + 3, 1, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 9, j + 3, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 3, 3, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 3, 4, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 3, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 3, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 9, j + 4, 1, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 9, j + 4, 2, Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 9, j + 4, 3, Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 9, j + 4, 4, Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 9, j + 4, 5, Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 9, j + 4, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 9, j + 5, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 9, j + 5, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 5, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 5, 3, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 5, 4, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 5, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 5, 6, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 5, 7, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 9, j + 6, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 9, j + 6, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 6, 3, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 6, 4, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 6, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 6, 6, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 9, j + 7, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 9, j + 7, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 7, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 7, 5, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 9, j + 8, 3, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 9, j + 8, 4, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 10, j + 5, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 10, j + 5, 7, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 10, j + 6, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 10, j + 6, 6, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 10, j + 7, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 10, j + 7, 5, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 10, j + 8, 3, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 10, j + 8, 4, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 4, j + 1, 5, Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.SOUTH), 0);
        setBlock(world, sbb, 4, j + 2, 5, Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.SOUTH), 0);
        setBlock(world, sbb, 4, j + 3, 5, Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.SOUTH), 0);
        setBlock(world, sbb, 4, j + 4, 5, Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.SOUTH), 0);
        setBlock(world, sbb, 6, j + 2, 5, Blocks.TORCH.getDefaultState(),4);
        setBlock(world, sbb, 9, j + 4, 0, Blocks.DARK_OAK_STAIRS.getDefaultState(), 6);
        setBlock(world, sbb, 6, j + 4, 7, Blocks.DARK_OAK_STAIRS.getDefaultState(), 7);
        setBlock(world, sbb, 4, j + 4, 7, Blocks.DARK_OAK_STAIRS.getDefaultState(), 7);
        setBlock(world, sbb, 1, j + 4, 7, Blocks.DARK_OAK_STAIRS.getDefaultState(), 7);
        setBlock(world, sbb, 1, j + 4, 0, Blocks.DARK_OAK_STAIRS.getDefaultState(), 6);
        setBlock(world, sbb, 9, j + 4, 7, Blocks.DARK_OAK_STAIRS.getDefaultState(), 7);
        setBlock(world, sbb, 5, j + 2, 6, Blocks.DARK_OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER).withProperty(BlockDoor.FACING, EnumFacing.SOUTH).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.RIGHT), 0);
        setBlock(world, sbb, 5, j + 1, 6, Blocks.DARK_OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER).withProperty(BlockDoor.FACING, EnumFacing.SOUTH).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.RIGHT), 0);
        setBlock(world, sbb, 2, j + 1, 2, Blocks.BED.getDefaultState().withProperty(BlockBed.FACING, EnumFacing.SOUTH).withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD), 0);
        setBlock(world, sbb, 2, j + 1, 3, Blocks.BED.getDefaultState().withProperty(BlockBed.FACING, EnumFacing.SOUTH).withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT), 0);
        setBlock(world, sbb, 7, j + 5, 1, Blocks.CHEST.getDefaultState(), 3);
        setBlock(world, sbb, 6, j + 5, 1, Blocks.CHEST.getDefaultState(), 3);

        WorldGenUtils.spawnEntities(world, this, 4,0,4,1,"villager");

    }

    private void setBlock(World world, StructureBoundingBox sbb, int x, int y, int z, IBlockState s, int metadata) {

        if(s == Blocks.LOG2.getDefaultState()) {
            setBlockState(world, Blocks.LOG2.getStateFromMeta(metadata), x, y, z, sbb);
        }
        else if(s == Blocks.STONE_BRICK_STAIRS.getDefaultState() || s == Blocks.OAK_STAIRS.getDefaultState() || s == Blocks.DARK_OAK_STAIRS.getDefaultState()){

            if(metadata >= 4){
                EnumFacing facing = EnumFacing.EAST;
                if(metadata == 4)facing = EnumFacing.EAST;
                if(metadata == 5)facing = EnumFacing.WEST;
                if(metadata == 6)facing = EnumFacing.NORTH;
                if(metadata == 7)facing = EnumFacing.SOUTH;
                setBlockState(world, s.withProperty(BlockStairs.FACING, facing).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP), x, y, z, sbb);
            }

            if(metadata == 0){
                setBlockState(world, s.withProperty(BlockStairs.FACING, EnumFacing.EAST), x, y, z, sbb);
            }
            if(metadata == 1){
                setBlockState(world, s.withProperty(BlockStairs.FACING, EnumFacing.WEST), x, y, z, sbb);
            }
            if(metadata == 2){
                setBlockState(world, s.withProperty(BlockStairs.FACING, EnumFacing.NORTH), x, y, z, sbb);
            }
            if(metadata == 3){
                setBlockState(world, s.withProperty(BlockStairs.FACING, EnumFacing.SOUTH), x, y, z, sbb);
            }
        }

        else if(s == Blocks.STONE.getDefaultState()){
            setBlockState(world, Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH), x, y, z, sbb);
        }

        else if(s == Blocks.CHEST.getDefaultState()){
            generateChest(world, sbb, new Random(), x, y, z, OPLootTables.OPVILLAGE_CHEST);

//            setBlockState(world, Blocks.CHEST.getStateFromMeta(metadata), x, y, z, sbb);
  //          if(metadata == 3){
    //            setBlockState(world, Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.NORTH), x, y, z, sbb);
      //      }
        //    if(metadata == 4){
          //      setBlockState(world, Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.WEST), x, y, z, sbb);
            //}
        }

        else if(s == Blocks.TORCH.getDefaultState()){
            if(metadata == 1){
                setBlockState(world, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.EAST), x, y, z, sbb);
            }
            if(metadata == 2){
                setBlockState(world, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.WEST), x, y, z, sbb);
            }
            if(metadata == 3){
                setBlockState(world, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH), x, y, z, sbb);
            }
            if(metadata == 4){
                setBlockState(world, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH), x, y, z, sbb);
            }
            if(metadata == 5){
                setBlockState(world, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.UP), x, y, z, sbb);
            }
        }

        else if(s == Blocks.CARPET.getDefaultState()){
            if(metadata == 14){
                setBlockState(world, Blocks.CARPET.getDefaultState().withProperty(BlockCarpet.COLOR, EnumDyeColor.RED), x, y, z, sbb);
            }

        }

        else{
            setBlockState(world, s, x, y, z, sbb);
        }

    }


}
