package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.OPUtils;
import georgetsak.opcraft.common.capability.haki.HakiCap;
import georgetsak.opcraft.dev_notUsed.OPLog;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

public class EntitySmokePunch extends EntityFlying {

    double x, y, z, startY;
    float yaw, pitch;
    Vec3d direction = new Vec3d(0, 0, 0);
    EntityPlayer ep;

    public EntitySmokePunch(World worldIn) {
        super(worldIn);
        setSize(1f, 1f);
    }

    public EntitySmokePunch(World worldIn, double x, double y, double z, float yaw, float pitch, EntityPlayer owner) {
        super(worldIn);

        this.ep = owner;
        this.x = x;
        this.y = this.startY = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.direction = OPUtils.convertRotation(yaw, pitch);
        direction.scale(2.5F);
        this.setPositionAndRotation(x, y, z, yaw, pitch);

        this.motionX = direction.x;
        this.motionY = direction.y;
        this.motionZ = direction.z;
        this.setSize(1F, 1F);
    }


    public void onLivingUpdate() {
        super.onLivingUpdate();
            this.motionX = direction.x;
            this.motionY = direction.y;
            this.motionZ = direction.z;
    }

    public void onCollideWithPlayer(EntityPlayer entityIn) {
        if (ep != entityIn && ep != null && !this.world.isRemote && entityIn.hurtResistantTime == 0) {
            float damage = OPUtils.damageCalculation(entityIn, 12F, true);
            entityIn.attackEntityFrom(DamageSource.causePlayerDamage(ep), damage);
            entityIn.hurtResistantTime = 20;
        }
    }

    public void onUpdate() {
        super.onUpdate();
        spawnParticles();
        if (this.ticksExisted >= 80) {
            this.setDead();
        }
    }

    public void collideWithEntity(Entity entityIn) {
        if (!this.world.isRemote && entityIn instanceof EntityLiving) {
                entityIn.attackEntityFrom(DamageSource.causePlayerDamage(ep), OPUtils.damageCalculation(ep, 12F, true));
        }
    }


    //@#@#@#@#@#@#@#@#@#@#@##@#@#@#@
    Random r = new Random();
    private void spawnParticles() {
        for(int i = 0; i < 2; i++){
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
