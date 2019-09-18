package georgetsak.opcraft.common.generator.terrain;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import static georgetsak.opcraft.common.util.WorldGenUtils.getGroundFromAbove;

public class WorldGenCherryTree extends WorldGenerator
{
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos) {
		int y = pos.getY() + 2;
		BlockPos bushPos = new BlockPos(pos.getX(), y, pos.getZ());
		Block toReplace = worldIn.getBlockState(bushPos).getBlock();

		if (toReplace == Blocks.AIR || toReplace.getMaterial(toReplace.getBlockState().getBaseState()) == Material.PLANTS) {
			GenCherryTree worldgenerator = new GenCherryTree(false, false);
			worldIn.setBlockToAir(pos);
			worldgenerator.generate(worldIn, rand, pos);
		}

		return false;
	}

}