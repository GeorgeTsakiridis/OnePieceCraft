package georgetsak.opcraft.common.entity.marine;

import net.minecraft.world.World;

/**
 * Created by GeorgeTsak on 7/31/2017.
 */
public class EntityWildHardMarine extends EntityHardMarine {

    public EntityWildHardMarine(World worldIn) {
        super(worldIn);
    }

    @Override
    protected boolean canDespawn() {
        return true;
    }
}
