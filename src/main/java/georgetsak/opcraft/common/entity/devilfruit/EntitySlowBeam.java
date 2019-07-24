package georgetsak.opcraft.common.entity.devilfruit;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class EntitySlowBeam extends EntitySimpleProjectile {

	boolean highSpeed = false;

	public EntitySlowBeam(World world) {
		super(world);
		setSize(1f, 1f);
	}

	public EntitySlowBeam(World worldIn, double x, double y, double z, float yaw, float pitch, EntityPlayer owner) {
		super(worldIn, x, y, z, yaw, pitch, 1f, 1f, owner);
	}

	public EntitySlowBeam(World worldIn, double x, double y, double z, float yaw, float pitch, EntityPlayer owner, boolean highSpeed) {
		super(worldIn, x, y, z, yaw, pitch, 1f, 1f, owner);
		this.highSpeed = highSpeed;
	}

	@Override
	public int getMaxTicks() {
		return 80;
	}

	@Override
	public double getMaxDistance() {
		return 45;
	}

	@Override
	public float getSpeedMultiplier() {
		return highSpeed ? 1.5f : 0.8f;
	}

	public void onCollideWithPlayer(EntityPlayer entityIn) {
		if (!isCollisionWithPlayerValid(entityIn)) return;

		entityIn.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 300, 9));
		entityIn.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 300, 9));
		entityIn.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 300, 9));
		entityIn.sendMessage(new TextComponentString("You were hit by" + TextFormatting.LIGHT_PURPLE + " Slow Slow Beam" + TextFormatting.RESET + "."));
		entityIn.hurtResistantTime = 20;

	}

	public void collideWithEntity(Entity entityIn) {
		if (!isCollisionWithEntityValid(entityIn)) return;

		EntityLiving e = (EntityLiving) entityIn;
		e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200, 10));
		e.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 200, 10));
		e.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 200, 10));
	}

	@Override
	public float getCollisionBorderSize() {
		return 2.0F;
	}

}
