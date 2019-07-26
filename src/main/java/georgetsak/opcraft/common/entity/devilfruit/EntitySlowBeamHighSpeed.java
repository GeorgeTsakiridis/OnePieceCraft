package georgetsak.opcraft.common.entity.devilfruit;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntitySlowBeamHighSpeed extends EntitySlowBeam{
    public EntitySlowBeamHighSpeed(World world) {
        super(world);
    }

    public EntitySlowBeamHighSpeed(World worldIn, double x, double y, double z, float yaw, float pitch, EntityPlayer owner) {
        super(worldIn, x, y, z, yaw, pitch, owner);
    }

    @Override
    public float getSpeedMultiplier() {
        return 1.5f;
    }
}
