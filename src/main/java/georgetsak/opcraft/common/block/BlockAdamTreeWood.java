package georgetsak.opcraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockAdamTreeWood extends Block {

	public BlockAdamTreeWood(Material materialIn) {
		super(materialIn);
		this.setResistance(30);
		this.setHardness(5);
		this.setSoundType(SoundType.WOOD);
		this.setHarvestLevel("axe", 3, getDefaultState());
	}


}
