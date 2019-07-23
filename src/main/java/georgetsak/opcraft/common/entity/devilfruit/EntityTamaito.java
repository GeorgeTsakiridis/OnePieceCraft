package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityTamaito extends EntitySimpleProjectile {

    public EntityTamaito(World worldIn) {
        super(worldIn);
        setSize(1f,0.5f);
        this.ignoreFrustumCheck = true;
    }

    public EntityTamaito(World world, double x, double y, double z, float yaw, float pitch, EntityPlayer owner){
        super(world, x, y, z, 1f, 0.5f, owner);
        setSize(1f,0.5f);
        this.ignoreFrustumCheck = true;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    @Override
    public float getSpeedMultiplier() {
        return 2.0f;
    }

    @Override
    public int getMaxTicks() {
        return 40;
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        if(!isCollisionWithPlayerValid(entityIn))return;

        entityIn.attackEntityFrom(DamageSource.causePlayerDamage(owner),OPUtils.calculateDamage(entityIn,8f,true));
    }

    @Override
    public void collideWithEntity(Entity entityIn) {
        if (!isCollisionWithEntityValid(entityIn)) return;

        entityIn.attackEntityFrom(DamageSource.causePlayerDamage(owner), OPUtils.calculateDamage(owner, 8F, true));
    }

    public float getCollisionBorderSize()
    {
        return 1.0F;
    }

}
