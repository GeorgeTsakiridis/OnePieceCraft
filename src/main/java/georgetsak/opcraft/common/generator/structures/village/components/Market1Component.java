package georgetsak.opcraft.common.generator.structures.village.components;

import georgetsak.opcraft.common.network.proxy.CommonProxy;
import georgetsak.opcraft.common.registry.OPLootTables;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import java.util.List;
import java.util.Random;

public class Market1Component extends StructureVillagePieces.House1{

    private static final int X_SIZE = 10;
    private static final int Y_SIZE = 6;
    private static final int Z_SIZE = 12;

    private int averageGroundLevel = -1;

    public Market1Component(){

    }

    public Market1Component(StructureBoundingBox boundingBox, EnumFacing par5){
        this.setCoordBaseMode(par5);
        this.boundingBox = boundingBox;
    }

    public static Market1Component buildComponent(List pieces, int p1, int p2, int p3, EnumFacing p4){
        StructureBoundingBox boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, X_SIZE, Y_SIZE, Z_SIZE, p4);
        return canVillageGoDeeper(boundingBox) && StructureComponent.findIntersecting(pieces, boundingBox) == null ? new Market1Component(boundingBox, p4) : null;
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
         setBlock(world, sbb, 0, j + 0,  0, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 0, j + 0,  1, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 0, j + 0,  2, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 0, j + 0,  3, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 0, j + 0,  4, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 0, j + 0,  5, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 0, j + 0,  6, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 0, j + 0,  7, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 0, j + 0,  8, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 0, j + 0,  9, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 0, j + 0,  10, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 0, j + 0,  11, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 1, j + 0,  0, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 1, j + 0,  1, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 1, j + 0,  2, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 1, j + 0,  3, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 1, j + 0,  4, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 1, j + 0,  5, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 1, j + 0,  6, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 1, j + 0,  7, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 1, j + 0,  8, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 1, j + 0,  9, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 1, j + 0,  10, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 1, j + 0,  11, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 1, j + 1,  1, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 1, j + 1,  2, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 1, j + 1,  9, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 1, j + 1,  10, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 1, j + 2,  1, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 1, j + 2,  2, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 1, j + 2,  9, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 1, j + 2,  10, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 1, j + 3,  1, Blocks.PLANKS.getDefaultState(), 0);
         setBlock(world, sbb, 1, j + 3,  2, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 1, j + 3,  9, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 1, j + 3,  10, Blocks.PLANKS.getDefaultState(), 0);
         setBlock(world, sbb, 1, j + 4,  2, Blocks.WOODEN_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 1, j + 4,  9, Blocks.WOODEN_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 2, j + 0,  0, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 2, j + 0,  1, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 2, j + 0,  2, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 2, j + 0,  3, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 2, j + 0,  4, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 2, j + 0,  5, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 2, j + 0,  6, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 2, j + 0,  7, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 2, j + 0,  8, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 2, j + 0,  9, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 2, j + 0,  10, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 2, j + 0,  11, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 2, j + 1,  1, Blocks.CHEST.getDefaultState(), 2);
         setBlock(world, sbb, 2, j + 1,  2, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 2, j + 1,  9, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 2, j + 1,  10, Blocks.CHEST.getDefaultState(), 3);
         setBlock(world, sbb, 2, j + 3,  1, Blocks.PLANKS.getDefaultState(), 0);
         setBlock(world, sbb, 2, j + 3,  10, Blocks.WOODEN_SLAB.getDefaultState(), 8);
         setBlock(world, sbb, 2, j + 4,  2, Blocks.WOODEN_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 2, j + 4,  9, Blocks.WOODEN_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 3, j + 0,  0, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 3, j + 0,  1, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 3, j + 0,  2, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 3, j + 0,  3, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 3, j + 0,  4, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 3, j + 0,  5, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 3, j + 0,  6, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 3, j + 0,  7, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 3, j + 0,  8, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 3, j + 0,  9, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 3, j + 0,  10, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 3, j + 0,  11, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 3, j + 1,  2, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 3, j + 1,  9, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 3, j + 3,  1, Blocks.PLANKS.getDefaultState(), 0);
         setBlock(world, sbb, 3, j + 3,  10, Blocks.WOODEN_SLAB.getDefaultState(), 8);
         setBlock(world, sbb, 3, j + 4,  2, Blocks.WOODEN_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 3, j + 4,  9, Blocks.WOODEN_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 4, j + 0,  0, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 4, j + 0,  1, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 4, j + 0,  2, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 4, j + 0,  3, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 4, j + 0,  4, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 4, j + 0,  5, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 4, j + 0,  6, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 4, j + 0,  7, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 4, j + 0,  8, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 4, j + 0,  9, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 4, j + 0,  10, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 4, j + 0,  11, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 4, j + 1,  1, Blocks.CHEST.getDefaultState(), 2);
         setBlock(world, sbb, 4, j + 1,  2, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 4, j + 1,  9, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 4, j + 1,  10, Blocks.CHEST.getDefaultState(), 3);
         setBlock(world, sbb, 4, j + 3,  1, Blocks.PLANKS.getDefaultState(), 0);
         setBlock(world, sbb, 4, j + 3,  10, Blocks.WOODEN_SLAB.getDefaultState(), 8);
         setBlock(world, sbb, 4, j + 4,  2, Blocks.WOODEN_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 4, j + 4,  9, Blocks.WOODEN_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 5, j + 0,  0, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 5, j + 0,  1, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 5, j + 0,  2, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 5, j + 0,  3, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 5, j + 0,  4, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 5, j + 0,  5, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 5, j + 0,  6, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 5, j + 0,  7, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 5, j + 0,  8, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 5, j + 0,  9, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 5, j + 0,  10, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 5, j + 0,  11, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 5, j + 1,  1, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 5, j + 1,  2, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 5, j + 1,  9, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 5, j + 1,  10, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 5, j + 2,  1, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 5, j + 2,  2, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 5, j + 2,  9, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 5, j + 2,  10, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 5, j + 3,  1, Blocks.PLANKS.getDefaultState(), 0);
         setBlock(world, sbb, 5, j + 3,  2, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 5, j + 3,  9, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 5, j + 3,  10, Blocks.PLANKS.getDefaultState(), 0);
         setBlock(world, sbb, 5, j + 4,  2, Blocks.WOODEN_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 5, j + 4,  9, Blocks.WOODEN_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 6, j + 0,  0, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 6, j + 0,  1, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 6, j + 0,  2, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 6, j + 0,  3, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 6, j + 0,  4, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 6, j + 0,  5, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 6, j + 0,  6, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 6, j + 0,  7, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 6, j + 0,  8, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 6, j + 0,  9, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 6, j + 0,  10, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 6, j + 0,  11, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 7, j + 0,  0, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 7, j + 0,  1, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 7, j + 0,  2, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 7, j + 0,  3, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 7, j + 0,  4, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 7, j + 0,  5, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 7, j + 0,  6, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 7, j + 0,  7, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 7, j + 0,  8, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 7, j + 0,  9, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 7, j + 0,  10, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 7, j + 0,  11, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 7, j + 1,  4, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 7, j + 1,  5, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 7, j + 1,  6, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 7, j + 1,  7, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 7, j + 2,  4, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 7, j + 2,  7, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 7, j + 3,  4, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 7, j + 3,  7, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 7, j + 4,  4, Blocks.WOODEN_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 7, j + 4,  5, Blocks.WOODEN_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 7, j + 4,  6, Blocks.WOODEN_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 7, j + 4,  7, Blocks.WOODEN_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 8, j + 0,  0, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 8, j + 0,  1, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 8, j + 0,  2, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 8, j + 0,  3, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 8, j + 0,  4, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 8, j + 0,  5, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 8, j + 0,  6, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 8, j + 0,  7, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 8, j + 0,  8, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 8, j + 0,  9, Blocks.STONE.getDefaultState(), 4);
         setBlock(world, sbb, 8, j + 0,  10, Blocks.STONE.getDefaultState(), 2);
         setBlock(world, sbb, 8, j + 0,  11, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 8, j + 1,  4, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 8, j + 1,  6, Blocks.CHEST.getDefaultState(), 5);
         setBlock(world, sbb, 8, j + 1,  7, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 8, j + 2,  4, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 8, j + 2,  7, Blocks.OAK_FENCE.getDefaultState(), 0);
         setBlock(world, sbb, 8, j + 3,  4, Blocks.PLANKS.getDefaultState(), 0);
         setBlock(world, sbb, 8, j + 3,  5, Blocks.WOODEN_SLAB.getDefaultState(), 8);
         setBlock(world, sbb, 8, j + 3,  6, Blocks.WOODEN_SLAB.getDefaultState(), 8);
         setBlock(world, sbb, 8, j + 3,  7, Blocks.PLANKS.getDefaultState(), 0);
         setBlock(world, sbb, 9, j + 0,  0, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 9, j + 0,  1, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 9, j + 0,  2, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 9, j + 0,  3, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 9, j + 0,  4, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 9, j + 0,  5, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 9, j + 0,  6, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 9, j + 0,  7, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 9, j + 0,  8, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 9, j + 0,  9, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 9, j + 0,  10, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 9, j + 0,  11, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0);
         setBlock(world, sbb, 2, j + 2,  2, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 0);
         setBlock(world, sbb, 2, j + 2,  9, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 0);
         setBlock(world, sbb, 3, j + 2,  2, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 0);
         setBlock(world, sbb, 3, j + 2,  9, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 0);
         setBlock(world, sbb, 4, j + 2,  2, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 0);
         setBlock(world, sbb, 4, j + 2,  9, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 0);
         setBlock(world, sbb, 7, j + 2,  5, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 0);
         setBlock(world, sbb, 7, j + 2,  6, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 0);


    }

    private void setBlock(World world, StructureBoundingBox sbb, int x, int y, int z, IBlockState s, int metadata) {

        if(s == Blocks.CHEST.getDefaultState()){
            generateChest(world, sbb, new Random(), x, y, z, OPLootTables.OPVILLAGE_CHEST);
        }

        if(s == Blocks.STONE.getDefaultState()){
            if(metadata == 2){
                setBlockState(world, s.withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE_SMOOTH), x, y, z, sbb);
            }
            else if(metadata == 4){
                setBlockState(world, s.withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE_SMOOTH), x, y, z, sbb);
            }
        }

        else{
            setBlockState(world, s, x, y, z, sbb);
        }

    }


}
