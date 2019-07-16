package georgetsak.opcraft.common.item.weapons;

import georgetsak.opcraft.common.entity.other.EntityKabutoAmmo;
import georgetsak.opcraft.common.item.weapons.ammo.ItemUssopKabutoAmmo;
import georgetsak.opcraft.common.registry.OPItems;
import georgetsak.opcraft.client.OPSoundEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class ItemUssopKabuto extends ItemBow {
	public ItemUssopKabuto(int type) {//0 is Green, 1 is Black
		super();
		if(type == 0){
			this.setMaxDamage(500);
		}
		else if(type == 1){
			this.setMaxDamage(750);
		}
	}

	protected boolean isAmmoRequired(ItemStack bow, EntityPlayer shooter) {
		return !shooter.capabilities.isCreativeMode && EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, bow) == 0;
	}

	protected ActionResult<ItemStack> nockArrow(ItemStack bow, World world, EntityPlayer shooter, EnumHand hand) {
		boolean hasAmmo = findAmmo(shooter) != null;

		final ActionResult<ItemStack> ret = ForgeEventFactory.onArrowNock(bow, world, shooter, hand, hasAmmo);
		if (ret != null) return ret;

		if (isAmmoRequired(bow, shooter) && !hasAmmo) {
			return new ActionResult<>(EnumActionResult.FAIL, bow);
		} else {
			shooter.setActiveHand(hand);
			return new ActionResult<>(EnumActionResult.SUCCESS, bow);
		}
	}

	 protected boolean isArrow(ItemStack stack)
	    {
	        return stack != null && stack.getItem() instanceof ItemUssopKabutoAmmo;
	    }
	
	protected void fireArrow(ItemStack bow, World world, EntityLivingBase shooter, int charge) {
		if (!(shooter instanceof EntityPlayer)) return;

		final EntityPlayer player = (EntityPlayer) shooter;
		final boolean ammoRequired = isAmmoRequired(bow, player);
		ItemStack itemStack = findAmmo(player);

		charge = ForgeEventFactory.onArrowLoose(bow, world, player, charge, itemStack != null || !ammoRequired);
		if (charge < 0) return;

		if (itemStack != null || !ammoRequired) {

			final float arrowVelocity = getArrowVelocity(charge);

			if (arrowVelocity >= 0.1) {

				if (!world.isRemote) {
					final ItemUssopKabutoAmmo ItemUssopKabutoAmmo = new ItemUssopKabutoAmmo();

					final EntityKabutoAmmo EntityKabbutoAmmo = ItemUssopKabutoAmmo.createArrow(world, itemStack, player);
					EntityKabbutoAmmo.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, arrowVelocity * 3.0F, 1.0F);

					if (arrowVelocity == 1.0f) {
						EntityKabbutoAmmo.setIsCritical(true);
					}

					final int powerLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, bow);
					if (powerLevel > 0) {
						EntityKabbutoAmmo.setDamage(EntityKabbutoAmmo.getDamage() + (double) powerLevel * 0.5D + 0.5D);
					}

					final int punchLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, bow);
					if (punchLevel > 0) {
						EntityKabbutoAmmo.setKnockbackStrength(punchLevel);
					}

					if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, bow) > 0) {
						EntityKabbutoAmmo.setFire(100);
					}
					if(ammoRequired) {
						itemStack.setCount(itemStack.getCount() - 1);
						if (itemStack.getCount() == 0) player.inventory.deleteStack(itemStack);
					}
					bow.damageItem(1, player);

					world.spawnEntity(EntityKabbutoAmmo);
				}
				world.playSound(null, player.posX, player.posY, player.posZ, OPSoundEvent.kabuto_release, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + arrowVelocity * 0.5F);

			}
		}
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


	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		final int charge = this.getMaxItemUseDuration(stack) - timeLeft;
		fireArrow(stack, worldIn, entityLiving, charge);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
        ItemStack itemStack = player.getHeldItem(hand);
        if(findAmmo(player) != null) {
            worldIn.playSound(null, player.posX, player.posY, player.posZ, OPSoundEvent.kabuto_load, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) * 0.5F);
            return nockArrow(itemStack, worldIn, player, hand);
        }
        return new ActionResult<>(EnumActionResult.FAIL, itemStack);
	}
}