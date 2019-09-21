package georgetsak.opcraft.common.item.weapons;

import georgetsak.opcraft.common.registry.OPItems;
import georgetsak.opcraft.client.OPSoundEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGun extends Item implements IExtendedReach {

	private int damage, range, delay, type;

	public ItemGun(int durability, int damage, int range, int delay, int type){
		this.setMaxDamage(durability);
		this.damage = damage;
		this.range = range;
		this.delay = delay;
		this.type = type;
		this.setMaxStackSize(1);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer playerIn, EnumHand hand)
	{
        ItemStack is = findAmmo(playerIn);
		boolean flag1 = !(type==1 && playerIn.inventory.hasItemStack(new ItemStack(OPItems.FLINTLOCK_AMMO)));
		boolean flag2 = !(type==2 && playerIn.inventory.hasItemStack(new ItemStack(OPItems.SENRIKU_AMMO)));
		boolean flag3 = !(type==3 && playerIn.inventory.hasItemStack(new ItemStack(OPItems.BAZOOKA_AMMO)));

		if(flag1 && flag2 && flag3){
			world.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, OPSoundEvent.gun_empty, SoundCategory.NEUTRAL, 2, 1);
		}
		else {
			if (is != null) {
				is.setCount(is.getCount() - 1);
				if (is.getCount() == 0) playerIn.inventory.deleteStack(is);

				if (type == 1)
					world.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, OPSoundEvent.flintlock_fire, SoundCategory.NEUTRAL, 30, 1);
				else if (type == 2)
					world.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, OPSoundEvent.senriku_fire, SoundCategory.NEUTRAL, 10, 1);
				else if (type == 3)
					world.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, OPSoundEvent.bazooka_fire, SoundCategory.NEUTRAL, 100, 1);
			}
		}
        playerIn.getCooldownTracker().setCooldown(this, delay);
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(hand));
	}

	 private ItemStack findAmmo(EntityPlayer player)
	    {
	        if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND)))
	        {
	            return player.getHeldItem(EnumHand.OFF_HAND);
	        }
	        else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND)))
	        {
	            return player.getHeldItem(EnumHand.MAIN_HAND);
	        }
	        else
	        {
	            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
	            {
	                ItemStack itemstack = player.inventory.getStackInSlot(i);

	                if (this.isArrow(itemstack))
	                {
	                    return itemstack;
	                }
	            }

	            return null;
	        }
	    }

	 protected boolean isArrow(ItemStack stack)
	    {
		 if(type == 1){
		        return stack != null && stack.getItem().getRegistryName() == OPItems.FLINTLOCK_AMMO.getRegistryName();
		 }
		 if(type == 2){
		        return stack != null && stack.getItem().getRegistryName() == OPItems.SENRIKU_AMMO.getRegistryName();
		 }
		 if(type == 3){
		        return stack != null && stack.getItem().getRegistryName() == OPItems.BAZOOKA_AMMO.getRegistryName();
		 }
		 return false;
	    }

    @Override
    public float getReach() {
        return range;
    }

    @Override
	public float getDamage(){return damage;}

    @Override
    public int getType(){return type;}

    @Override
    public Item getItem(){return this;}
}