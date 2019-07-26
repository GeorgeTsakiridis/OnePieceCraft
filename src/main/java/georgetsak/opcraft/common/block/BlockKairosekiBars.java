package georgetsak.opcraft.common.block;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.capability.devilfruits.DevilFruitsCapProvider;
import georgetsak.opcraft.common.capability.devilfruits.IDevilFruitsCap;
import net.minecraft.block.BlockPane;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by GeorgeTsak on 8/7/2017.
 */

public class BlockKairosekiBars extends BlockPane{

    public BlockKairosekiBars() {
        super(Material.IRON, true);
        this.setResistance(60);
        this.setHardness(8);
        this.setHarvestLevel("pickaxe", 2);
        setSoundType(SoundType.METAL);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        applyEffects(entityIn);
        super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        applyEffects(entityIn);
        super.onEntityWalk(worldIn, pos, entityIn);
    }

    private void applyEffects(Entity entityIn){
        if (entityIn instanceof EntityPlayer) {
            if(!OPCraft.config.doesSeaStoneAffectDevilFruitUsers.getCurrentValue() || ((EntityPlayer) entityIn).isCreative())return;
            IDevilFruitsCap df = entityIn.getCapability(DevilFruitsCapProvider.DF_CAP, null);
            if(df.hasPower()) {
                ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20, 3));
                ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 20, 3));
                ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 20, 3));
            }
        }
    }


}
