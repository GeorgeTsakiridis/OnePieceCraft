package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.MathUtils;
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
        super(world, x, y, z, yaw, pitch, 1f, 0.5f, owner);
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
    public void onValidPlayerCollision(EntityPlayer entityIn) {
        entityIn.attackEntityFrom(OPUtils.causePlayerCustomDamage(owner,true), MathUtils.calculateDamage(entityIn,8f + getLevel(1),true));
    }

    @Override
    public void onValidEntityCollision(Entity entity) {
        entity.attackEntityFrom(OPUtils.causePlayerCustomDamage(owner,true), MathUtils.calculateDamage(owner, 8f + getLevel(1), true));
    }

    public float getCollisionBorderSize()
    {
        return 1.0F;
    }

}
