package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.capability.devilfruitlevels.DevilFruitLevelsCap;
import georgetsak.opcraft.common.util.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class EntitySmokeSnake extends EntityFlying {

    double x, y, z;
    EntityPlayer ep;
    Entity target;

    public EntitySmokeSnake(World worldIn) {
        super(worldIn);
        setSize(1f, 1f);
    }

    public EntitySmokeSnake(EntityPlayer owner, Entity target) {
        super(owner.world);

        this.ep = owner;
        this.target = target;
        this.x = owner.posX;
        this.y = owner.posY + 0.6f;
        this.z = owner.posZ;
        this.setPosition(x, y, z);
        this.setSize(1F, 1F);
    }


    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(target != null){
                double distanceX = target.posX - posX;
                double distanceY = target.posY - posY;
                double distanceZ = target.posZ - posZ;

                motionX = MathHelper.clamp(distanceX, -.5d, .5d);
                motionY = MathHelper.clamp(distanceY, -.5d, .5d);
                motionZ = MathHelper.clamp(distanceZ, -.5d, .5d);
        }
    }

    public void onCollideWithPlayer(EntityPlayer entityIn) {
        if (ep != entityIn && ep != null && !this.world.isRemote) {
            entityIn.attackEntityFrom(DamageSource.causePlayerDamage(ep), MathUtils.calculateDamage(entityIn, 4f + getLevel()*2f, true));
            entityIn.hurtResistantTime = 20;
        }
    }

    public void onUpdate() {
        super.onUpdate();
        spawnParticles();
        if (this.ticksExisted >= 160) {
            this.setDead();
        }
    }

    @Override
    protected void setRotation(float yaw, float pitch) {
        super.setRotation(yaw, pitch);
        rotationYaw = (float) (Math.atan2(motionZ, motionX) * (180D / Math.PI)) + 180f;
        setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
    }

    public void collideWithEntity(Entity entityIn) {
        if (ep != null && entityIn instanceof EntityLiving && !this.world.isRemote) {
            entityIn.attackEntityFrom(DamageSource.causePlayerDamage(ep), MathUtils.calculateDamage(ep, 4f + getLevel()*2f, true));
        }
    }

    int getLevel(){
        if(ep != null){
            return DevilFruitLevelsCap.get(ep).getPowerLevel(2);
        }

        return 0;
    }

    //@#@#@#@#@#@#@#@#@#@#@##@#@#@#@
    Random r = new Random();
    private void spawnParticles() {
        for(int i = 0; i < 4; i++){
            double offsetX = ((double)r.nextInt(20)+ 1 -10) / 14;
            double offsetY = ((double)r.nextInt(20)+ 1 -10) / 14 + 0.5;
            double offsetZ = ((double)r.nextInt(20)+ 1 -10) / 14;

            this.world.spawnParticle(EnumParticleTypes.CLOUD, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0, 0, 0);
        }
    }

    public float getCollisionBorderSize() {
        return 2.0F;
    }

    public boolean isEntityInvulnerable(DamageSource source) {
        return true;
    }

    public boolean canBePushed() {
        return false;
    }

    public boolean canBeCollidedWith() {
        return true;
    }

}
