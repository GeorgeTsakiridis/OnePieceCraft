package georgetsak.opcraft.common.item;

import georgetsak.opcraft.common.crew.CrewSaveData;
import georgetsak.opcraft.common.crew.EnumRole;
import georgetsak.opcraft.common.crew.Member;
import georgetsak.opcraft.common.util.CrewUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class ItemHeal extends Item {

    int healAmount;

    public ItemHeal(int healAmount) {
        this.healAmount = healAmount;
    }


    //TODO Players should be able to use the item to himself not only on others.
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {

        if (playerIn.world.isRemote) {
            return false;
        }

        if (target instanceof EntityPlayer) {
            CrewSaveData saveData = CrewSaveData.get(playerIn.world);
            EntityPlayer player = (EntityPlayer) target;
            Member member = CrewUtils.getMemberFromPlayer(saveData.getCrews(), playerIn);
            if (member != null && member.getRole() == EnumRole.DOCTOR) {
                player.heal(healAmount * 2);
            } else {
                player.heal(healAmount);
            }
            stack.setCount(stack.getCount() - 1);
            return true;
        }
        return false;
    }
}
