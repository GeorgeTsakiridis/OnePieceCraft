package georgetsak.opcraft.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.UUID;

public class ItemLawHeart extends Item {

    public ItemLawHeart(){
        this.setMaxDamage(9); //10 Uses
    }

    public EntityPlayer getOwner(World world, ItemStack stack){
        if(stack.getTagCompound() != null){
            return world.getPlayerEntityByUUID(UUID.fromString(stack.getTagCompound().getString("owner")));
        }
        return null;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        if(stack.getTagCompound() != null){
            return stack.getTagCompound().getString("ownerDisplayName") + "'s Heart";
        }
        return "Heart";
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack itemStack = playerIn.getHeldItem(hand);
        EntityPlayer owner = getOwner(playerIn.world, itemStack);
        if(!playerIn.world.isRemote) {
            if (owner != null) {
                ItemStack itemStack2 = itemStack;
                itemStack2.damageItem(1, playerIn);

                owner.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 80));
                owner.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 80, 3));
                owner.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 80, 3));
                owner.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 6);


            } else {
                playerIn.sendMessage(new TextComponentString("The owner of this heart was not found!"));
            }
        }

        return super.onItemRightClick(worldIn, playerIn, hand);
    }
}
