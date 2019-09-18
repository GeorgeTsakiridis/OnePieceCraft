package georgetsak.opcraft.common.block;

import georgetsak.opcraft.common.entity.other.EntitySkypiean;
import georgetsak.opcraft.common.registry.OPBlocks;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockCloud extends Block {

    private boolean canFallThrough;

    public BlockCloud(boolean canFallThrough) {
        super(Material.CLOTH, MapColor.AIR);
        this.setResistance(3);
        this.setHardness(0.5f);
        this.setLightLevel(0.75f);
        this.setTickRandomly(true);
        setSoundType(SoundType.CLOTH);
        this.canFallThrough = canFallThrough;
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
        if(worldIn.getGameRules().getBoolean("doMobSpawning")  && this == OPBlocks.BlockDenseCloud && random.nextInt(300) == 0) {
            int totalSkypieansInArea = OPUtils.getNearbyEntities(worldIn, pos, 50, EntitySkypiean.class).size();
            int totalSkypieansNearby = OPUtils.getNearbyEntities(worldIn, pos, 10, EntitySkypiean.class).size();
            int totalSkypieansOnBlock = OPUtils.getNearbyEntities(worldIn,pos.up(),1,EntitySkypiean.class).size();

            Block up1Block = worldIn.getBlockState(pos.up(1)).getBlock();
            Block up2Block = worldIn.getBlockState(pos.up(2)).getBlock();

            boolean flag1 = up1Block == Blocks.AIR || up1Block == Blocks.SNOW_LAYER;
            boolean flag2 = up2Block == Blocks.AIR || up1Block == Blocks.SNOW_LAYER;


            if (pos.getY() > 150 && totalSkypieansNearby < 3 && flag1 && flag2 && totalSkypieansOnBlock < 1 && totalSkypieansInArea < 20) {
                worldIn.spawnEntity(new EntitySkypiean(worldIn, pos.up()));
            }
        }
        super.randomTick(worldIn, pos, state, random);
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    @Override
    public boolean canSpawnInBlock() {
        return false;
    }

    @Override
    public boolean canEntitySpawn(IBlockState state, Entity entityIn) {
        return false;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return super.getBlockFaceShape(worldIn, state, pos, face);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return canFallThrough ? NULL_AABB : blockState.getBoundingBox(worldIn, pos);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();

        if (blockState != iblockstate)
        {
            return true;
        }

        return block != this;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 1;
    }


}
