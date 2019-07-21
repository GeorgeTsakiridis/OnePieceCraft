package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.client.OPSoundEvent;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityEntei extends EntityFlying {

	private EntityPlayer ep;

	public EntityEntei(World world){
		super(world);
		this.setSize(10F, 10F);
	}
	
	 public EntityEntei(World worldIn, double x, double y, double z, EntityPlayer owner) {
			super(worldIn);
			this.ep = owner;
			this.setPositionAndRotation(x, y, z, 0, 0);
			this.setSize(10F, 10F);
	}

	public void onLivingUpdate(){
		 super.onLivingUpdate();
		 //move the big ball above player's head
	        if(ep != null){
	        	this.posX = ep.posX;
	        	this.posY = ep.posY + 10;
	        	this.posZ = ep.posZ;
			}
		//spawns the fireballs every half a second in condition the big ball is over 60 ticks old.
		 if(this.ticksExisted >= 60 && ep != null &&
		    	this.ticksExisted % 10 == 0 &&
		    	this.world.getEntityByID(ep.getEntityId()) != null &&
		    	this.world.getEntityByID(ep.getEntityId()) instanceof EntityPlayer &&
		    	ep.getName().equals(this.world.getEntityByID(ep.getEntityId()).getName())){

				 EntityPlayer e = (EntityPlayer) this.world.getEntityByID(ep.getEntityId());

				 Vec3d vec3d = new Vec3d(ep.posX, ep.posY + (double)ep.getEyeHeight(), ep.posZ);
				 Vec3d vec3d1 = ep.getLook(1);
				 Vec3d vec3d2 = vec3d.addVector(vec3d1.x * 150, vec3d1.y * 150, vec3d1.z * 150);
				 RayTraceResult result = this.world.rayTraceBlocks(vec3d, vec3d2, false, false, true);

				 if (result != null && result.typeOfHit != RayTraceResult.Type.MISS) {
					 this.world.spawnEntity(new EntityFirePunch(world, posX, posY, posZ, e.rotationYaw, 90F - calculatePitch(e, result.getBlockPos()), ep, 3));
					 this.world.playSound((EntityPlayer) null, posX, posY, posZ, OPSoundEvent.fire_fist, SoundCategory.NEUTRAL, 10.0F, 1.0F);
				 }
		 }
	}

	    private float calculatePitch(EntityPlayer player, BlockPos target) {
			double Px = player.posX;
			double Py = player.posY + player.height;
			double Pz = player.posZ;
			double Sx = Px, Sy = Py + 10D, Sz = Pz;
			double Tx = target.getX();
			double Ty = target.getY();
			double Tz = target.getZ();
			double d = Math.sqrt(Math.pow(Px - Tx, 2) + Math.pow(Py - Ty, 2) + Math.pow(Pz - Tz, 2));
			double dt = Math.sqrt(Math.pow(Sx - Tx, 2) + Math.pow(Sy - Ty, 2) + Math.pow(Sz - Tz, 2));
			double r = Math.acos((Math.pow(dt, 2) - Math.pow(d, 2) + 100D) / (20D * dt));
			return (float) Math.toDegrees(r);
		}


	 public void onCollideWithPlayer(EntityPlayer entityIn)
	    {
		 if(ep != entityIn && ep != null){
			 entityIn.attackEntityFrom(DamageSource.causePlayerDamage(ep), OPUtils.calculateDamage(entityIn, 10F, true));
			 entityIn.setFire(10);
		 }
		 }
	 
	 public void onUpdate(){
		 super.onUpdate();

		 if (this.ticksExisted >= 240){
			 this.setDead();
		 }
		 //Spawns the particles
		 for (int x = -5; x < 5; x++) {
			 for (int y = -5; y < 5; y++) {
				 for (int z = -5; z < 5; z++) {
					 if (x == 4 || x == -5 || y == 4 || y == -5 || z == 4 || z == -5) {
						 this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX + x + 0.5 + (Math.random() * 2 - 1), this.posY + y + 5.5 + (Math.random() * 2 - 1), this.posZ + z + 0.5 + (Math.random() * 2 - 1), (Math.random() * 2 - 1) / 20, (Math.random() * 2 - 1) / 20, (Math.random() * 2 - 1) / 20);
					 }
				 }
			 }
		 }
	 }

	 public void collideWithEntity(Entity entityIn){
		 if(entityIn instanceof EntityLiving){
			 entityIn.attackEntityFrom(DamageSource.causePlayerDamage(ep), 10F);
			 entityIn.setFire(10);
		 }
	 }
	 
	 public void applyEntityCollision(Entity entityIn)
	    {
	    }
	
	 public float getCollisionBorderSize()
	    {
	        return 1.0F;
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
