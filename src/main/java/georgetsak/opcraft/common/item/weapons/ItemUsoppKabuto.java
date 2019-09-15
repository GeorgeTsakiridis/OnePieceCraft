package georgetsak.opcraft.common.item.weapons;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.OPSoundEvent;
import georgetsak.opcraft.client.gui.ContainerKabuto;
import georgetsak.opcraft.common.capability.kabuto.KabutoCap;
import georgetsak.opcraft.common.capability.kabuto.KabutoCapProvider;
import georgetsak.opcraft.common.entity.other.EntityKabutoAmmo;
import georgetsak.opcraft.common.registry.OPItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;
import java.util.List;

public class ItemUsoppKabuto extends ItemBow {

	public ItemUsoppKabuto(int type) {//0 is Green, 1 is Black
		super();
		if(type == 0){
			this.setMaxDamage(500);
		}
		else if(type == 1){
			this.setMaxDamage(750);
		}
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
		System.out.println("DROPPED");
		return super.onDroppedByPlayer(item, player);
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
		return new KabutoCapProvider();
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Right click while sneaking to load.");
	}

	protected boolean isAmmoRequired(EntityPlayer shooter) {
		return !shooter.capabilities.isCreativeMode;
	}

	protected ActionResult<ItemStack> nockArrow(ItemStack bow, World world, EntityPlayer shooter, EnumHand hand) {
		boolean hasAmmo = KabutoCap.get(bow).getItemStackStored() != ItemStack.EMPTY;

		final ActionResult<ItemStack> ret = ForgeEventFactory.onArrowNock(bow, world, shooter, hand, hasAmmo);
		if (ret != null) return ret;

		if (isAmmoRequired(shooter) && !hasAmmo) {
			return new ActionResult<>(EnumActionResult.FAIL, bow);
		} else {
			shooter.setActiveHand(hand);
			return new ActionResult<>(EnumActionResult.SUCCESS, bow);
		}
	}

	 protected boolean isArrow(ItemStack stack)
	    {
	        return stack != null && stack.getItem() == OPItems.ItemSmallRock;
	    }
	
	protected void fireArrow(ItemStack bow, World world, EntityLivingBase shooter, int charge) {
		if (!(shooter instanceof EntityPlayer)) return;

		final EntityPlayer player = (EntityPlayer) shooter;
		final boolean ammoRequired = isAmmoRequired(player);

		ItemStack ammo = KabutoCap.get(bow).getItemStackStored();

		charge = ForgeEventFactory.onArrowLoose(bow, world, player, charge, !ammoRequired);
		if (charge < 0) return;

		if (ammo != null && ammo != ItemStack.EMPTY || !ammoRequired) {

			final float arrowVelocity = getArrowVelocity(charge);

			if (arrowVelocity >= 0.1) {

				if (!world.isRemote) {

					final EntityKabutoAmmo entityKabutoAmmo = new EntityKabutoAmmo(world, shooter, ammo);
					entityKabutoAmmo.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, arrowVelocity * 3.0F, 1.0F);

					if (arrowVelocity == 1.0f) {
						entityKabutoAmmo.setIsCritical(true);
					}

					final int powerLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, bow);
					if (powerLevel > 0) {
						entityKabutoAmmo.setDamage(entityKabutoAmmo.getDamage() + (double) powerLevel * 0.5D + 0.5D);
					}

					final int punchLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, bow);
					if (punchLevel > 0) {
						entityKabutoAmmo.setKnockbackStrength(punchLevel);
					}

					if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, bow) > 0) {
						entityKabutoAmmo.setFire(100);
					}
					if(ammoRequired) {
						ammo.setCount(ammo.getCount() - 1);
						if (ammo.getCount() == 0) KabutoCap.get(bow).setItemStack(ItemStack.EMPTY);
					}
					bow.damageItem(1, player);

					world.spawnEntity(entityKabutoAmmo);
				}
				world.playSound(null, player.posX, player.posY, player.posZ, OPSoundEvent.kabuto_release, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + arrowVelocity * 0.5F);

			}
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

		if (hand.equals(EnumHand.MAIN_HAND)) {
			if (player.isSneaking()) {
				player.openGui(OPCraft.MODID, 7, worldIn, 0, 0, 0);
				return new ActionResult<>(EnumActionResult.FAIL, itemStack);
			}

			if (itemStack != ItemStack.EMPTY && KabutoCap.get(itemStack).getItemStackStored() != ItemStack.EMPTY) {
				worldIn.playSound(null, player.posX, player.posY, player.posZ, OPSoundEvent.kabuto_load, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) * 0.5F);
				return nockArrow(itemStack, worldIn, player, hand);
			}
		}
		return new ActionResult<>(EnumActionResult.FAIL, itemStack);
	}
}