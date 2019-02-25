package georgetsak.opcraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockTemporaryIce extends Block
{

    boolean createWater;
    int changeOfBeingDestroyed;

    public BlockTemporaryIce(Material material, float hardness, float resistance, boolean createWater, int changeOfBeingDestroyed)
    {
    	super(material);
    	this.slipperiness = 0.98F;
        this.setTickRandomly(true);
        this.setResistance(resistance);
        this.setHardness(hardness);
        this.setSoundType(SoundType.GLASS);
        this.createWater = createWater;
        this.changeOfBeingDestroyed = changeOfBeingDestroyed;
    }

    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player){
    	return false;
    }
    
    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {
        if (createWater) {
            worldIn.setBlockState(pos, Blocks.WATER.getDefaultState());
        }
    }
    
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack)
    {
        if(createWater) worldIn.setBlockState(pos, Blocks.WATER.getDefaultState());
    }

    public int quantityDropped(Random random)
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean isOpaqueCube(IBlockState blockState)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return true;
    }


    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if(changeOfBeingDestroyed != 0 && rand.nextInt(changeOfBeingDestroyed) == 0) {
            if (createWater) {
                worldIn.setBlockState(pos, Blocks.WATER.getDefaultState());
            } else {
                worldIn.destroyBlock(pos, false);
            }
        }
    }

}