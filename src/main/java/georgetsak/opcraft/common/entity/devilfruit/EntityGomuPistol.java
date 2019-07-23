package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityGomuPistol extends EntitySimpleProjectile {

	public static final DataParameter<Boolean> IS_GEAR_3 = EntityDataManager.<Boolean>createKey(EntityGomuPistol.class, DataSerializers.BOOLEAN);

	public EntityGomuPistol(World world){
		super(world);
        this.dataManager.register(IS_GEAR_3, false);
        this.setSize(1.5F, 1.5F);
	}
	
	public EntityGomuPistol(World worldIn, double x, double y, double z, float yaw, float pitch, EntityPlayer owner, boolean isGear3) {
		super(worldIn, x, y, z, yaw, pitch,1.5f, 1.5f, owner);
		this.dataManager.register(IS_GEAR_3, isGear3);

		if(isGear3){
			this.setSize(3F, 3F);
		}
		else{
		this.setSize(1.5F, 1.5F);
		}
	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBox(Entity entityIn) {
		return super.getCollisionBox(entityIn);
	}

	@Override
	public void onMaxDistanceCovered() {
		super.onMaxDistanceCovered();
	}

	@Override
	public int getMaxTicks() {
		return isGear3() ? 100 : 800;
	}

	@Override
	public float getSpeedMultiplier() {
		return isGear3() ? 1.4f : 2.5f;
	}

	@Override
	public double getMaxDistance() {
		return isGear3() ? 45 : 55;
	}

	@Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        setIsGear3(compound.getBoolean("is_gear3"));

    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("is_gear3", this.getDataManager().get(IS_GEAR_3));
    }

	 public void onLivingUpdate() {
		 super.onLivingUpdate();

		 if (this.collided && isGear3()) {
			 this.world.newExplosion(owner, posX, posY, posZ, 3.5F, false, true);
			 this.setDead();
		 }
	 }
	
	 public void onCollideWithPlayer(EntityPlayer entityIn) {
		 if (!isCollisionWithPlayerValid(entityIn)) return;

		 if (isGear3()) {
			 entityIn.attackEntityFrom(DamageSource.causePlayerDamage(owner), OPUtils.calculateDamage(entityIn, 12F, true));
		 } else {
			 entityIn.attackEntityFrom(DamageSource.causePlayerDamage(owner), OPUtils.calculateDamage(entityIn, 8F, true));
		 }

		 entityIn.hurtResistantTime = 20;

	 }
	
	 public void collideWithEntity(Entity entityIn) {
		 if (!isCollisionWithEntityValid(entityIn)) return;

		 entityIn.attackEntityFrom(DamageSource.causePlayerDamage(owner), OPUtils.calculateDamage(owner, 8F, true));

	 }

	 public float getCollisionBorderSize()
	    {
	        return 2.0F;
	    }
	 

	public void setIsGear3(boolean b){
		this.dataManager.set(IS_GEAR_3, b);
	}

	public boolean isGear3(){
		return this.dataManager.get(IS_GEAR_3);
	}


}
