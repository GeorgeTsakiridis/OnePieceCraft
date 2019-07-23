package georgetsak.opcraft.common.entity.devilfruit;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityOverheat extends EntityLongLine{

    public EntityOverheat(World worldIn) {
        super(worldIn);
    }

    public EntityOverheat(World world, double x, double y, double z, boolean extend, EntityPlayer owner) {
        super(world, x, y, z, 300, owner, extend);
    }

    @Override
    public int getMaxTicks() {
        return 999999999;
    }

    @Override
    public void extendTo(double x, double y, double z, EntityPlayer owner) {
        world.spawnEntity(new EntityOverheat(world, x, y, z, false, owner));
    }
}
