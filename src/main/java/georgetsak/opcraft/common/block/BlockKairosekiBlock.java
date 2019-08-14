package georgetsak.opcraft.common.block;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.capability.devilfruits.DevilFruitCapProvider;
import georgetsak.opcraft.common.capability.devilfruits.IDevilFruitCap;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by GeorgeTsak on 8/7/2017.
 */
public class BlockKairosekiBlock extends Block {

    public BlockKairosekiBlock() {
        super(Material.IRON);
        this.setResistance(90);
        this.setHardness(10);
        this.setHarvestLevel("pickaxe", 2);
        this.setSoundType(SoundType.METAL);
    }

    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof EntityPlayer) {
            if(!OPCraft.config.doesSeaStoneAffectDevilFruitUsers.getCurrentValue() || ((EntityPlayer) entityIn).isCreative())return;
            IDevilFruitCap df = entityIn.getCapability(DevilFruitCapProvider.DF_CAP, null);
            if(df.hasPower()) {
                ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20, 3));
                ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 20, 3));
                ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 20, 3));
            }
        }
        super.onEntityWalk(worldIn, pos, entityIn);
    }

}
