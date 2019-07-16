package georgetsak.opcraft.common.block;

import georgetsak.opcraft.common.block.tile.SmokeCloudTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSmokeCloud extends BlockTransparentIndestructableBlock{

    public BlockSmokeCloud(Material material) {
        super(material);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new SmokeCloudTileEntity();
    }
}
