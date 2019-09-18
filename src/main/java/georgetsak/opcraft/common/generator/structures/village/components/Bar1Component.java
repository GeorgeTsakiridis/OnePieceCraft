package georgetsak.opcraft.common.generator.structures.village.components;

import georgetsak.opcraft.common.util.WorldGenUtils;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import java.util.List;
import java.util.Random;

public class Bar1Component extends StructureVillagePieces.House1{

    private static final int X_SIZE = 14;
    private static final int Y_SIZE = 10;
    private static final int Z_SIZE = 17;

    private int averageGroundLevel = -1;

    public Bar1Component(){

    }

    public Bar1Component(StructureBoundingBox boundingBox, EnumFacing par5){
        this.setCoordBaseMode(par5);
        this.boundingBox = boundingBox;
    }

    public static Bar1Component buildComponent(List pieces, int p1, int p2, int p3, EnumFacing p4){
        StructureBoundingBox boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, X_SIZE, Y_SIZE, Z_SIZE, p4);
        return canVillageGoDeeper(boundingBox) && StructureComponent.findIntersecting(pieces, boundingBox) == null ? new Bar1Component(boundingBox, p4) : null;
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

        setBlock(world, sbb, 0, j + 6, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 0, j + 6, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 0, j + 6, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 0, j + 6, 3, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 0, j + 6, 4, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 0, j + 6, 5, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 0, j + 6, 6, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 0, j + 6, 7, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 0, j + 6, 8, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 0, j + 6, 9, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 0, j + 6, 10, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 0, j + 6, 11, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 0, j + 6, 12, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 0, j + 6, 13, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 0, j + 6, 14, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 0, j + 6, 15, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 1, j + 0, 7, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 0, 8, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 1, 1,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 1, 2,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 1, j + 1, 3,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 1, j + 1, 4,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 1, j + 1, 5,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 1, j + 1, 6,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 1, 9,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 1, 10,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 1, j + 1, 11,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 1, j + 1, 12,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 1, j + 1, 13,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 1, j + 1, 14,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 2, 1,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 2, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 2, 3, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 2, 4, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 2, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 2, 6,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 2, 9,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 2, 10, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 2, 11, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 2, 12, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 2, 13, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 2, 14,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 3, 1,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 3, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 3, 3, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 3, 4, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 3, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 3, 6,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 3, 7,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 3, 8,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 3, 9,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 3, 10, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 3, 11, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 3, 12, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 3, 13, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 3, 14,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 4, 1,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 4, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 4, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 4, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 4, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 4, 6,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 4, 7,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 4, 8,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 4, 9,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 4, 10, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 4, 11, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 4, 12, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 4, 13, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 4, 14,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 5, 1,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, j + 5, 2,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 5, 3,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 5, 4,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 5, 5,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 5, 6,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 5, 7,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 5, 8,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 5, 9,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 5, 10,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 5, 11,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 5, 12,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 5, 13,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 5, 14,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 1, j + 6, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 1, j + 6, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 6, 2, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 6, 3, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 6, 4, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 6, 5, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 6, 6, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 6, 7, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 6, 8, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 6, 9, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 6, 10, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 6, 11, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 6, 12, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 6, 13, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 6, 14, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 6, 15, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 1, j + 7, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 1, j + 7, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 7, 3, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 7, 4, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 7, 5, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 7, 6, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 7, 7, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 7, 8, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 7, 9, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 7, 10, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 7, 11, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 7, 12, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 7, 13, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 7, 14, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 2, j + 0, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 0, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 0, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 0, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 0, 6, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 0, 7, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 0, 8, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 0, 9, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 0, 10, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 0, 11, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 0, 12, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 0, 13, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 1, 1,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 2, j + 1, 14,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 2, j + 2, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 2, 14, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 3, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 3, 14, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 4, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 4, 14, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 5, 1,  Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 2, j + 5, 14,  Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 2, j + 6, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 2, j + 6, 1, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 6, 14, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 6, 15, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 2, j + 7, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 2, j + 7, 14, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 2, j + 8, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 8, 3, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 8, 4, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 8, 5, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 8, 6, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 8, 7, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 8, 8, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 8, 9, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 8, 10, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 8, 11, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 8, 12, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 2, j + 8, 13, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 0, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 0, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 0, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 0, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 0, 6, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 0, 7, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 0, 8, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 0, 9, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 0, 10, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 0, 11, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 0, 12, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 0, 13, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 1, 1,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 3, j + 1, 14,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 3, j + 2, 1, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 2, 14, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 3, 1, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 3, 14, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 4, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 4, 14, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 5, 1,  Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 3, j + 5, 14,  Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 3, j + 6, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 3, j + 6, 1, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 6, 14, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 6, 15, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 3, j + 7, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 3, j + 7, 14, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 3, j + 8, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 3, j + 8, 13, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 3, j + 9, 3, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 3, j + 9, 4, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 3, j + 9, 5, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 3, j + 9, 6, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 3, j + 9, 7, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 3, j + 9, 8, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 3, j + 9, 9, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 3, j + 9, 10, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 3, j + 9, 11, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 3, j + 9, 12, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 4, j + 0, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 0, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 0, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 0, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 0, 6, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 0, 7, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 0, 8, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 0, 9, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 0, 10, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 0, 11, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 0, 12, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 0, 13, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 1, 1,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 4, j + 1, 14,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 4, j + 2, 1, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 2, 14, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 3, 1, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 3, 14, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 4, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 4, 14, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 5, 1,  Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 4, j + 5, 14,  Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 4, j + 6, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 4, j + 6, 1, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 6, 14, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 6, 15, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 4, j + 7, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 4, j + 7, 14, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 4, j + 8, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 4, j + 8, 13, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 4, j + 9, 3, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 4, j + 9, 4, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 4, j + 9, 5, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 4, j + 9, 6, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 4, j + 9, 7, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 4, j + 9, 8, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 4, j + 9, 9, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 4, j + 9, 10, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 4, j + 9, 11, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 4, j + 9, 12, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 5, j + 0, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 0, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 0, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 0, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 0, 6, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 0, 7, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 0, 8, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 0, 9, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 0, 10, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 0, 11, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 0, 12, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 0, 13, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 1, 1,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 5, j + 1, 14,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 5, j + 2, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 2, 14, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 3, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 3, 14, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 4, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 4, 14, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 5, 1,  Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 5, j + 5, 14,  Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 5, j + 6, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 5, j + 6, 1, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 6, 14, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 6, 15, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 5, j + 7, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 5, j + 7, 14, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 5, j + 8, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 5, j + 8, 13, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 5, j + 9, 3, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 5, j + 9, 4, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 5, j + 9, 5, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 5, j + 9, 6, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 5, j + 9, 7, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 5, j + 9, 8, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 5, j + 9, 9, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 5, j + 9, 10, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 5, j + 9, 11, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 5, j + 9, 12, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 6, j + 0, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 0, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 0, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 0, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 0, 6, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 0, 7, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 0, 8, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 0, 9, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 0, 10, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 0, 11, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 0, 12, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 0, 13, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 1, 1,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, j + 1, 14,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, j + 2, 1,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, j + 2, 14,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, j + 3, 1,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, j + 3, 14,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, j + 4, 1,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, j + 4, 14,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, j + 5, 1,  Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 6, j + 5, 14,  Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 6, j + 6, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 6, j + 6, 1, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 6, 14, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 6, 15, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 6, j + 7, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 6, j + 7, 14, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 6, j + 8, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 6, j + 8, 13, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 6, j + 9, 3, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 6, j + 9, 4, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 6, j + 9, 5, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 6, j + 9, 6, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 6, j + 9, 7, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 6, j + 9, 8, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 6, j + 9, 9, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 6, j + 9, 10, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 6, j + 9, 11, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 6, j + 9, 12, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 7, j + 0, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 0, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 0, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 0, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 0, 6, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 0, 7, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 0, 8, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 0, 9, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 0, 10, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 0, 11, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 0, 12, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 0, 13, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 1, 1,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 7, j + 1, 14,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 7, j + 2, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 2, 14, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 3, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 3, 14, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 4, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 4, 14, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 5, 1,  Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 7, j + 5, 14,  Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 7, j + 6, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 7, j + 6, 1, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 6, 14, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 6, 15, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 7, j + 7, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 7, j + 7, 14, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 7, j + 8, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 7, j + 8, 13, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 7, j + 9, 3, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 7, j + 9, 4, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 7, j + 9, 5, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 7, j + 9, 6, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 7, j + 9, 7, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 7, j + 9, 8, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 7, j + 9, 9, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 7, j + 9, 10, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 7, j + 9, 11, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 7, j + 9, 12, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 8, j + 0, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 0, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 0, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 0, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 0, 6, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 0, 7, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 0, 8, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 0, 9, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 0, 10, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 0, 11, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 0, 12, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 0, 13, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 1, 1,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 8, j + 1, 5, Blocks.PLANKS.getDefaultState(), 5);
        setBlock(world, sbb, 8, j + 1, 6, Blocks.WOODEN_SLAB.getDefaultState(), 13);
        setBlock(world, sbb, 8, j + 1, 7, Blocks.WOODEN_SLAB.getDefaultState(), 13);
        setBlock(world, sbb, 8, j + 1, 8, Blocks.WOODEN_SLAB.getDefaultState(), 13);
        setBlock(world, sbb, 8, j + 1, 9, Blocks.WOODEN_SLAB.getDefaultState(), 13);
        setBlock(world, sbb, 8, j + 1, 10, Blocks.PLANKS.getDefaultState(), 5);
        setBlock(world, sbb, 8, j + 1, 14,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 8, j + 2, 1, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 2, 14, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 3, 1, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 3, 14, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 4, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 4, 14, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 5, 1,  Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 8, j + 5, 14,  Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 8, j + 6, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 8, j + 6, 1, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 6, 14, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 8, j + 6, 15, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 8, j + 7, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 8, j + 7, 14, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 8, j + 8, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 8, j + 8, 13, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 8, j + 9, 3, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 8, j + 9, 4, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 8, j + 9, 5, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 8, j + 9, 6, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 8, j + 9, 7, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 8, j + 9, 8, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 8, j + 9, 9, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 8, j + 9, 10, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 8, j + 9, 11, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 8, j + 9, 12, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 9, j + 0, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 0, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 0, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 0, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 0, 6, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 0, 7, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 0, 8, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 0, 9, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 0, 10, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 0, 11, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 0, 12, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 0, 13, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 1, 1,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 9, j + 1, 5, Blocks.WOODEN_SLAB.getDefaultState(), 13);
        setBlock(world, sbb, 9, j + 1, 10, Blocks.WOODEN_SLAB.getDefaultState(), 13);
        setBlock(world, sbb, 9, j + 1, 14,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 9, j + 2, 1, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 2, 14, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 3, 1, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 3, 14, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 4, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 4, 14, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 5, 1,  Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 9, j + 5, 14,  Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 9, j + 6, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 9, j + 6, 1, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 6, 14, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 6, 15, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 9, j + 7, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 9, j + 7, 14, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 9, j + 8, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 9, j + 8, 13, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 9, j + 9, 3, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 9, j + 9, 4, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 9, j + 9, 5, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 9, j + 9, 6, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 9, j + 9, 7, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 9, j + 9, 8, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 9, j + 9, 9, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 9, j + 9, 10, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 9, j + 9, 11, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 9, j + 9, 12, Blocks.STONE_SLAB.getDefaultState(), 5);
        setBlock(world, sbb, 10, j + 0, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 0, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 0, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 0, 5, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 0, 6, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 0, 7, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 0, 8, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 0, 9, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 0, 10, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 0, 11, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 0, 12, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 0, 13, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 1, 1,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 10, j + 1, 5, Blocks.WOODEN_SLAB.getDefaultState(), 13);
        setBlock(world, sbb, 10, j + 1, 10, Blocks.WOODEN_SLAB.getDefaultState(), 13);
        setBlock(world, sbb, 10, j + 1, 14,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 10, j + 2, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 2, 14, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 3, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 3, 14, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 4, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 4, 14, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 5, 1,  Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 10, j + 5, 14,  Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 10, j + 6, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 10, j + 6, 1, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 6, 14, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 6, 15, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 10, j + 7, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 10, j + 7, 14, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 10, j + 8, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 10, j + 8, 3, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 10, j + 8, 4, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 10, j + 8, 5, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 10, j + 8, 6, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 10, j + 8, 7, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 10, j + 8, 8, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 10, j + 8, 9, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 10, j + 8, 10, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 10, j + 8, 11, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 10, j + 8, 12, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 10, j + 8, 13, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 11, j + 1, 1,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 1, 2,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 11, j + 1, 3,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 11, j + 1, 4,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 11, j + 1, 5,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 1, 6,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 11, j + 1, 7,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 11, j + 1, 8,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 11, j + 1, 9,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 11, j + 1, 10,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 1, 11,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 11, j + 1, 12,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 11, j + 1, 13,  Blocks.STONE.getDefaultState(), 6);
        setBlock(world, sbb, 11, j + 1, 14,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 2, 1,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 2, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 2, 3, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 2, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 2, 5,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 2, 6, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 2, 7, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 2, 8, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 2, 9, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 2, 10,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 2, 11, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 2, 12, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 2, 13, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 2, 14,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 3, 1,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 3, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 3, 3, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 3, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 3, 5,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 3, 6, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 3, 7, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 3, 8, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 3, 9, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 3, 10,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 3, 11, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 3, 12, Blocks.GLASS_PANE.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 3, 13, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 3, 14,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 4, 1,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 4, 2, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 4, 3, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 4, 4, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 4, 5,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 4, 6, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 4, 7, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 4, 8, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 4, 9, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 4, 10,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 4, 11, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 4, 12, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 4, 13, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 4, 14,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 5, 1,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 5, 2,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 11, j + 5, 3,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 11, j + 5, 4,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 11, j + 5, 5,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 11, j + 5, 6,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 11, j + 5, 7,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 11, j + 5, 8,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 11, j + 5, 9,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 11, j + 5, 10,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 11, j + 5, 11,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 11, j + 5, 12,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 11, j + 5, 13,  Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 11, j + 5, 14,  Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 6, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 11, j + 6, 1, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 6, 2, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 6, 3, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 6, 4, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 6, 5, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 6, 6, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 6, 7, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 6, 8, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 6, 9, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 6, 10, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 6, 11, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 6, 12, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 6, 13, Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 6, 14, Blocks.PLANKS.getDefaultState(), 0);
        setBlock(world, sbb, 11, j + 6, 15, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 11, j + 7, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 7, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 7, 3, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 7, 4, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 7, 5, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 7, 6, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 7, 7, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 7, 8, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 7, 9, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 7, 10, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 7, 11, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 7, 12, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 7, 13, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 11, j + 7, 14, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 12, j + 6, 0, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 12, j + 6, 1, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 12, j + 6, 2, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 12, j + 6, 3, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 12, j + 6, 4, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 12, j + 6, 5, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 12, j + 6, 6, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 12, j + 6, 7, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 12, j + 6, 8, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 12, j + 6, 9, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 12, j + 6, 10, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 12, j + 6, 11, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 12, j + 6, 12, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 12, j + 6, 13, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 12, j + 6, 14, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 12, j + 6, 15, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 3, j + 2, 3, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 2, 12, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 2, 5, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 0);
        setBlock(world, sbb, 4, j + 2, 10, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 2, 3, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 2, 3, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 2, 3, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 2, 3, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 2, 12, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 0);


