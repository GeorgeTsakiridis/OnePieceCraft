package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.MathUtils;
import georgetsak.opcraft.common.util.OPUtils;
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
    public void onValidPlayerCollision(EntityPlayer entityIn) {
        entityIn.attackEntityFrom(OPUtils.causePlayerCustomDamage(owner,true), MathUtils.calculateDamage(entityIn, 12f + getLevel(3),true));
        entityIn.hurtResistantTime = 20;
    }

    @Override
    public void onValidEntityCollision(Entity entity) {
        entity.attackEntityFrom(OPUtils.causePlayerCustomDamage(owner,true), 12f + getLevel(3));
    }

}
