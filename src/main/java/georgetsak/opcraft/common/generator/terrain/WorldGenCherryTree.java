package georgetsak.opcraft.common.generator.terrain;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCherryTree extends WorldGenerator
{
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos) 
	{
		int y = 1 + getGroundFromAbove(worldIn, pos.getX(), pos.getZ());
		if(y >= pos.getY())
		{
			BlockPos bushPos = new BlockPos(pos.getX(), y, pos.getZ());
			Block toReplace = worldIn.getBlockState(bushPos).getBlock();

			if(toReplace == Blocks.AIR || toReplace.getMaterial(toReplace.getBlockState().getBaseState()) == Material.PLANTS)
			{
				GenCherryTree worldgenerator = new GenCherryTree(false, false);
            	worldIn.setBlockToAir(pos);
            	worldgenerator.generate(worldIn, rand, pos);
			}  
		}
		return false;
	}

	public static int getGroundFromAbove(World world, int x, int z)
	{
		int y = 255;
		boolean foundGround = false;
		while(!foundGround && y-- >= 0)
		{
			Block blockAt = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			foundGround = blockAt == Blocks.DIRT || blockAt == Blocks.GRASS;
		}

		return y;
	}
}