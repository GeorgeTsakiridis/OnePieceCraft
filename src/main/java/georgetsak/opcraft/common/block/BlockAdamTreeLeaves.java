package georgetsak.opcraft.common.block;

import georgetsak.opcraft.common.registry.OPBlocks;
import georgetsak.opcraft.common.registry.OPItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockAdamTreeLeaves extends Block implements IShearable{
   
    public BlockAdamTreeLeaves(Material mat, boolean decayable){
	super(mat);
	this.setTickRandomly(decayable);
	this.setHardness(0.4F); // Default 3.0F
	this.setResistance(4); // Default 2F
	this.setLightOpacity(2);
	this.setSoundType(SoundType.PLANT);

    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand){
    	boolean shouldDecay = true;
    	
    	for(int x = -6; x < 6; x++){
    		for(int y = -6; y < 6; y++){
    			for(int z = -6; z < 6; z++){
    				if(worldIn.getBlockState(pos.add(x, y, z)).getBlock() == OPBlocks.BlockAdamTreeWood){
    					shouldDecay = false;
    				}
    			}
    		}
    	}
    	if(shouldDecay){
    		
            worldIn.setBlockToAir(pos);
    	}
    	
    }

    
    
	@Override
	 public int quantityDropped(Random random)
	    {
	        return 0;
	    }
    
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }
	
	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }
	
    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return Minecraft.isFancyGraphicsEnabled() ? BlockRenderLayer.CUTOUT_MIPPED : BlockRenderLayer.SOLID;
    }

    public boolean isVisuallyOpaque()
    {
        return false;
    }
	
    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return true;
    }

	@SideOnly(Side.CLIENT)
	@Override
    public boolean isOpaqueCube(IBlockState blockState)
    {
        return !Minecraft.isFancyGraphicsEnabled();
    }

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		list.add(new ItemStack(OPItems.ItemAdamTreeLeavesNonDecayable, 1));
		return list;
	}
    
}