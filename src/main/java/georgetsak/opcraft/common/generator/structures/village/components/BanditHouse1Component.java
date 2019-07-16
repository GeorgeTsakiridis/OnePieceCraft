package georgetsak.opcraft.common.generator.structures.village.components;

import georgetsak.opcraft.common.entity.other.EntityBandit;
import georgetsak.opcraft.common.network.proxy.CommonProxy;
import georgetsak.opcraft.common.registry.OPLootTables;
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

public class BanditHouse1Component extends StructureVillagePieces.House2{

    private static final int X_SIZE = 7;
    private static final int Y_SIZE = 6;
    private static final int Z_SIZE = 7;

    private int averageGroundLevel = -1;

    public BanditHouse1Component(){

    }

    public BanditHouse1Component(StructureBoundingBox boundingBox, EnumFacing par5){
        this.setCoordBaseMode(par5);
        this.boundingBox = boundingBox;
    }

    public static BanditHouse1Component buildComponent(List pieces, int p1, int p2, int p3, EnumFacing p4){
        StructureBoundingBox boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, X_SIZE, Y_SIZE, Z_SIZE, p4);
        return canVillageGoDeeper(boundingBox) && StructureComponent.findIntersecting(pieces, boundingBox) == null ? new BanditHouse1Component(boundingBox, p4) : null;
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

