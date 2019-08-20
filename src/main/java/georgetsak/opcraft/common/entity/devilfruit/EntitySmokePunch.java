package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.MathUtils;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntitySmokePunch extends EntitySimpleProjectile {

    float speed = 1f;

    public EntitySmokePunch(World worldIn) {
        super(worldIn);
    }

    public EntitySmokePunch(World worldIn, double x, double y, double z, float yaw, float pitch, EntityPlayer owner) {
        super(worldIn, x, y, z, yaw, pitch, 1f, 1f, owner);
    }

    @Override
    public int getMaxTicks() {
        return 80;
    }

    @Override
    public float getSpeedMultiplier() {
        return speed;
    }

    @Override
    public void onValidPlayerCollision(EntityPlayer entityIn) {
        float damage = MathUtils.calculateDamage(entityIn, 12F, true);
        entityIn.attackEntityFrom(OPUtils.causePlayerCustomDamage(owner,true), damage);
        entityIn.hurtResistantTime = 20;
    }

    @Override
    public void onValidEntityCollision(Entity entity) {
        entity.attackEntityFrom(OPUtils.causePlayerCustomDamage(owner,true), MathUtils.calculateDamage(owner, 12F, true));
    }

    @Override
    public void onMaxDistanceCovered() {
        speed = 0f;
    }

    //@#@#@#@#@#@#@#@#@#@#@##@#@#@#@
     public void spawnParticles() {
        for(int i = 0; i < 2; i++){
            double offsetX = ((double)rand.nextInt(20)+ 1 - 10) / 14;
            double offsetY = ((double)rand.nextInt(20)+ 1 - 10) / 14 + 0.5;
            double offsetZ = ((double)rand.nextInt(20)+ 1 - 10) / 14;

            this.world.spawnParticle(EnumParticleTypes.CLOUD, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0, 0, 0);
        }
    }

    public float getCollisionBorderSize() {
        return 2.0F;
    }

}
