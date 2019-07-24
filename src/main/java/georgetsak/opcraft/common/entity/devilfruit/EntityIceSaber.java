package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

public class EntityIceSaber extends EntityFlying {

    double x, y, z, startY;
    float yaw, pitch;
    Vec3d direction = new Vec3d(0,0,0);
    EntityPlayer ep;

    public EntityIceSaber(World world){
        super(world);
        this.setSize(1F, 1F);
    }

    public EntityIceSaber(World worldIn, double x, double y, double z, float yaw, float pitch, EntityPlayer owner) {
        this(worldIn);
        this.ep = owner;
        this.x = x;
        this.y = this.startY = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.direction = MathUtils.convertRotation(yaw, pitch);
        direction.scale(2.5F);
        this.setPositionAndRotation(x, y, z, yaw, pitch);
        this.motionX = direction.x;
        this.motionY = direction.y;
        this.motionZ = direction.z;
        this.setSize(0.5F, 0.5F);
    }

    public void onLivingUpdate(){
        super.onLivingUpdate();

        spawnParticles(rand);

        if(collided){
            setDead();
        }
            motionX = direction.x;
            motionY = direction.y;
            motionZ = direction.z;
    }

    public void onCollideWithPlayer(EntityPlayer entityIn)
    {
        if(ep != entityIn && ep != null && entityIn.hurtResistantTime == 0){
            entityIn.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 1));
            entityIn.attackEntityFrom(DamageSource.causePlayerDamage(ep), MathUtils.calculateDamage(entityIn, 6F, true));
            entityIn.hurtResistantTime = 20;
            this.setDead();
        }
    }

    public void onUpdate()
    {
        super.onUpdate();
        if(this.ticksExisted >= 80)
        {
            this.setDead();
        }
    }

    public void collideWithEntity(Entity entityIn){
        if(entityIn instanceof EntityLiving){
            EntityLiving e = (EntityLiving)entityIn;
            e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 1));
            entityIn.attackEntityFrom(DamageSource.causePlayerDamage(ep), 6F);
            entityIn.hurtResistantTime = 20;
            this.setDead();
        }
    }

    public void applyEntityCollision(Entity entityIn)
    {

    }

    //@#@#@#@#@#@#@#@#@#@#@##@#@#@#@

    private void spawnParticles(Random r) {
        for(int i = 0; i < 25; i++){
            double offsetX = ((double)r.nextInt(20)+ 1 -10) / 20;
            double offsetY = ((double)r.nextInt(20)+ 1 -10) / 20 + 0.5;
            double offsetZ = ((double)r.nextInt(20)+ 1 -10) / 20;

            this.world.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0, 0, 0);
        }
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
