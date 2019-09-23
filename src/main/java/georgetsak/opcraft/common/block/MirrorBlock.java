package georgetsak.opcraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class MirrorBlock extends BlockConnectedTexture {

    public MirrorBlock(){
        super(Material.GLASS);
    }

}
