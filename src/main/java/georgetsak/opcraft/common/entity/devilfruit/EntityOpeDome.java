package georgetsak.opcraft.common.entity.devilfruit;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityOpeDome extends EntityFlying {

	double x, y, z, startY;
	EntityPlayer ep;
	
	public EntityOpeDome(World world){
		super(world);
		this.setSize(1F, 1F);
	}
	
	public EntityOpeDome(World worldIn, double x, double y, double z, EntityPlayer owner) {
		super(worldIn);
		this.ep = owner;
		this.x = x;
		this.y = this.startY = y;
		this.z = z;
		this.setVelocity(0, 0, 0);
		this.setPosition(x, y, z);
	}

	public void onLivingUpdate(){
		 super.onLivingUpdate();
     	this.motionX = 0;
     	this.motionY = 0;
     	this.motionZ = 0;
	    }
	
	 public void onUpdate(){
	        super.onUpdate();
	        if(this.ticksExisted >= 400)
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
	
	//@#@#@#@#@#@#@#@#@#@#@##@#@#@#@
	
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
