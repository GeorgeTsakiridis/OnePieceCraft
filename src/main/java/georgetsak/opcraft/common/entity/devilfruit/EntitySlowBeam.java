package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class EntitySlowBeam extends EntityFlying {

	double x, y, z, startY;
	float yaw, pitch;
	boolean repeat = false;
	boolean randomly = false;
	boolean highSpeed = false;
	Vec3d direction = new Vec3d(0, 0, 0);
	EntityPlayer ep;

	public EntitySlowBeam(World world) {
		super(world);
		this.setSize(1F, 0.5F);
	}

	public EntitySlowBeam(World worldIn, double x, double y, double z, float yaw, float pitch, EntityPlayer owner) {
		this(worldIn);

		this.ep = owner;
		this.x = x;
		this.y = this.startY = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
		this.direction = OPUtils.convertRotation(yaw, pitch);
		direction.scale(4F);
		this.setPositionAndRotation(x, y, z, yaw, pitch);
		this.motionX = direction.x;
		this.motionY = direction.y;
		this.motionZ = direction.z;
		this.setSize(1F, 1F);
	}

	public EntitySlowBeam(World worldIn, double x, double y, double z, float yaw, float pitch, EntityPlayer owner, boolean highSpeed) {
		this(worldIn, x, y, z, yaw, pitch, owner);
		this.highSpeed = highSpeed;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (this.collided) {
			this.setDead();
		}

		if (this.movedDistance <= 45 && Math.abs(this.startY - this.posY) <= 45) {
			this.motionX = direction.x;
			this.motionY = direction.y;
			this.motionZ = direction.z;
			if (highSpeed) {
				this.motionX = direction.x * 3;
				this.motionY = direction.y * 3;
				this.motionZ = direction.z * 3;

			}
		}
	}

	public void onCollideWithPlayer(EntityPlayer entityIn) {
		if (ep != entityIn && ep != null && entityIn.hurtResistantTime == 0) {
			entityIn.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 300, 9));
			entityIn.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 300, 9));
			entityIn.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 300, 9));
			entityIn.sendMessage(new TextComponentString("You were hit by" + TextFormatting.LIGHT_PURPLE + " Slow Slow Beam" + TextFormatting.RESET + "."));
			entityIn.hurtResistantTime = 20;

		}
	}

	public void onUpdate() {
		super.onUpdate();
		if (this.ticksExisted >= 80) {
			this.setDead();
		}
	}

	public void collideWithEntity(Entity entityIn) {
		if (entityIn instanceof EntityLiving) {
			EntityLiving e = (EntityLiving) entityIn;
			e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200, 10));
			e.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 200, 10));
			e.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 200, 10));
		}
	}

	@Override
	public void applyEntityCollision(Entity entityIn) {

	}

	//@#@#@#@#@#@#@#@#@#@#@##@#@#@#@
	@Override
	public float getCollisionBorderSize() {
		return 2.0F;
	}

	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		return true;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}


}