        setBlock(world, sbb, 4, j + 1, 10, Blocks.SPRUCE_FENCE.getDefaultState(), 0); //SPRUCE FENCE
        setBlock(world, sbb, 4, j + 1, 5, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 1, 3, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 3, j + 1, 12, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 1, 12, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 9, j + 1, 3, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 6, 5, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 6, 10, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 7, j + 1, 3, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 8, 5, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 8, 10, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 7, 5, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 7, 10, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 6, 4, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 6, 5, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 6, 6, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 6, 9, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 6, 10, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 6, 11, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 6, j + 1, 3, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 6, 5, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 6, 10, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 1, 3, Blocks.SPRUCE_FENCE.getDefaultState(), 0);
        setBlock(world, sbb, 1, j + 2, 7, Blocks.DARK_OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.RIGHT).withProperty(BlockDoor.FACING, EnumFacing.EAST), 0);
        setBlock(world, sbb, 1, j + 2, 8, Blocks.DARK_OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.LEFT).withProperty(BlockDoor.FACING, EnumFacing.EAST), 0);
        setBlock(world, sbb, 1, j + 1, 7, Blocks.DARK_OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.RIGHT).withProperty(BlockDoor.FACING, EnumFacing.EAST), 0);
        setBlock(world, sbb, 1, j + 1, 8, Blocks.DARK_OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.LEFT).withProperty(BlockDoor.FACING, EnumFacing.EAST), 0);
        setBlock(world, sbb, 12, j + 5, 1, Blocks.DARK_OAK_STAIRS.getDefaultState(), 5);
        setBlock(world, sbb, 12, j + 5, 5, Blocks.DARK_OAK_STAIRS.getDefaultState(), 5);
        setBlock(world, sbb, 12, j + 5, 10, Blocks.DARK_OAK_STAIRS.getDefaultState(), 5);
        setBlock(world, sbb, 12, j + 5, 14, Blocks.DARK_OAK_STAIRS.getDefaultState(), 5);
        setBlock(world, sbb, 11, j + 5, 15, Blocks.DARK_OAK_STAIRS.getDefaultState(), 7);
        setBlock(world, sbb, 11, j + 5, 0, Blocks.DARK_OAK_STAIRS.getDefaultState(), 6);
        setBlock(world, sbb, 1, j + 5, 15, Blocks.DARK_OAK_STAIRS.getDefaultState(), 7);
        setBlock(world, sbb, 1, j + 5, 0, Blocks.DARK_OAK_STAIRS.getDefaultState(), 6);
        setBlock(world, sbb, 6, j + 5, 15, Blocks.DARK_OAK_STAIRS.getDefaultState(), 7);
        setBlock(world, sbb, 6, j + 5, 0, Blocks.DARK_OAK_STAIRS.getDefaultState(), 6);
        setBlock(world, sbb, 0, j + 5, 1, Blocks.DARK_OAK_STAIRS.getDefaultState(), 4);
        setBlock(world, sbb, 0, j + 5, 6, Blocks.DARK_OAK_STAIRS.getDefaultState(), 4);
        setBlock(world, sbb, 0, j + 5, 9, Blocks.DARK_OAK_STAIRS.getDefaultState(), 4);
        setBlock(world, sbb, 0, j + 5, 14, Blocks.DARK_OAK_STAIRS.getDefaultState(), 4);

        setBlock(world, sbb, 5, j + 1, 5, Blocks.DARK_OAK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 5, j + 1, 10, Blocks.DARK_OAK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 1, 12, Blocks.DARK_OAK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 1, 13, Blocks.DARK_OAK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 1, 2, Blocks.DARK_OAK_STAIRS.getDefaultState(), 0);
        setBlock(world, sbb, 10, j + 1, 3, Blocks.DARK_OAK_STAIRS.getDefaultState(), 0);


        setBlock(world, sbb, 7, j + 7, 5,  Blocks.TORCH.getDefaultState(), 5);//TORCHES
        setBlock(world, sbb, 7, j + 7, 10, Blocks.TORCH.getDefaultState(), 5);
        setBlock(world, sbb, 6, j + 7, 4,  Blocks.TORCH.getDefaultState(), 5);
        setBlock(world, sbb, 6, j + 7, 6,  Blocks.TORCH.getDefaultState(), 5);
        setBlock(world, sbb, 6, j + 7, 9,  Blocks.TORCH.getDefaultState(), 5);
        setBlock(world, sbb, 6, j + 7, 11, Blocks.TORCH.getDefaultState(), 5);
        setBlock(world, sbb, 5, j + 7, 5,  Blocks.TORCH.getDefaultState(), 5);
        setBlock(world, sbb, 5, j + 7, 10, Blocks.TORCH.getDefaultState(), 5);

        setBlock(world, sbb, 0, j + 2, 6,  Blocks.TORCH.getDefaultState(), 2);
        setBlock(world, sbb, 0, j + 2, 9,  Blocks.TORCH.getDefaultState(), 2);


        setBlock(world, sbb, 4, j + 1, 11, Blocks.DARK_OAK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 3, j + 1, 2, Blocks.DARK_OAK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 3, j + 1, 5, Blocks.DARK_OAK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 3, j + 1, 10, Blocks.DARK_OAK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 3, j + 1, 13, Blocks.DARK_OAK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 2, j + 1, 2, Blocks.DARK_OAK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 2, j + 1, 3, Blocks.DARK_OAK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 2, j + 1, 12, Blocks.DARK_OAK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 2, j + 1, 13, Blocks.DARK_OAK_STAIRS.getDefaultState(), 1);
        setBlock(world, sbb, 9, j + 1, 13, Blocks.DARK_OAK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 9, j + 1, 2, Blocks.DARK_OAK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 7, j + 1, 2, Blocks.DARK_OAK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 6, j + 1, 2, Blocks.DARK_OAK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 5, j + 1, 2, Blocks.DARK_OAK_STAIRS.getDefaultState(), 3);
        setBlock(world, sbb, 4, j + 1, 4, Blocks.DARK_OAK_STAIRS.getDefaultState(), 3);//?
        setBlock(world, sbb, 4, j + 1, 6, Blocks.DARK_OAK_STAIRS.getDefaultState(), 2);
        setBlock(world, sbb, 4, j + 1, 9, Blocks.DARK_OAK_STAIRS.getDefaultState(), 3);

        WorldGenUtils.spawnEntities(world, this, 5, 1, 5, 2, "villager");
        WorldGenUtils.spawnEntities(world, this, 5, 2, 5, 2, "marine");

    }

    private void setBlock(World world, StructureBoundingBox sbb, int x, int y, int z, IBlockState s, int metadata) {



           if(s == Blocks.STONE_BRICK_STAIRS.getDefaultState() || s == Blocks.OAK_STAIRS.getDefaultState() || s == Blocks.DARK_OAK_STAIRS.getDefaultState()){

            if(metadata >= 4){
                EnumFacing facing = EnumFacing.EAST;
                if(metadata == 4)facing = EnumFacing.EAST;
                if(metadata == 5)facing = EnumFacing.WEST;
                if(metadata == 6)facing = EnumFacing.NORTH;
                if(metadata == 7)facing = EnumFacing.SOUTH;
                setBlockState(world, s.withProperty(BlockStairs.FACING, facing).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP), x, y, z, sbb);
            }
            if(metadata == 0)setBlockState(world, s.withProperty(BlockStairs.FACING, EnumFacing.EAST), x, y, z, sbb);
            if(metadata == 1)setBlockState(world, s.withProperty(BlockStairs.FACING, EnumFacing.WEST), x, y, z, sbb);
            if(metadata == 2)setBlockState(world, s.withProperty(BlockStairs.FACING, EnumFacing.NORTH), x, y, z, sbb);
            if(metadata == 3)setBlockState(world, s.withProperty(BlockStairs.FACING, EnumFacing.SOUTH), x, y, z, sbb);

        }

        else if(s == Blocks.WOODEN_SLAB.getDefaultState()){
               setBlockState(world, s.withProperty(BlockWoodSlab.HALF, BlockSlab.EnumBlockHalf.TOP).withProperty(BlockWoodSlab.VARIANT, BlockPlanks.EnumType.DARK_OAK), x, y, z, sbb);
           }

        else if(s == Blocks.LOG2.getDefaultState()) {
            setBlockState(world, Blocks.LOG2.getStateFromMeta(metadata), x, y, z, sbb);
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

        else if(s == Blocks.STONE.getDefaultState()){
            setBlockState(world, Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH), x, y, z, sbb);
        }

        else if(s == Blocks.STONE_SLAB.getDefaultState()){
               setBlockState(world, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.byMetadata(5)).withProperty(BlockStoneSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM), x, y, z, sbb);
           }

        else{
            setBlockState(world, s, x, y, z, sbb);
        }

    }


}
