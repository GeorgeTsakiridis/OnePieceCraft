package georgetsak.opcraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockSteel extends Block {

//type 1: Steel, type 2: Dark Steel	
	public BlockSteel(Material materialIn, int type) {
		super(materialIn);
		if(type == 1){
			this.setHardness(6);
			this.setResistance(45);
		}
		else{
			this.setHardness(10);
			this.setResistance(90);
		}
		this.setHarvestLevel("pickaxe", 2);
		this.setSoundType(SoundType.METAL);
	}

}
