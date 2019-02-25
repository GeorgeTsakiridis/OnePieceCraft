package georgetsak.opcraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockCherryTreePlanks extends Block {

	public BlockCherryTreePlanks(Material materialIn) {
		super(materialIn);
		this.setHardness(2);
		this.setResistance(15);
		this.setSoundType(SoundType.WOOD);
	}

	@Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return getFlammability(world, pos, face) > 0;
    }

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 200;
    }
	
}
