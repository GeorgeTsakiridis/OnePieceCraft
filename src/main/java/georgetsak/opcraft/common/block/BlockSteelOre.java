package georgetsak.opcraft.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockSteelOre extends Block {

	public BlockSteelOre(Material materialIn) {
		super(materialIn);
		this.setResistance(15);
		this.setHardness(3);
		this.setHarvestLevel("pickaxe", 2);
		this.setSoundType(SoundType.STONE);
	}

	@Override
	 public int quantityDropped(Random random){
	        return 1;
	    }
	 
	@Override
	 public Item getItemDropped(IBlockState state, Random rand, int fortune){
	        return Item.getItemFromBlock(this);
	        
	        		}
	
}
