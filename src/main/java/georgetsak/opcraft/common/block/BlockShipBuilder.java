package georgetsak.opcraft.common.block;

import georgetsak.opcraft.OPCraft;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by GeorgeTsak on 7/31/2017.
 */
public class BlockShipBuilder extends Block {

    public BlockShipBuilder() {
        super(Material.WOOD);
        setSoundType(SoundType.WOOD);
        setHardness(2.5F);
        setResistance(5.0F);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!playerIn.isSneaking()){
            playerIn.openGui(OPCraft.MODID, 2, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }else{
            return false;
        }
    }

}
