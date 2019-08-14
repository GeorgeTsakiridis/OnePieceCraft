package georgetsak.opcraft.common.item.weapons.swords;

import georgetsak.opcraft.common.capability.devilfruits.DevilFruitCap;
import georgetsak.opcraft.common.capability.devilfruits.IDevilFruitCap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class ItemSmokerJitte extends ItemSimpleSword{

    public ItemSmokerJitte(int durability, float attackDamage) {
        super(durability, attackDamage);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {

        if(target instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer)target;
            IDevilFruitCap devilFruitsCap = DevilFruitCap.get(player);
            if(devilFruitsCap.hasPower()){
                player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100));
                player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 1));
            }
        }

        return super.hitEntity(stack, target, attacker);
    }
}