        setBlock(world, sbb, 0, 0, 0, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 0, 0, 1,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 0, 0, 2,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 0, 0, 4,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 0, 0, 5,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 0, 0, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 0, 1, 0, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 0, 1, 1,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 0, 1, 2,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 0, 1, 4,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 0, 1, 5,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 0, 1, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 0, 2, 0, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 0, 2, 1,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 0, 2, 2,  Blocks.STONEBRICK.getDefaultState(), 1);
        setBlock(world, sbb, 0, 2, 3,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 0, 2, 4,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 0, 2, 5,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 0, 2, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 0, 3, 0, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 0, 3, 1,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 0, 3, 2,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 0, 3, 3,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 0, 3, 4,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 0, 3, 5,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 0, 3, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 0, 4, 0, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 0, 4, 1, Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 0, 4, 2, Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 0, 4, 3, Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 0, 4, 4, Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 0, 4, 5, Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 0, 4, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, 0, 0,  Blocks.STONEBRICK.getDefaultState(), 1);
        setBlock(world, sbb, 1, 0, 1, Blocks.CHEST.getDefaultState(), 3);
        setBlock(world, sbb, 1, 0, 6,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 1, 1, 0,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 1, 1, 6,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 1, 2, 0,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 1, 2, 6,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 1, 3, 0,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 1, 3, 6,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 1, 4, 0, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 1, 4, 1,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 1, 4, 2,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 1, 4, 3,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 1, 4, 4,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 1, 4, 5,  Blocks.STONEBRICK.getDefaultState(), 1);
        setBlock(world, sbb, 1, 4, 6, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 2, 0, 0,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 2, 0, 1, Blocks.CHEST.getDefaultState(), 3);
        setBlock(world, sbb, 2, 0, 6,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 2, 1, 0,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 2, 1, 6,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 2, 2, 0,  Blocks.STONEBRICK.getDefaultState(), 1);
        setBlock(world, sbb, 2, 2, 6,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 2, 3, 0,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 2, 3, 6,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 2, 4, 0, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 2, 4, 1,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 2, 4, 2,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 2, 4, 3,  Blocks.STONEBRICK.getDefaultState(), 1);
        setBlock(world, sbb, 2, 4, 4,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 2, 4, 5,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 2, 4, 6, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 3, 0, 0,  Blocks.STONEBRICK.getDefaultState(), 1);
        setBlock(world, sbb, 3, 0, 6,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 3, 1, 0,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 3, 1, 6,  Blocks.STONEBRICK.getDefaultState(), 1);
        setBlock(world, sbb, 3, 2, 0,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 3, 2, 6,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 3, 3, 0,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 3, 3, 6,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 3, 4, 0, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 3, 4, 1,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 3, 4, 2,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 3, 4, 3,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 3, 4, 4,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 3, 4, 5,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 3, 4, 6, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 4, 0, 0,  Blocks.STONEBRICK.getDefaultState(), 1);
        setBlock(world, sbb, 4, 0, 3,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 4, 0, 6,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 4, 1, 0,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 4, 1, 3,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 4, 1, 6,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 4, 2, 0,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 4, 2, 6,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 4, 3, 0,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 4, 3, 6,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 4, 4, 0, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 4, 4, 1,  Blocks.STONEBRICK.getDefaultState(), 1);
        setBlock(world, sbb, 4, 4, 2,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 4, 4, 3,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 4, 4, 4,  Blocks.STONEBRICK.getDefaultState(), 1);
        setBlock(world, sbb, 4, 4, 5,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 4, 4, 6, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 5, 0, 0,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 5, 0, 3,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 5, 0, 5, Blocks.CHEST.getDefaultState(), 4);
        setBlock(world, sbb, 5, 0, 6,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 5, 1, 0,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 5, 1, 3,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 5, 1, 6,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 5, 2, 0,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 5, 2, 6,  Blocks.STONEBRICK.getDefaultState(), 1);
        setBlock(world, sbb, 5, 3, 0,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 5, 3, 6,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 5, 4, 0, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 5, 4, 1,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 5, 4, 2,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 5, 4, 3,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 5, 4, 4,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 5, 4, 5,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 5, 4, 6, Blocks.LOG2.getDefaultState(), 5);
        setBlock(world, sbb, 6, 0, 0, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, 0, 1,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 6, 0, 2,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 6, 0, 3,  Blocks.STONEBRICK.getDefaultState(), 1);
        setBlock(world, sbb, 6, 0, 4,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 6, 0, 5,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 6, 0, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, 1, 0, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, 1, 1,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 6, 1, 2,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 6, 1, 3,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 6, 1, 4,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 6, 1, 5,  Blocks.STONEBRICK.getDefaultState(), 1);
        setBlock(world, sbb, 6, 1, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, 2, 0, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, 2, 1,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 6, 2, 2,  Blocks.STONEBRICK.getDefaultState(), 1);
        setBlock(world, sbb, 6, 2, 3,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 6, 2, 4,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 6, 2, 5,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 6, 2, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, 3, 0, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, 3, 1,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 6, 3, 2,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 6, 3, 3,  Blocks.STONEBRICK.getDefaultState(), 2);
        setBlock(world, sbb, 6, 3, 4,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 6, 3, 5,  Blocks.STONEBRICK.getDefaultState(), 0);
        setBlock(world, sbb, 6, 3, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, 4, 0, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 6, 4, 1, Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 6, 4, 2, Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 6, 4, 3, Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 6, 4, 4, Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 6, 4, 5, Blocks.LOG2.getDefaultState(), 9);
        setBlock(world, sbb, 6, 4, 6, Blocks.LOG2.getDefaultState(), 1);
        setBlock(world, sbb, 1, 0, 4, Blocks.REDSTONE_WIRE.getDefaultState(), 0);
        setBlock(world, sbb, 2, 0, 5, Blocks.REDSTONE_WIRE.getDefaultState(), 0);

