package georgetsak.opcraft.common.registry;

import georgetsak.opcraft.common.dimension.mirror.MirrorWorldProvider;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class OPDimensions {

    public static DimensionType MIRROR;

    public static void registerDimensions(){
        MIRROR = DimensionType.register("mirror", "_mirror", 3, MirrorWorldProvider.class, false);
        DimensionManager.registerDimension(3, MIRROR);
    }

}
