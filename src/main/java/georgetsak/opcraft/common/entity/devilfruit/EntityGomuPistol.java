package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityGomuPistol extends EntityFlying {

	double x, y, z, startY;
	float yaw, pitch;
	public static final DataParameter<Boolean> IS_GEAR_3 = EntityDataManager.<Boolean>createKey(EntityGomuPistol.class, DataSerializers.BOOLEAN);

	Vec3d direction = new Vec3d(0,0,0);
	EntityPlayer ep;
	
	public EntityGomuPistol(World world){
		super(world);
        this.dataManager.register(IS_GEAR_3, false);
        this.setSize(1.5F, 1.5F);
	}
	
	public EntityGomuPistol(World worldIn, double x, double y, double z, float yaw, float pitch, EntityPlayer owner, boolean isGear3) {
		this(worldIn);
		this.ep = owner;
		this.x = x;
		this.y = this.startY = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
		this.direction = OPUtils.convertRotation(yaw, pitch);
		direction.scale(2.5F);
		this.setPositionAndRotation(x, y, z, yaw, pitch);
		setIsGear3(isGear3);
		this.motionX = direction.x;
		this.motionY = direction.y;
		this.motionZ = direction.z;

		if(isGear3()){
			this.setSize(3F, 3F);
		}
		else{
		this.setSize(1.5F, 1.5F);
		}
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

	 public void onLivingUpdate(){
		 super.onLivingUpdate();
		
     	if(this.collided && isGear3()){
	        	this.world.newExplosion(ep, posX, posY, posZ, 3.5F, false, true);
	        	this.setDead();
	        }
		 
		 if(isGear3()){
			 if (this.movedDistance <= 45 && Math.abs(this.startY - this.posY) <= 30){
		        	this.motionX = direction.x;
		        	this.motionY = direction.y;
		        	this.motionZ = direction.z;
		        }

		 }else{
		 if (this.movedDistance <= 55 && Math.abs(this.startY - this.posY) <= 35){
	        	this.motionX = direction.x;
	        	this.motionY = direction.y;
	        	this.motionZ = direction.z;
	        }
		 }
	    }
	
	 public void onCollideWithPlayer(EntityPlayer entityIn)
	    {
		 if(ep != entityIn && !isGear3() && ep != null){
			 entityIn.attackEntityFrom(DamageSource.causePlayerDamage(ep), OPUtils.damageCalculation(entityIn, 8F, true));
			 entityIn.hurtResistantTime = 20;
		 }
		 if(ep != entityIn && isGear3() && ep != null){
			 entityIn.attackEntityFrom(DamageSource.causePlayerDamage(ep), OPUtils.damageCalculation(entityIn, 12F, true));
			 entityIn.hurtResistantTime = 20;
		 }
		 }
	 
	 public void onUpdate()
	    {
		 int i = isGear3() ? 100 : 80;
	        super.onUpdate();
	        if(this.ticksExisted >= i)
	        {
	            this.setDead();
	        }
	    }
	
	 public void collideWithEntity(Entity entityIn){
		 if(entityIn instanceof EntityLiving){
			 entityIn.attackEntityFrom(DamageSource.causePlayerDamage(ep), OPUtils.damageCalculation(ep, 8F, true));
		 }
	 }
	 
	 public void applyEntityCollision(Entity entityIn)
	    {
		 
		 if (!this.isRidingSameEntity(entityIn))
	        {
	            if (!entityIn.noClip && !this.noClip)
	            {
	                double d0 = entityIn.posX - this.posX;
	                double d1 = entityIn.posZ - this.posZ;
	                double d2 = MathHelper.absMax(d0, d1);

	                if (d2 >= 0.009999999776482582D)
	                {
	                    d2 = (double)MathHelper.sqrt(d2);
	                    d0 = d0 / d2;
	                    d1 = d1 / d2;
	                    double d3 = 1.0D / d2;

	                    if (d3 > 1.0D)
	                    {
	                        d3 = 1.0D;
	                    }

	                    d0 = d0 * d3;
	                    d1 = d1 * d3;
	                    d0 = d0 * 0.05000000074505806D;
	                    d1 = d1 * 0.05000000074505806D;
	                    d0 = d0 * (double)(1.0F - this.entityCollisionReduction);
	                    d1 = d1 * (double)(1.0F - this.entityCollisionReduction);

	                    if (!this.isBeingRidden())
	                    {
	                        this.addVelocity(-d0, 0.0D, -d1);
	                    }

	                    if (!entityIn.isBeingRidden())
	                    {
	                        entityIn.addVelocity(d0, 0.0D, d1);
	                    }
	                }
	            }
	        }
		 
	    }
	
	//@#@#@#@#@#@#@#@#@#@#@##@#@#@#@

	 public float getCollisionBorderSize()
	    {
	        return 2.0F;
	    }
	 
	public boolean isEntityInvulnerable(DamageSource source){
		 return true;
	 }
	
	public  float getDistanceMoved(){
		return this.movedDistance;
	}

	public void setIsGear3(boolean b){
		this.dataManager.set(IS_GEAR_3, b);
	}

	public boolean isGear3(){
		return this.dataManager.get(IS_GEAR_3);
	}
	
	public boolean canBePushed(){
		return false;
	}
	
	public boolean canBeCollidedWith()
 {
     return true;
 }

	
}
