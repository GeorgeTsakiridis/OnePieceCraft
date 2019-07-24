package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.client.OPSoundEvent;
import georgetsak.opcraft.common.util.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntitySlowBeamSpawner extends EntityFlying {

	int lifetime, spacing = 9999;
	double x, y, z, startY;
	float yaw, pitch;
	boolean random = false;
	Vec3d direction = new Vec3d(0,0,0);
	EntityPlayer ep;
	
	public EntitySlowBeamSpawner(World world){
		super(world);
		this.setSize(1F, 1F);
	}
	
	public EntitySlowBeamSpawner(World worldIn, double x, double y, double z, float yaw, float pitch, EntityPlayer owner, boolean random, int lifetime, int spacing) {
		super(worldIn);
		this.lifetime = lifetime;
		this.random = random;
		this.spacing = spacing;
		this.ep = owner;
		this.x = x;
		this.y = this.startY = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
		this.direction = MathUtils.convertRotation(yaw, pitch);
		direction.scale(2.5F);
		this.motionX = 0;
		this.motionY = 0;
		this.motionZ = 0;
		this.setPositionAndRotation(x, y, z, yaw, pitch);
	}

	public void onLivingUpdate(){
		 super.onLivingUpdate();
     	this.motionX = 0;
     	this.motionY = 0;
     	this.motionZ = 0;
	    }
	
	 public void onUpdate(){
	        super.onUpdate();
	        
			 if(ticksExisted % spacing == 0){
				 if(random){
				 int yaw = 12;
				 int pitch = 7;
				 for(int o = yaw; o > 0; o--){
					 for(int k = pitch; k > 0; k--){
						 EntitySlowBeam esb = new EntitySlowBeam(world, posX, posY, posZ, (o * (360/yaw)) - 180, (k * (180 / pitch)) - 90, ep);
						 world.playSound(null, this.getPosition(), OPSoundEvent.slow_beam, SoundCategory.NEUTRAL, 5, 1);
						 world.spawnEntity(esb);
					 }
					 EntitySlowBeam esb = new EntitySlowBeam(world, posX, posY, posZ, o * (360/yaw), 0, ep);
					 world.spawnEntity(esb);
				 }
				 }
				 else{
					 if(ep != null && this.world.getEntityByID(ep.getEntityId()) != null && this.world.getEntityByID(ep.getEntityId()) instanceof EntityPlayer){
						 if(ep.getName() == this.world.getEntityByID(ep.getEntityId()).getName()){
							 EntityPlayer e = (EntityPlayer) this.world.getEntityByID(ep.getEntityId());
							 this.world.spawnEntity(new EntitySlowBeam(world, e.posX, e.posY+0.8f, e.posZ, e.rotationYaw, e.rotationPitch, ep, true));
							 if(this.ticksExisted % 2 == 0){
								 world.playSound(null, this.getPosition(), OPSoundEvent.slow_beam, SoundCategory.NEUTRAL, 5, 1);
							 }
						 }}
					 }
				 }
			 

	        
	        if(this.ticksExisted >= lifetime)
	        {
	            this.setDead();
	        }
	    }
	
	 public void onCollideWithPlayer(EntityPlayer entityIn){
		 }

	 
	 public void collideWithEntity(Entity entityIn){
	 }
	 
	 public void applyEntityCollision(Entity entityIn){
	    }
	
	 public float getCollisionBorderSize()
	    {
	        return 2.0F;
	    }
	 
	public boolean isEntityInvulnerable(DamageSource source){
		 return true;
	 }
	
	public boolean canBePushed(){
		return false;
	}
	
	public boolean canBeCollidedWith()
 {
     return true;
 }

	
}
