package georgetsak.opcraft.common.block;

import georgetsak.opcraft.common.block.tile.LawDomeTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLawDomeCenter extends BlockTransparentIndestructableBlock {

    BlockPos pos;

    public BlockLawDomeCenter(Material material) {
        super(material);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        super.onBlockAdded(worldIn, pos, state);
        GenerateDome gd = new GenerateDome();
        gd.generate(worldIn, pos.getX() - 25, pos.getY() - 15, pos.getZ() - 20, false);
        this.pos = pos;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new LawDomeTileEntity();
    }
}