        setBlock(world, sbb, 5, 1, 1, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.byMetadata(5)).withProperty(BlockStoneSlab.HALF, BlockSlab.EnumBlockHalf.TOP), 0);
        setBlock(world, sbb, 5, 1, 2, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.byMetadata(5)).withProperty(BlockStoneSlab.HALF, BlockSlab.EnumBlockHalf.TOP), 0);
        setBlock(world, sbb, 4, 1, 1, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.byMetadata(5)).withProperty(BlockStoneSlab.HALF, BlockSlab.EnumBlockHalf.TOP), 0);
        setBlock(world, sbb, 4, 1, 2, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.byMetadata(5)).withProperty(BlockStoneSlab.HALF, BlockSlab.EnumBlockHalf.TOP), 0);
        setBlock(world, sbb, 1, 0, 5, Blocks.SKULL.getDefaultState().withProperty(BlockSkull.FACING, EnumFacing.UP).withProperty(BlockSkull.NODROP, false), 1);

        setBlock(world, sbb, 0, 1, 3, Blocks.SPRUCE_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER).withProperty(BlockDoor.FACING, EnumFacing.EAST).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.LEFT), 0);
        setBlock(world, sbb, 0, 0, 3, Blocks.SPRUCE_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER).withProperty(BlockDoor.FACING, EnumFacing.EAST).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.LEFT), 0);

        setBlock(world, sbb, 5, 0, 1, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD).withProperty(BlockBed.FACING, EnumFacing.EAST), 0);
        setBlock(world, sbb, 5, 0, 2, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD).withProperty(BlockBed.FACING, EnumFacing.EAST), 0);
        setBlock(world, sbb, 4, 2, 1, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT).withProperty(BlockBed.FACING, EnumFacing.EAST), 0);
        setBlock(world, sbb, 4, 2, 2, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT).withProperty(BlockBed.FACING, EnumFacing.EAST), 0);
        setBlock(world, sbb, 5, 2, 1, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD).withProperty(BlockBed.FACING, EnumFacing.EAST), 0);
        setBlock(world, sbb, 5, 2, 2, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD).withProperty(BlockBed.FACING, EnumFacing.EAST), 0);
        setBlock(world, sbb, 4, 0, 1, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT).withProperty(BlockBed.FACING, EnumFacing.EAST), 0);
        setBlock(world, sbb, 4, 0, 2, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT).withProperty(BlockBed.FACING, EnumFacing.EAST), 0);

        setBlock(world, sbb, 2, 2, 1, Blocks.REDSTONE_TORCH.getDefaultState().withProperty(BlockRedstoneTorch.FACING, EnumFacing.NORTH), 2);
        setBlock(world, sbb, 4, 1, 5, Blocks.REDSTONE_TORCH.getDefaultState().withProperty(BlockRedstoneTorch.FACING, EnumFacing.SOUTH), 2);

        setBlock(world, sbb, 5, 0, 4, Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.NORTH), 0);
        setBlock(world, sbb, 5, 1, 4, Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.NORTH), 0);
        fillWithBlocks(world, sbb, 0, -1, 0, 5, -1, 5, Blocks.STONEBRICK);

        EntityBandit bandit1 = new EntityBandit(world);
        EntityBandit bandit2 = new EntityBandit(world);

        bandit1.setLocationAndAngles(sbb.maxX - 10, sbb.minY + 5, sbb.maxZ - 10, 0F, 0F);
        bandit2.setLocationAndAngles(sbb.maxX - 10, sbb.minY + 5, sbb.maxZ - 10, 0F, 0F);

        world.spawnEntity(bandit1);
        world.spawnEntity(bandit2);


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
/**
            setBlockState(world, Blocks.CHEST.getStateFromMeta(metadata), x, y, z, sbb);
            if(metadata == 3){
                setBlockState(world, Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.NORTH), x, y, z, sbb);
            }
            if(metadata == 4){
                setBlockState(world, Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.WEST), x, y, z, sbb);
            }**/
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

        else if(s == Blocks.STONEBRICK.getDefaultState()){
            Random r = new Random();
            int n = r.nextInt(100) + 1;
            BlockStoneBrick.EnumType type;
            if(n <= 20){
                type = BlockStoneBrick.EnumType.CRACKED;
            }
            else if(n >= 20 && n <= 40){
                type = BlockStoneBrick.EnumType.MOSSY;
            }
            else{
                type = BlockStoneBrick.EnumType.DEFAULT;
            }
            setBlockState(world, Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, type), x, y, z, sbb);

        }

        else{
            setBlockState(world, s, x, y, z, sbb);
        }

    }


}
