package georgetsak.opcraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockDarkSteelOre extends Block {

	public BlockDarkSteelOre(Material materialIn) {
		super(materialIn);
		this.setResistance(15);
		this.setHardness(3);
		this.setHarvestLevel("pickaxe", 2);
		this.setSoundType(SoundType.STONE);
	}

}
