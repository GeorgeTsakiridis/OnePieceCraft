package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.capability.devilfruitlevels.DevilFruitLevelsCap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class EntitySlowBeam extends EntitySimpleProjectile {

	private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntitySlowBeam.class, DataSerializers.VARINT);

	public EntitySlowBeam(World world) {
		super(world);
		setSize(1f, 1f);
		this.getDataManager().register(TYPE, 0);
	}

	//Type: 1 -> Slow Beam, 2 -> High Rotation Mirror Ball, 3 -> Mashi Mashi
	public EntitySlowBeam(World worldIn, int type, double x, double y, double z, float yaw, float pitch, EntityPlayer owner) {
		super(worldIn, x, y, z, yaw, pitch, 1f, 1f, owner);
		this.getDataManager().register(TYPE, type);
		this.getDataManager().setDirty(TYPE);

	}

	public int getType(){
		return this.getDataManager().get(TYPE);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		dataManager.set(TYPE,compound.getInteger("BeamType"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("BeamType",getType());
		return super.writeToNBT(compound);
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
	public float getSpeedMultiplier(){return 0.8f; }

	@Override
	public void onValidPlayerCollision(EntityPlayer entityIn) {
		entityIn.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, getDuration(), 9));
		entityIn.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, getDuration(), 9));
		entityIn.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, getDuration(), 9));
		entityIn.sendMessage(new TextComponentString("You were hit by" + TextFormatting.LIGHT_PURPLE + " Slow Slow Beam" + TextFormatting.RESET + "."));
		entityIn.hurtResistantTime = 20;
	}

	@Override
	public void onValidEntityCollision(Entity entity) {
		EntityLiving e = (EntityLiving) entity;
		e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, getDuration(), 9));
		e.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, getDuration(), 9));
		e.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, getDuration(), 9));
	}


	int getDuration(){
		int level = getLevel(getType());

		switch(getType()){
			case 1:
				return 60 + level*10;
			case 2:
				return 100 + level*10;
			case 3:
				return 140 + level*10;
		}
		return 0;
	}

	@Override
	public float getCollisionBorderSize() {
		return 2.0F;
	}

}
