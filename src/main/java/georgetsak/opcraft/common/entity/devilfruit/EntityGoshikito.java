package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGoshikito extends EntitySimpleProjectile{

    public EntityGoshikito(World world, double x, double y, double z, float yaw, float pitch, EntityPlayer owner) {
        super(world, x, y, z, yaw, pitch, 1.4f, 1f, owner);
    }

    public EntityGoshikito(World worldIn) {
        super(worldIn);
        setSize(1.4f, 1f);
    }

    @Override
    public float getSpeedMultiplier() {
        return 2.3f;
    }

    @Override
    public int getMaxTicks() {
        return 60;
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        if(!isCollisionWithPlayerValid(entityIn))return;

        entityIn.attackEntityFrom(DamageSource.causePlayerDamage(owner), MathUtils.calculateDamage(entityIn, 14f,true));
        entityIn.hurtResistantTime = 20;

    }

    @Override
    public void collideWithEntity(Entity entityIn) {
        if (!isCollisionWithEntityValid(entityIn)) return;

        entityIn.attackEntityFrom(DamageSource.causePlayerDamage(owner), 14f);
    }

}
