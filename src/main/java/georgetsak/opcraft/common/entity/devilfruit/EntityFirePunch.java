package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.MathUtils;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityFirePunch extends EntitySimpleProjectile {

    private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityFirePunch.class, DataSerializers.VARINT);

    public EntityFirePunch(World world){
		super(world);
		this.getDataManager().register(TYPE, 0);
		this.setSize(0.5F, 0.5F);
	}

	public EntityFirePunch(World worldIn, double x, double y, double z, float yaw, float pitch, EntityPlayer owner, int type) {
		super(worldIn, x, y, z, yaw, pitch, 0.5f, 0.5f, owner);
		this.getDataManager().register(TYPE, type);
		this.getDataManager().setDirty(TYPE);
	}

	public int getType(){
        return this.getDataManager().get(TYPE);
    }

	public void onUpdate() {
		super.onUpdate();

		boolean flag1 = getType() != 1 && getType() != 0;

		if (this.collided && flag1) {
			OPUtils.newExplosion(owner, world, posX, posY, posZ, getExplosionSize(), true, true);
			this.setDead();
		}

		if (getType() != 0 && this.ticksExisted > getMaxTicks()) {
			this.setDead();
		}
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn) {
		if (!isCollisionWithPlayerValid(entityIn) || getType() == 0) return;

		entityIn.attackEntityFrom(OPUtils.causePlayerCustomDamage(owner,true), MathUtils.calculateDamage(entityIn, getDamageValue(), true));
		entityIn.setFire(getFireTimeValue());
	}

	@Override
	public void collideWithEntity(Entity entityIn) {
    	if (!isCollisionWithEntityValid(entityIn) || getType() == 0) return;

		entityIn.attackEntityFrom(OPUtils.causePlayerCustomDamage(owner,true), getDamageValue());
		entityIn.setFire(getFireTimeValue());

	}

	//@#@#@#@#@#@#@#@#@#@#@##@#@#@#@

	public void spawnParticles() {
		for(int i = 0; i < 25; i++){
			 double offsetX = ((double)rand.nextInt(20)+ 1 -10) / 20;
			 double offsetY = ((double)rand.nextInt(20)+ 1 -10) / 20 + 0.5;
			 double offsetZ = ((double)rand.nextInt(20)+ 1 -10) / 20;
	
			 world.spawnParticle(EnumParticleTypes.FLAME, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0, 0, 0);
			 world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0, 0, 0);
			 
		 }		
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		dataManager.set(TYPE,compound.getInteger("FireType"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("FireType",getType());
    	return super.writeToNBT(compound);
	}

	@Override
	public int getMaxTicks()	{
		return 160;
	}

	private float getExplosionSize() {
		 switch(getType()){
		 case 1: return 0F;
		 case 2: return 1.5F;
		 case 3: return 2F;
		 case 4: return 4F;
		 default: return 0;
		 }
	}
	
	private int getFireTimeValue(){

		switch(getType()){
		case 1: return 2;
		case 2: return 5;
		case 3: return 8;
		case 4: return 10;
		default: return 0;
		}
	}
	 
	private float getDamageValue() {

		switch(getType()){
		case 1: return 2F;
		case 2: return 7F;
		case 3: return 5F;
		case 4: return 10F;
		default: return 0F;
		}
	}

	@Override
	public double getMaxDistance() {
		switch(getType()){
			default:
			case 1: return 70;
			case 2: return 60;
			case 3: return 200;
			case 4: return 100;
		}
	}

	 public float getSpeedMultiplier() {

		 switch(getType()){
		 case 1: return 1f;
		 case 2: return 1.2f;
		 case 3: return 1.5f;
		 case 4: return 3.5f;
		 default: return 0f;
		 }
	}

	 public void applyEntityCollision(Entity entityIn) {
	 }

	public float getCollisionBorderSize()
	    {
	        return 1.0F;
	    }
	
}
