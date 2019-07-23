package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityLongLine extends EntitySimpleProjectile{
    public EntityLongLine(World world, double x, double y, double z, float yaw, float pitch, float width, float height, EntityPlayer owner) {
        super(world, x, y, z, yaw, pitch, width, height, owner);
    }

    public EntityLongLine(World worldIn) {
        super(worldIn);
    }

    @Override
    public float getSpeedMultiplier() {
        return 0f;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }
}
