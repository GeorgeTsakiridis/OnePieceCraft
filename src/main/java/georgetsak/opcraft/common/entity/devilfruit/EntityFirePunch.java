package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

public class EntityFirePunch extends EntityFlying {

	private double x, y, z, startY, scale;
	private float yaw, pitch;
	public int type = 0;
	private Vec3d direction = new Vec3d(0,0,0);
	private EntityPlayer ep;
	private Random r = new Random();
    private static final DataParameter<Integer> TYPE = EntityDataManager.<Integer>createKey(EntityZombie.class, DataSerializers.VARINT);//used to send the TYPE to the ModelFireFist.

    public EntityFirePunch(World world){
		super(world);
        this.getDataManager().register(TYPE, 0);
        this.setSize(0.5F, 0.5F);
	}
	
	public EntityFirePunch(World worldIn, double x, double y, double z, float yaw, float pitch, EntityPlayer owner, int type) {
		this(worldIn);

		this.type = type;
        this.getDataManager().set(TYPE, type);
        this.ep = owner;
		this.x = x;
		this.y = this.startY = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
		this.direction = OPUtils.convertRotation(yaw, pitch);
		this.scale = getScale(this.type);
		this.setPositionAndRotation(x, y, z, yaw, pitch);
		this.setSize(0.5F, 0.5F);
		
	}

	public int getType(){
        return this.getDataManager().get(TYPE);
    }

	public void onLivingUpdate(){
		 super.onLivingUpdate();
			 if (this.movedDistance <= getMaxDistance("x") && Math.abs(this.startY - this.posY) <= getMaxDistance("y")){
		        	this.motionX = direction.x * scale;
		        	this.motionY = direction.y * scale;
		        	this.motionZ = direction.z * scale;
		        }
		 }

	public void onUpdate()
	    {
	        super.onUpdate();

	        spawnParticles();

	        boolean flag1 = type != 1 && type != 0;
	        		
        	if(this.collided && flag1){
	        	world.newExplosion(ep, posX, posY, posZ, getExplosionSize(), true, true);
	        	this.setDead();
	        }
        	
	        if(type != 0 && this.ticksExisted >= getLifetime())
	        {
	            this.setDead();
	        }
	        
	    }
	
	public void onCollideWithPlayer(EntityPlayer entityIn)
	{
	 if(ep != entityIn && ep != null && type != 0){
		 entityIn.attackEntityFrom(DamageSource.causePlayerDamage(ep), OPUtils.damageCalculation(entityIn, getDamageValue(), true));
		 entityIn.setFire(getFireTimeValue());
	 }
	 }

	public void collideWithEntity(Entity entityIn){
		 if(entityIn instanceof EntityLiving  && type != 0){
			 entityIn.attackEntityFrom(DamageSource.causePlayerDamage(ep), getDamageValue());
			 entityIn.setFire(getFireTimeValue());
		 }
	 }
	 
	

	//@#@#@#@#@#@#@#@#@#@#@##@#@#@#@

	private void spawnParticles() {
		for(int i = 0; i < 25; i++){
			 double offsetX = ((double)r.nextInt(20)+ 1 -10) / 20;
			 double offsetY = ((double)r.nextInt(20)+ 1 -10) / 20 + 0.5;
			 double offsetZ = ((double)r.nextInt(20)+ 1 -10) / 20;
	
			 world.spawnParticle(EnumParticleTypes.FLAME, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0, 0, 0);
			 world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0, 0, 0);
			 
		 }		
	}
	
	 private int getLifetime() {

		 switch(type){
		 case 1: return 80;
		 case 2: return 80;
		 case 3: return 160;
		 case 4: return 60;
		 default: return 0;
		 }
		 
	}

	private float getExplosionSize() {

		 switch(type){
		 case 1: return 0F;
		 case 2: return 1.5F;
		 case 3: return 2F;
		 case 4: return 4F;
		 default: return 0;
		 }
	}
	
	private int getFireTimeValue(){

		switch(type){
		case 1: return 3;
		case 2: return 5;
		case 3: return 8;
		case 4: return 10;
		default: return 0;
		}
	}
	 
	private float getDamageValue() {

		switch(type){
		case 1: return 3F;
		case 2: return 7F;
		case 3: return 5F;
		case 4: return 10F;
		default: return 0F;
		}
	}

	 private int getMaxDistance(String string) {
			
		 if(string.equals("x")){
			 switch(type){
			 case 1: return 70; 
			 case 2: return 60;
			 case 3: return 200;
			 case 4: return 100;
			 }
		 }
		 if(string.equals("y")){
			 switch(type){
			 case 1: return 40; 
			 case 2: return 40;
			 case 3: return 100;
			 case 4: return 60;
			 }
		 }
		 
		 return 0;
	}
	
	 private double getScale(int type2) {
		 switch(type){
		 case 1: return 1D;
		 case 2: return 1D;
		 case 3: return 1.5D;
		 case 4: return 3.5D;
		 default: return 0;
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
