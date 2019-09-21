package georgetsak.opcraft.common.block;

import georgetsak.opcraft.common.dimension.mirror.MirrorWorldProvider;
import net.minecraft.block.BlockEmptyDrops;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.awt.*;

public class MirrorWallBlock extends BlockEmptyDrops implements IBlockColor {

    public MirrorWallBlock() {
        super(Material.ROCK);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return super.canPlaceBlockAt(worldIn, pos) && worldIn.provider instanceof MirrorWorldProvider;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
        //x: +-100
        //z: +-100
        if(pos == null)return Color.MAGENTA.getRGB();

        int[] colors = new int[200];
        float start = 0.66f;
        float end = 1f;
        for(int i = 0; i < 200; i++){
            colors[i] = Color.HSBtoRGB(end - (i/200f)*(end-start), 0.9f,0.7f);
        }

        int index = pos.getX()*2 + 200;

        if(index > 200)index = 400 - index;

        int colorA = colors[MathHelper.clamp(index, 0, 199)];
        int colorB = new Color(colorA).darker().getRGB();

        boolean flag1 = pos.getX() % 2 == 0;
        boolean flag2 = pos.getY() % 2 == 0;
        boolean flag3 = pos.getZ() % 2 == 0;

        if(flag1 && flag3)return flag2 ? colorA : colorB;
        if(flag1)return flag2 ? colorB : colorA;
        if(flag3)return flag2 ? colorB : colorA;
        else return flag2 ? colorA : colorB;

    }
}
